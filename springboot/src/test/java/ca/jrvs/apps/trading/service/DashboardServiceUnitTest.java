package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.view.SecurityRow;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DashboardServiceUnitTest {

  @Mock
  private TraderDao traderDao;
  @Mock
  private AccountDao accountDao;
  @Mock
  private PositionDao positionDao;
  @Mock
  private QuoteDao quoteDao;
  @InjectMocks
  private DashboardService dashboardService;

  private Account account;
  private Trader trader;
  private Position position;
  private Quote quote;

  @Before
  public void create() {
    account = new Account();
    account.setId(1);
    account.setTraderId(1);
    account.setAmount(100.0);

    trader = new Trader();
    trader.setFirstName("John");
    trader.setLastName("Doe");
    trader.setId(1);
    trader.setEmail("john.doe@gmail.com");
    trader.setCountry("Canada");
    trader.setDob("2000-12-12");

    position = new Position();
    position.setPosition(2);
    position.setTicker("AAPL");
    position.setAccountId(1);

    quote = new Quote();
    quote.setId("AAPL");
    quote.setBidSize(10L);
    quote.setAskSize(11L);
    quote.setTicker("AAPL");
    quote.setLastPrice(50.0);
    quote.setAskPrice(55.0);
    quote.setBidPrice(53.0);
  }

  @Test
  public void getTraderAccount() {
    TraderAccountView expectedTraderAccountView = new TraderAccountView(account, trader);
    when(traderDao.findById(any())).thenReturn(Optional.of(trader));
    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    TraderAccountView actualTraderAccountView = dashboardService.getTraderAccount(1);
    assertEquals(expectedTraderAccountView, actualTraderAccountView);
  }

  @Test
  public void getProfileViewByTraderId() {
    List<Position> positions = Arrays.asList(position);
    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    when(positionDao.findAllById(any())).thenReturn(positions);
    when(quoteDao.findById(any())).thenReturn(Optional.of(quote));
    PortfolioView expectedPortfolioView = new PortfolioView();
    List<SecurityRow> securityRows = new ArrayList<>();
    securityRows.add(new SecurityRow(position, quote, "AAPL"));
    expectedPortfolioView.setSecurityRows(securityRows);
    PortfolioView actualPortfolioView = dashboardService.getProfileViewByTraderId(1);
    assertEquals(expectedPortfolioView, actualPortfolioView);
  }

  @Test
  public void findAccountByTraderId() {
    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    assertEquals(account, accountDao.findById(1).get());
  }
}