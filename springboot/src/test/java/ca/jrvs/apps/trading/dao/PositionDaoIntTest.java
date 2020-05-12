package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class PositionDaoIntTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Autowired
  private PositionDao positionDao;

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private QuoteDao quoteDao;

  private Position firstPosition = new Position();
  private Position secondPosition = new Position();
  private Position thirdPosition = new Position();
  private SecurityOrder firstSecurityOrder = new SecurityOrder();
  private SecurityOrder secondSecurityOrder = new SecurityOrder();
  private SecurityOrder thirdSecurityOrder = new SecurityOrder();
  private SecurityOrder fourthSecurityOrder = new SecurityOrder();
  private Trader firstTrader = new Trader();
  private Trader secondTrader = new Trader();
  private Account firstAccount = new Account();
  private Account secondAccount = new Account();
  private Quote firstSavedQuote = new Quote();
  private Quote secondSavedQuote = new Quote();

  @Before
  public void insert() {
    firstTrader.setFirstName("John");
    firstTrader.setLastName("Doe");
    firstTrader.setCountry("Canada");
    firstTrader.setEmail("john.doe@gmail.com");
    firstTrader.setDob(LocalDate.of(2000, 12, 12));
    traderDao.save(firstTrader);
    secondTrader.setFirstName("Jane");
    secondTrader.setLastName("Smith");
    secondTrader.setCountry("Canada");
    secondTrader.setEmail("jane.smith@gmail.com");
    secondTrader.setDob(LocalDate.of(2000, 1, 1));
    traderDao.save(secondTrader);

    firstAccount.setTraderId(1);
    firstAccount.setAmount(1000.0);
    accountDao.save(firstAccount);
    secondAccount.setTraderId(2);
    secondAccount.setAmount(1100.32);
    accountDao.save(secondAccount);

    firstSavedQuote.setAskPrice(10d);
    firstSavedQuote.setAskSize(10L);
    firstSavedQuote.setBidPrice(10.2d);
    firstSavedQuote.setBidSize(10L);
    firstSavedQuote.setId("aapl");
    firstSavedQuote.setLastPrice(10.1d);
    quoteDao.save(firstSavedQuote);
    secondSavedQuote.setAskPrice(11d);
    secondSavedQuote.setAskSize(11L);
    secondSavedQuote.setBidPrice(11.2d);
    secondSavedQuote.setBidSize(11L);
    secondSavedQuote.setId("amzn");
    secondSavedQuote.setLastPrice(11.1d);
    quoteDao.save(secondSavedQuote);

    firstSecurityOrder.setAccountId(1);
    firstSecurityOrder.setTicker("aapl");
    firstSecurityOrder.setStatus("FILLED");
    firstSecurityOrder.setSize(55);
    firstSecurityOrder.setPrice(35.23);
    firstSecurityOrder.setNotes("Some notes");
    securityOrderDao.save(firstSecurityOrder);
    secondSecurityOrder.setAccountId(1);
    secondSecurityOrder.setTicker("aapl");
    secondSecurityOrder.setStatus("FILLED");
    secondSecurityOrder.setSize(45);
    secondSecurityOrder.setPrice(46.23);
    secondSecurityOrder.setNotes("Some other notes");
    securityOrderDao.save(secondSecurityOrder);
    thirdSecurityOrder.setAccountId(1);
    thirdSecurityOrder.setTicker("amzn");
    thirdSecurityOrder.setStatus("FILLED");
    thirdSecurityOrder.setSize(33);
    thirdSecurityOrder.setPrice(46.23);
    thirdSecurityOrder.setNotes("Some other notes");
    securityOrderDao.save(thirdSecurityOrder);
    fourthSecurityOrder.setAccountId(2);
    fourthSecurityOrder.setTicker("amzn");
    fourthSecurityOrder.setStatus("FILLED");
    fourthSecurityOrder.setSize(22);
    fourthSecurityOrder.setPrice(46.23);
    fourthSecurityOrder.setNotes("Some other notes");
    securityOrderDao.save(fourthSecurityOrder);

    firstPosition.setAccountId(1);
    firstPosition.setTicker("aapl");
    firstPosition.setPosition(100);
    secondPosition.setAccountId(1);
    secondPosition.setTicker("amzn");
    secondPosition.setPosition(33);
    thirdPosition.setAccountId(2);
    thirdPosition.setTicker("amzn");
    thirdPosition.setPosition(22);
  }

  @After
  public void remove() {
    securityOrderDao.deleteById(firstSecurityOrder.getId());
    securityOrderDao.deleteById(secondSecurityOrder.getId());
  }

  @Test
  public void existsById() {
    //found
    assertTrue(positionDao.existsById(1));
    //not found
    assertFalse(positionDao.existsById(6));
  }

  @Test
  public void existsByTicker() {
    //found
    assertTrue(positionDao.existsByTicker("aapl"));
    //not found
    assertFalse(positionDao.existsByTicker("googl"));
  }

  @Test
  public void findAll() {
    Position[] expectedPositions = new Position[]{firstPosition, secondPosition, thirdPosition};
    Position[] actualPositions = new Position[3];
    positionDao.findAll().toArray(actualPositions);

    assertArrayEquals(expectedPositions, actualPositions);
  }

  @Test
  public void findAllById() {
    Position[] expectedPositions = new Position[]{firstPosition, secondPosition, thirdPosition};
    Position[] actualPositions = new Position[3];
    positionDao.findAllById(Arrays.asList(1, 2)).toArray(actualPositions);
    System.out.println(positionDao.findAll().toString());
    assertArrayEquals(expectedPositions, actualPositions);
  }

  @Test
  public void findAllByTickers() {
    Position[] expectedPositions = new Position[]{firstPosition, secondPosition, thirdPosition};
    Position[] actualPositions = new Position[3];
    positionDao.findAllByTickers(Arrays.asList("aapl", "amzn")).toArray(actualPositions);

    assertArrayEquals(expectedPositions, actualPositions);
  }

  @Test
  public void count() {
    assertEquals(3, positionDao.count());
  }
}