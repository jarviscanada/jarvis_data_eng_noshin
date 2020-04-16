package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  private TwitterHttpHelper twitterHttpHelper;
  private URI uri;
  private HttpResponse response;

  @Before
  public void setUp() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
  }

  @Test
  public void whenURIisCorrectHttpPostMethodShouldCreateNewPost() throws Exception {
    String status = "some text #abc" + System.currentTimeMillis();
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    uri = new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));
    response = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test(expected = RuntimeException.class)
  public void whenURIisIncorrectHttpPostMethodShouldThrowRuntimeException() throws Exception {
    String status = "TestingTwitterHttpHelperPost" + System.currentTimeMillis();
    uri = new URI(
        "https://api.twiter.com/1.1/statuses/update.json?status=" + status);
    response = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void whenURIisCorrectHttpGetMethodShouldRetrievePost() throws Exception {
    uri = new URI("https://api.twitter.com/1.1/statuses/show.json?id=1247691063831207936");
    response = twitterHttpHelper.httpGet(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test(expected = RuntimeException.class)
  public void whenURIisIncorrectHttpGetMethodShouldThrowRuntimeException() throws Exception {
    uri = new URI("https://api.twtter.com/1.1/statuses/show.json?id=1247691063831207936");
    response = twitterHttpHelper.httpGet(uri);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}