package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order> {
    
	public static final Logger LOGGER = LogManager.getLogger();
	List<Item> itemList = new ArrayList<>();

	
	
	@Override
	 public Order create(Order order) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO orders(fk_cid) VALUES (?)");) {
            statement.setLong(1, order.getOrderCustomer().getId());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }
	
	public Order addItem(Order order, Long orderId, Long itemId) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderItem(fk_oid, fk_iid) VALUES (?,?)");) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();
			return readLatest();
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}

	 public Order read(Long id) {
		 try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		         PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
		     statement.setLong(1, id);
		     ResultSet resultSet = statement.executeQuery();
		     resultSet.next();
		     return modelFromResultSet(resultSet);
	     } catch (Exception e) {
		     LOGGER.debug(e);
		     LOGGER.error(e.getMessage());
	     }
		     return null;
	  }			
			
	@Override		
	public List<Order> readAll() {
		List<Order> orders = new ArrayList<>();		
		
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
             //   ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
        		ResultSet resultSet = statement.executeQuery("SELECT orders.fk_cid, customers.first_name, customers.surname FROM Customers INNER JOIN Items ON orders.fk_cid=Customers.id");) {
//        	SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
//        	FROM Orders
//        	INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;	
        	
            while (resultSet.next()) {
            	orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return orders;
	
	
	}
	
	  public Order readLatest() {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
	            resultSet.next();
	            return modelFromResultSet(resultSet);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }


	@Override
	public Order update(Order order) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("UPDATE orders SET fk_cid = ?,  WHERE id = ?");) {
			   statement.setLong(1, order.getOrderCustomer().getId());
	            statement.executeUpdate();
	            return read(order.getId());
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }		return null;
	}


	@Override
	 public int delete(long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
           // return statement.executeUpdate("delete from orderItem where fk_oid  = " + id);
              return statement.executeUpdate("DELETE FROM orderitem WHERE f_oid = "+ id + "; DELETE FROM orders WHERE id = " + id + ";");

        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }


	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long fk_cid = resultSet.getLong("fk_cid");
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.read(fk_cid);
		return new Order(id, null, customer, null);
	}

	
	
	
	
	
	
	
	
	
}
