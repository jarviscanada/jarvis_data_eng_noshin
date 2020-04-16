package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private TwitterController controller;

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
    Service service = new TwitterService(dao);
    //pass dependency
    controller = new TwitterController(service);
  }

  @Test
  public void shouldPostTweet() {
    String status = "some text #abc " + System.currentTimeMillis();
    String hashtag = "#abc";
    String[] args = {"post", status, "1:-1"};

    Tweet tweet = controller.postTweet(args);

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
    String hashtag = "#abc";
    String[] args = {"post", "status", "1:-100"};

    Tweet tweet = controller.postTweet(args);
  }

  @Test
  public void shouldShowTweet() {
    String status = "some text #abc " + System.currentTimeMillis();
    String hashtag = "#abc";
    String[] postArgs = {"post", status, "1:-1"};
    Tweet postedTweet = controller.postTweet(postArgs);
    String id = postedTweet.getIdString();
    status = postedTweet.getText();
    float[] longLat = {1, -1};

    String[] args = {"show", id};

    Tweet tweet = controller.showTweet(args);

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
    String[] args = {"show", id};

    Tweet tweet = controller.showTweet(args);
  }

  @Test
  public void shouldDeleteTweets() {
    String firstStatus = "some text #abc " + System.currentTimeMillis();
    String hashtag = "#abc";
    String[] firstPostArgs = {"post", firstStatus, "1:-1"};
    String secondStatus = "some text 2 #abc " + System.currentTimeMillis();
    String[] secondPostArgs = {"post", secondStatus, "1:-1"};
    Tweet firstPostedTweet = controller.postTweet(firstPostArgs);
    Tweet secondPostedTweet = controller.postTweet(secondPostArgs);
    String firstId = firstPostedTweet.getIdString();
    String secondId = secondPostedTweet.getIdString();
    float[] longLat = {1, -1};

    String ids = firstId+","+secondId;
    String[] args = {"delete", ids};

    List<Tweet> tweets = controller.deleteTweet(args);

    assertEquals(firstStatus, tweets.get(0).getText());
    assertEquals(secondStatus, tweets.get(1).getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForWrongIdFormats() {
    String ids = "string,string2";
    String[] args = {"delete", ids};
    List<Tweet> tweet = controller.deleteTweet(args);
  }

}