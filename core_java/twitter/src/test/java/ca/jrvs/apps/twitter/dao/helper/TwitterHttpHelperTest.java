package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {

  String CONSUMER_KEY = System.getenv("consumerKey");
  String CONSUMER_SECRET = System.getenv("consumerSecret");
  String ACCESS_TOKEN = System.getenv("accessToken");
  String TOKEN_SECRET = System.getenv("tokenSecret");

  TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
      ACCESS_TOKEN, TOKEN_SECRET);
  URI uri;
  HttpResponse response;


  @Test(expected = RuntimeException.class)
  public void httpPost() throws Exception {
    uri = new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=TestingTwitterHttpHelperPost2");
    response = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
    uri = new URI(
        "https://api.twiter.com/1.1/statuses/update.json?status=TestingTwitterHttpHelperPost2");
    response = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test(expected = RuntimeException.class)
  public void httpGet() throws Exception {
    uri = new URI("https://api.twitter.com/1.1/statuses/show.json?id=1247691063831207936");
    response = twitterHttpHelper.httpGet(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
    uri = new URI("https://api.twtter.com/1.1/statuses/show.json?id=1247691063831207936");
    response = twitterHttpHelper.httpGet(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}