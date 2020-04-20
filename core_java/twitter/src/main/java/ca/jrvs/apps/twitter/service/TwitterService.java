package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);

    return (Tweet) dao.create(tweet);
  }

  /**
   * Checks if text exceeds 140 characters and if the coordinates are out of range
   *
   * @param tweet
   */
  private void validatePostTweet(Tweet tweet) {
    if (tweet == null) {
      throw new NullPointerException("Tweet is empty");
    }
    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Tweet length exceeded 140 characters");
    }
    Coordinates coordinates = tweet.getCoordinates();
    if (coordinates.getLongLat()[0] <= -180 || coordinates.getLongLat()[1] >= 80) {
      throw new IllegalArgumentException("Longitude out of range");
    }
    if (coordinates.getLongLat()[1] <= -90 || coordinates.getLongLat()[0] >= 90) {
      throw new IllegalArgumentException("Latitude out of range");
    }
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateId(id);
    return (Tweet) dao.findById(id);
  }

  private void validateId(String idStr) {
    if (idStr == null) {
      throw new NullPointerException("No ID provided");
    }
    try {
      long id = Long.parseLong(idStr);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Incorrect ID format : " + idStr);
    }
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    if (ids == null) {
      throw new NullPointerException("List of IDs is empty");
    }
    List<Tweet> deletedTweets = new LinkedList<>();
    for (String id : ids) {
      validateId(id);
      deletedTweets.add(dao.deleteById(id));
    }
    return deletedTweets;
  }
}
