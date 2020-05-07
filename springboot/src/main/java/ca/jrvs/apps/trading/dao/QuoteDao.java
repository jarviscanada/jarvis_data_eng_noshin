package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate((dataSource));
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * if quote exists in database, update its values, else add new quote
   *
   * @param quote new quote
   * @return saved quote
   * @throws org.springframework.dao.DataAccessException for unexpected SQL result or SQL execution
   *                                                     failure
   */
  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getId())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves new quote
   *
   * @param quote new quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * helper method that updates existing quote
   *
   * @param quote new quote
   * @return row number of updated quote
   */
  private int updateOne(Quote quote) {
    String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
        + "ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(updateSql, makeUpdateValues(quote));
  }

  /**
   * helper method to generate update values (Object[]) from Quote object for update SQL statement
   *
   * @param quote quote to generate values from
   * @return Object[] containing the values of Quote
   */
  private Object[] makeUpdateValues(Quote quote) {
    Object[] values = new Object[6];
    values[0] = quote.getLastPrice();
    values[1] = quote.getBidPrice();
    values[2] = quote.getBidSize();
    values[3] = quote.getAskPrice();
    values[4] = quote.getAskSize();
    values[5] = quote.getTicker();
    return values;
  }

  /**
   * Update all the provided quotes in the quote table
   *
   * @param quotes list of quotes
   * @param <S>
   * @return quotes list if succeeds in updating
   * @throws ResourceNotFoundException if quote ticker not found in quote table
   */
  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> quotes) {
    String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
        + "ask_size=? WHERE ticker=?";
    List<Object[]> batch = new ArrayList<>();
    quotes.forEach(quote -> {
      if (!existsById(quote.getTicker())) {
        throw new ResourceNotFoundException("Ticker not found:" + quote.getTicker());
      }
      Object[] values = new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
          quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};
      batch.add(values);
    });
    int[] rows = jdbcTemplate.batchUpdate(updateSql, batch);
    int totalRow = Arrays.stream(rows).sum();
    if (totalRow != batch.size()) {
      throw new IncorrectResultSizeDataAccessException("Number of rows ", batch.size(), totalRow);
    }
    return quotes;
  }

  /**
   * find quote, given its ticker
   *
   * @param ticker provided ticker name
   * @return quote of ticker
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    String selectSql = "SELECT * FROM quote WHERE ticker=?";
    List<Quote> listQuote = jdbcTemplate
        .query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class), ticker);
    if (!listQuote.isEmpty()) {
      return Optional.of(listQuote.get(0));
    } else {
      return Optional.empty();
    }
  }

  /**
   * checks if quote of ticker exists in quote table
   *
   * @param ticker to be searched
   * @return true if exists, false otherwise
   */
  @Override
  public boolean existsById(String ticker) {
    String countSql = "SELECT count(*) FROM quote WHERE ticker='" + ticker + "'";
    long count = jdbcTemplate.queryForObject(countSql, Long.class);
    if (count > 0L) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * gives list of all quotes in the quote table
   *
   * @return list of quotes in quote table
   */
  @Override
  public List<Quote> findAll() {
    return jdbcTemplate
        .query("SELECT * FROM quote", BeanPropertyRowMapper.newInstance(Quote.class));
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * counts number of quotes in quote table
   *
   * @return number of quotes
   */
  @Override
  public long count() {
    return jdbcTemplate.queryForObject("SELECT count(*) FROM quote", Long.class);
  }

  /**
   * deletes quote of provided ticker
   *
   * @param ticker to be deleted
   */
  @Override
  public void deleteById(String ticker) {
    if (ticker == null) {
      throw new IllegalArgumentException("Ticker name can't be null");
    }
    String deleteSql = "DELETE FROM quote WHERE ticker=?";
    jdbcTemplate.update(deleteSql, ticker);

  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * deletes all quotes in quote table
   */
  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM quote";
    jdbcTemplate.update(deleteSql);
  }
}
