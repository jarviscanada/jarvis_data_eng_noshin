package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MarketDataDaoTest {

  private MarketDataDao dao;

  @Before
  public void init(){
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

    dao = new MarketDataDao(cm, marketDataConfig);
  }

  @Test
  public void shouldFindIexQuoteByTicker() {
    String ticker = "GOOGL";
    IexQuote iexQuote = dao.findById(ticker).get();

    assertEquals(ticker, iexQuote.getSymbol());
  }

  @Test
  public void shouldfindListOfIexQuotesByTickers() {
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("GOOGL", "AMZN"));

    assertEquals(2, quoteList.size());
    assertEquals("GOOGL", quoteList.get(0).getSymbol());

  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenInvalidTicker(){
        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL","FB2"));
  }

}