package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
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
    Tweet actualTweet = TweetUtil.createTweet();
    String hashtag = "#abc";

    Tweet tweet = service.postTweet(actualTweet);

    assertEquals(actualTweet.getText(), tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getLongLat().length);
    assertEquals(1, tweet.getCoordinates().getLongLat()[0], 0.0001);
    assertEquals(-1, tweet.getCoordinates().getLongLat()[1], 0.0001);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldThrowIllegalArgumentExceptionForWrongLatitude() {
    Tweet actualTweet = TweetUtil.createTweetWithWrongLatitude();

    Tweet tweet = service.postTweet(actualTweet);
  }

  @Test
  public void shouldShowTweet() {
    String hashtag = "#abc";
    Tweet postTweet = TweetUtil.createTweet();
    Tweet postedTweet = service.postTweet(postTweet);
    String id = postedTweet.getIdString();

    Tweet tweet = service.showTweet(id, null);

    assertEquals(postTweet.getText(), tweet.getText());
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
  public void shouldDeleteTweets() {
    Tweet firstPostTweet = TweetUtil.createTweet();
    Tweet secondPostTweet = TweetUtil.createDifferentTweet();
    Tweet firstPostedTweet = service.postTweet(firstPostTweet);
    Tweet secondPostedTweet = service.postTweet(secondPostTweet);
    String firstId = firstPostedTweet.getIdString();
    String secondId = secondPostedTweet.getIdString();
    String[] ids = {firstId, secondId};

    List<Tweet> tweets = service.deleteTweets(ids);

    assertEquals(firstPostTweet.getText(), tweets.get(0).getText());
    assertEquals(secondPostTweet.getText(), tweets.get(1).getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForWrongIdFormats() {
    String[] ids = {"string", "string2"};

    List<Tweet> tweet = service.deleteTweets(ids);
  }
}