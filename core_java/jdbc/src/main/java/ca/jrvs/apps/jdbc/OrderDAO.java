package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

  private final static String GET_ONE = "SELECT "
      + "c.first_name, c.last_name, c.email, "
      + "o.order_id, o.creation_date, o.total_due, o.status, "
      + "s.first_name, s.last_name, s.email, ol.quantity, "
      + "p.code, p.name, p.size, p.variety, p.price "
      + "from orders o "
      + "join customer c on o.customer_id = c.customer_id "
      + "join salesperson s on o.salesperson_id=s.salesperson_id "
      + "join order_item ol on ol.order_id=o.order_id "
      + "join product p on ol.product_id = p.product_id "
      + "where o.order_id = ?";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order order = new Order();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      List<OrderItem> orderItems = new ArrayList<>();
      boolean firstItem = true;
      while (resultSet.next()) {
        if (firstItem) {
          order.setCustomerFirstName(resultSet.getString(1));
          order.setCustomerLastName(resultSet.getString(2));
          order.setCustomerEmail(resultSet.getString(3));
          order.setId(resultSet.getLong(4));
          order.setCreationDate(resultSet.getDate(5));
          order.setTotalDue(resultSet.getBigDecimal(6));
          order.setStatus(resultSet.getString(7));
          order.setSalespersonFirstName(resultSet.getString(8));
          order.setSalespersonLastName(resultSet.getString(9));
          order.setSalespersonEmail(resultSet.getString(10));
          firstItem = false;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProductQuantity(resultSet.getInt(11));
        orderItem.setProductCode(resultSet.getString(12));
        orderItem.setProductName(resultSet.getString(13));
        orderItem.setProductSize(resultSet.getInt(14));
        orderItem.setProductVariety(resultSet.getString(15));
        orderItem.setProductPrice(resultSet.getBigDecimal(16));
        orderItems.add(orderItem);
      }
      order.setOrderItems(orderItems);
    } catch (SQLException e) {
      //e.printStackTrace();
      throw new RuntimeException();
    }
    return order;
  }

  @Override
  public List<Order> findAll() {
    return null;
  }

  @Override
  public Order update(Order dto) {
    return null;
  }

  @Override
  public Order create(Order dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
