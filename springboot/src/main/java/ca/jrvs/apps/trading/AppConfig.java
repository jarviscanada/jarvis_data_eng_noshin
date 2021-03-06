package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

  private Logger logger = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public MarketDataConfig marketDataConfig() {
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

    return marketDataConfig;
  }

  @Bean
  public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    return cm;
  }

  @Bean
  public DataSource dataSource() {
    String url;
    String user;
    String password;
    if (System.getenv("RDS_HOSTNAME") != null) {
      url = "jdbc:postgresql://" + System.getenv("RDS_HOSTNAME")
          + ":" + System.getenv("RDS_PORT")
          + "/" + System.getenv("RDS_DB_NAME");
      user = System.getenv("RDS_USERNAME");
      password = System.getenv("RDS_PASSWORD");
    } else {
      url = System.getenv("PSQL_URL");
      user = System.getenv("PSQL_USER");
      password = System.getenv("PSQL_PASSWORD");
    }
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }

}
