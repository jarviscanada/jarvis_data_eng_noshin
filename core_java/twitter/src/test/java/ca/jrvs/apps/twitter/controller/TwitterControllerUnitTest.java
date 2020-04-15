package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void shouldPostTweet() {
    String status = "some text #abc 1586810461125";
    float[] longLat = {1, -1};
    String hashtag = "#abc";
    Coordinates coordinates = new Coordinates(longLat, "Point");
    Tweet newTweet = new Tweet(status, coordinates);
    String [] args ={"post", status, "1:-1"};

    when(service.postTweet(any())).thenReturn(newTweet);
    Tweet tweet = controller.postTweet(args);
    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongLatitude() {
    String status = "some text #abc 1586810461125";
    String  [] args = {"post", status, "1:-100"};

    when(service.postTweet(any())).thenThrow(IllegalArgumentException.class);
    Tweet tweet = controller.postTweet(args);
  }

  @Test
  public void shouldShowTweet() {
    String id = "1250185392676130816";
    String status = "some text #abc 1586902399442";
    float[] longLat = {1, -1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    String hashtag = "#abc";
    Tweet newTweet = new Tweet(status, coordinates);
    String [] args = {"show", id};

    when(service.showTweet(any(), any())).thenReturn(newTweet);
    Tweet tweet = controller.showTweet(args);
    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormat() {
    String id = "somestring";
    String [] args = {"show", id};

    when(service.showTweet(any(), any())).thenThrow(IllegalArgumentException.class);
    Tweet tweet = controller.showTweet(args);
  }

  @Test
  public void shouldDeleteTweet() {
    String ids = "1250180206020694019,1250173916997451778";
    String firstStatus = "some text #abc 1586901162179";
    String secondStatus = "some text #abc 1586899662404";
    float [] longLat = {1,-1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    List<Tweet> tweets = new LinkedList<>();
    tweets.add(new Tweet(firstStatus, coordinates));
    tweets.add(new Tweet(secondStatus, coordinates));
    String [] args = {"delete", ids};

    when(service.deleteTweets(any())).thenReturn(tweets);
    List<Tweet> deletedTweets = controller.deleteTweet(args);

    assertEquals(deletedTweets.get(0).getText(), firstStatus);
    assertEquals(deletedTweets.get(1).getText(), secondStatus);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormats() {
    String ids = "somestring,somestring2";
    String [] args = {"delete", ids};

    when(service.deleteTweets(any())).thenThrow(IllegalArgumentException.class);
    List<Tweet> tweet = controller.deleteTweet(args);
  }
}