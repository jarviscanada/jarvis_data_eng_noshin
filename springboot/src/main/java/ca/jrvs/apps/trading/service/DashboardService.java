package ca.jrvs.apps.trading.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, PositionDao positionDao,
      AccountDao accountDao, QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a traderAccountView by traderId - get trader account by id - get trader info
   * by id - create and return a traderAccountView
   *
   * @param traderId must not be null
   * @return traderAccountView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    Trader trader = traderDao.findById(traderId).get();
    Account account = findAccountByTraderId(traderId);
    return new TraderAccountView(account, trader);
  }

  /**
   * Create and return a portfolioView by traderId - get account by trader id - get positions by
   * account id - create and return a portfolioView
   *
   * @param traderId must not be null
   * @return portfolioView
   * @throws IllegalArgumentException if trader ID is null or not found
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {
    PortfolioView portfolioView = new PortfolioView();
    Account account = findAccountByTraderId(traderId);
    List<Position> positions = positionDao.findAllById(Arrays.asList(traderId));
    List<SecurityRow> securityRows = new ArrayList<>();
    for (Position position : positions) {
      Quote quote = quoteDao.findById(position.getTicker()).get();
      SecurityRow securityRow = new SecurityRow(position, quote, position.getTicker());
      securityRows.add(securityRow);
    }
    portfolioView.setSecurityRows(securityRows);
    return portfolioView;
  }

  /**
   * Finds trader account by trader ID
   *
   * @param traderId
   * @return account
   * @throws IllegalArgumentException if traderId not found
   */
  public Account findAccountByTraderId(Integer traderId) {
    return accountDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }
}
