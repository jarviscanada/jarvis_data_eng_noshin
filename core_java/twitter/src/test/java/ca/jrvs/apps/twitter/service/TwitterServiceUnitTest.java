package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void shouldPostTweet() {
    String status = "some text #abc 1586810461125";
    float[] longLat = {1, -1};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet newTweet = new Tweet(status, coordinates);

    when(dao.create(any())).thenReturn(newTweet);
    Tweet tweet = service.postTweet(newTweet);
    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongLatitude() {
    String status = "some text #abc 1586810461125";
    float[] longLat = {1, -100};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet newTweet = new Tweet(status, coordinates);

    Tweet tweet = service.postTweet(newTweet);
  }

  @Test
  public void shouldShowTweet() {
    String id = "1250185392676130816";
    String status = "some text #abc 1586902399442";
    float[] longLat = {1, -1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    String hashtag = "#abc";
    Tweet newTweet = new Tweet(status, coordinates);

    when(dao.findById(any())).thenReturn(newTweet);
    Tweet tweet = service.showTweet(id, null);
    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormat() {
    String status = "somestring";
    float[] longLat = {1, -100};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet newTweet = new Tweet(status, coordinates);

    Tweet tweet = service.postTweet(newTweet);
  }

  public void shouldDeleteTweets() {
    String[] ids = {"1250185392676130816", "1250975392676130816"};
    String status = "some text #abc 1586902399442";
    float[] longLat = {1, -1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    String hashtag = "#abc";
    Tweet firstTweet = new Tweet(status, coordinates);
    status = "some text #abc 1586902399332";
    Tweet secondTweet = new Tweet(status, coordinates);
    List<Tweet> tweets = new LinkedList<>();
    tweets.add(firstTweet);
    tweets.add(secondTweet);

    when(dao.deleteById((any()))).thenReturn(tweets);
    List<Tweet> tweet = service.deleteTweets(ids);
    assertEquals(tweets.get(0).getText(), firstTweet.getText());
    assertEquals(tweets.get(1).getText(), secondTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormats() {
    String[] ids = {"somestring", "somestring2"};
    List<Tweet> tweet = service.deleteTweets(ids);
  }

}