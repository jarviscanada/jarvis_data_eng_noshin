package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
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
    Tweet newTweet = TweetUtil.createTweet();

    when(dao.create(any())).thenReturn(newTweet);
    Tweet tweet = service.postTweet(newTweet);

    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongLatitude() {
    Tweet newTweet = TweetUtil.createTweetWithWrongLatitude();

    Tweet tweet = service.postTweet(newTweet);
  }

  @Test
  public void shouldShowTweet() {
    String id = "1250185392676130816";
    Tweet newTweet = TweetUtil.createTweet();

    when(dao.findById(anyString())).thenReturn(newTweet);
    Tweet tweet = service.showTweet(id, null);

    assertEquals(tweet.getText(), newTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormat() {
    String id = "somestring";

    Tweet tweet = service.showTweet(id, null);
  }

  @Test
  public void shouldDeleteTweets() {
    String[] ids = {"1250185392676130816"};
    Tweet firstTweet = TweetUtil.createTweet();
    List<Tweet> tweets = new LinkedList<>();
    tweets.add(firstTweet);

    when(dao.deleteById((anyString()))).thenReturn(firstTweet);
    List<Tweet> tweet = service.deleteTweets(ids);

    assertEquals(tweets.get(0).getText(), firstTweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgExcpForWrongIdFormats() {
    String[] ids = {"somestring", "somestring2"};

    List<Tweet> tweet = service.deleteTweets(ids);
  }

}