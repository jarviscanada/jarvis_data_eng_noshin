package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.time.LocalDate;
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
public class TraderAccountServiceIntTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  private Trader firstTrader = new Trader();
  private Trader secondTrader = new Trader();
  private Account firstAccount = new Account();
  private TraderAccountView savedView;

  @Test
  public void testCreateTraderAndAccount() {
    //when email is null
    firstTrader.setFirstName("John");
    firstTrader.setLastName("Doe");
    firstTrader.setCountry("Canada");
    firstTrader.setDob(LocalDate.of(2000, 12, 12));

    expectedException.expect(IllegalArgumentException.class);
    savedView = traderAccountService.createTraderAndAccount(firstTrader);

    //when email is non-null
    firstTrader.setEmail("john.doe@gmail.com");
    savedView = traderAccountService.createTraderAndAccount(firstTrader);
    firstAccount.setTraderId(1);
    firstAccount.setAmount(0.0);
    TraderAccountView expectedView = new TraderAccountView(firstAccount, firstTrader);

    assertEquals(savedView, expectedView);
  }

  @Test
  public void testDeleteTraderById() {
    //when amount and open positions are 0
    firstTrader.setFirstName("John");
    firstTrader.setLastName("Doe");
    firstTrader.setCountry("Canada");
    firstTrader.setDob(LocalDate.of(2000, 12, 12));
    firstTrader.setEmail("john.doe@gmail.com");
    savedView = traderAccountService.createTraderAndAccount(firstTrader);
    traderAccountService.deleteTraderById(savedView.getTrader().getId());

    assertEquals(0, traderDao.count());
    assertEquals(0, accountDao.count());

    //when amount is 100.0
    secondTrader.setFirstName("Jane");
    secondTrader.setLastName("Smith");
    secondTrader.setCountry("Canada");
    secondTrader.setEmail("jane.smith@gmail.com");
    secondTrader.setDob(LocalDate.of(2000, 1, 1));
    savedView = traderAccountService.createTraderAndAccount(secondTrader);
    savedView.getAccount().setAmount(100.0);
    accountDao.updateOne(savedView.getAccount());

    expectedException.expect(IllegalArgumentException.class);
    traderAccountService.deleteTraderById(savedView.getTrader().getId());
    assertNotEquals(0, traderDao.count());
    assertNotEquals(0, accountDao.count());
  }

  @Test
  public void testDeposit() {
    secondTrader.setFirstName("Jane");
    secondTrader.setLastName("Smith");
    secondTrader.setCountry("Canada");
    secondTrader.setEmail("jane.smith@gmail.com");
    secondTrader.setDob(LocalDate.of(2000, 1, 1));
    savedView = traderAccountService.createTraderAndAccount(secondTrader);
    savedView.getAccount().setAmount(100.0);

    //when fund is 0.0
    expectedException.expect(IllegalArgumentException.class);
    traderAccountService.deposit(savedView.getTrader().getId(), 0.0);

    //when fund is greater than 0.0
    Account secondAccount = traderAccountService.deposit(savedView.getTrader().getId(), 23.0);
    assertEquals(java.util.Optional.of(123.0), secondAccount.getAmount());
  }

  @Test
  public void testWithdraw() {
    secondTrader.setFirstName("Jane");
    secondTrader.setLastName("Smith");
    secondTrader.setCountry("Canada");
    secondTrader.setEmail("jane.smith@gmail.com");
    secondTrader.setDob(LocalDate.of(2000, 1, 1));
    savedView = traderAccountService.createTraderAndAccount(secondTrader);
    savedView.getAccount().setAmount(100.0);

    //when fund is greater than balance
    expectedException.expect(IllegalArgumentException.class);
    traderAccountService.withdraw(savedView.getTrader().getId(), 300.0);

    //when fund is greater than 0.0 and less than balance
    Account secondAccount = traderAccountService.deposit(savedView.getTrader().getId(), 20.0);
    assertEquals(java.util.Optional.of(80.0), secondAccount.getAmount());
  }
}