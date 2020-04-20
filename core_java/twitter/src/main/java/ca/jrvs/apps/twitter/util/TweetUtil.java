package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {

  /**
   * creates a new Tweet with text and coordinates
   *
   * @return created tweet
   */
  public static Tweet createTweet() {
    String status = "some text #abc " + System.currentTimeMillis();
    float[] longLat = {1, -1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    return new Tweet(status, coordinates);
  }

  /**
   * creates a new tweet which is slightly different from the one created in createTweet() method to
   * avoid duplicate tweets when calling createTweet() consecutively
   *
   * @return created tweet
   */
  public static Tweet createDifferentTweet() {
    String status = "some text 2 #abc " + System.currentTimeMillis();
    float[] longLat = {1, -1};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    return new Tweet(status, coordinates);
  }

  /**
   * creates a new tweet with latitude out of range
   *
   * @return created tweet
   */
  public static Tweet createTweetWithWrongLatitude() {
    String status = "some text #abc 1586810461125";
    float[] longLat = {1, -100};
    Coordinates coordinates = new Coordinates(longLat, "Point");
    return new Tweet(status, coordinates);
  }

}
