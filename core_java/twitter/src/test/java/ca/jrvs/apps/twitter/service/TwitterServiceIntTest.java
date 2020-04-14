package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    //setup dependency
    HttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao dao = new TwitterDao(twitterHttpHelper);
    //pass dependency
    service = new TwitterService(dao);
  }

  @Test
  public void ShouldPostTweet() {
    String status = "some text #abc " + System.currentTimeMillis();
    float[] longLat = {1, -1};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet actualTweet = new Tweet(status, coordinates);

    Tweet tweet = service.postTweet(actualTweet);

    assertEquals(status, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getLongLat().length);
    assertEquals(1, tweet.getCoordinates().getLongLat()[0], 0.0001);
    assertEquals(-1, tweet.getCoordinates().getLongLat()[1], 0.0001);

    assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));

  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldThrowIllegalArgumentExceptionForWrongLatitude() {
    String status = "some text #abc " + System.currentTimeMillis();
    float[] longLat = {1, -100};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet actualTweet = new Tweet(status, coordinates);

    Tweet tweet = service.postTweet(actualTweet);
  }

  @Test
  public void shouldShowTweet() {
    String id = "1250185392676130816";
    String status = "some text #abc 1586902399442";
    float[] longLat = {1, -1};
    String hashtag = "#abc";

    Tweet tweet = service.showTweet(id, null);
    assertEquals(status, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getLongLat().length);
    assertEquals(1, tweet.getCoordinates().getLongLat()[0], 0.0001);
    assertEquals(-1, tweet.getCoordinates().getLongLat()[1], 0.0001);

    assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForWrongIdFormat() {
    String id = "string";
    Tweet tweet = service.showTweet(id, null);
  }

  @Test
  public void deleteTweets() {
    String[] ids = {"1250182286739492864", "1250176142574837760"};
    String firstStatus = "some text #abc 1586901658280";
    String secondStatus = "some text #abc 1586900193383";

    List<Tweet> tweets = service.deleteTweets(ids);

    assertEquals(firstStatus, tweets.get(0).getText());
    assertEquals(secondStatus, tweets.get(1).getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForWrongIdFormats() {
    String[] ids = {"string", "string2"};
    List<Tweet> tweet = service.deleteTweets(ids);
  }
}