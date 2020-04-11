package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.UserMention;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com/1.1/statuses/";
  private static final String CREATE_PATH = "update.json?status=";
  private static final String READ_PATH = "show.json?id=";
  private static final String DELETE_PATH = "destroy/";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";
  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    URI uri;
    try {
      uri = getCreateURI(entity);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Wrong input for tweet : " + e);
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return jsonParser(response);
  }

  public Tweet jsonParser(HttpResponse response) {
    Tweet tweet = null;
    int status = response.getStatusLine().getStatusCode();
    if (status != HTTP_OK) {
      throw new RuntimeException("Unexpected status code : " + status);
    } else if (response.getEntity() != null) {
      String tweetStr;
      try {
        tweetStr = EntityUtils.toString(response.getEntity());
      } catch (IOException e) {
        throw new RuntimeException("Failed to convert JSON into string : " + e);
      }
      try {
        return JsonParser.toObjectFromJson(tweetStr, Tweet.class);
      } catch (IOException e) {
        throw new RuntimeException("Failed to create Tweet object : " + e);
      }
    } else {
      throw new RuntimeException("Response body empty");
    }
  }

  public String createStatus(String text, List<Hashtag> hashtags, List<UserMention> userMentions) {
    StringBuilder tweet = new StringBuilder(text);
    hashtags.stream().forEach(hashtag -> tweet.insert(hashtag.getIndices()[0], hashtag.getText()));
    userMentions.stream().forEach(
        userMention -> tweet.insert(userMention.getIndices()[0], userMention.getScreenName()));
    return tweet.toString();
  }

  public URI getCreateURI(Tweet entity) throws URISyntaxException {
    String status = createStatus(entity.getText(), entity.getEntities().getHashtags(),
        entity.getEntities().getUserMentions());
    float[] coordinates = entity.getCoordinates().getLongLat();
    StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
    stringBuilder.append(CREATE_PATH);
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    stringBuilder.append(percentEscaper.escape(status));
    stringBuilder.append(percentEscaper.escape(AMPERSAND));
    stringBuilder.append("long");
    stringBuilder.append(EQUAL);
    stringBuilder.append(coordinates[0]);
    stringBuilder.append(percentEscaper.escape(AMPERSAND));
    stringBuilder.append("lat");
    stringBuilder.append(EQUAL);
    stringBuilder.append(coordinates[1]);
    return new URI(stringBuilder.toString());
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    URI uri;
    try {
      uri = getReadUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Tweet ID not found : " + e);
    }

    HttpResponse response = httpHelper.httpGet(uri);
    return jsonParser(response);
  }

  public URI getReadUri(String id) throws URISyntaxException {
    StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
    stringBuilder.append(READ_PATH);
    stringBuilder.append(id);
    return new URI(stringBuilder.toString());
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    URI uri;
    try {
      uri = getReadUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Tweet ID not found : " + e);
    }

    HttpResponse response = httpHelper.httpPost(uri);
    return jsonParser(response);
  }

  public URI getDeleteUri(String id) throws URISyntaxException {
    StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
    stringBuilder.append(DELETE_PATH);
    stringBuilder.append(id);
    return new URI(stringBuilder.toString());
  }
}
