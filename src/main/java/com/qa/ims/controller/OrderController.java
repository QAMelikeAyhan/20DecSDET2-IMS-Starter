package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;

	public class OrderController implements ICrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDao orderDao;
	private JavaUtilities javaUtilities;
	
	public OrderController(OrderDao orderDao, JavaUtilities javaUtilities) {
		super();
		this.orderDao = orderDao;
		this.javaUtilities = javaUtilities;
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> items = orderDao.readAll();
		for (Order item : items ) {
			LOGGER.info(item);
		}
		return items;
	}
	
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer ID:");
		Long customerId = javaUtilities.getLong();
		Order order = orderDao.create(new Order(new Customer(customerId, null, null), 0.0));       
		return order;
	}
	
	@Override
	public Order update() {
		LOGGER.info("Please  enter the ID of the order you would like to update:");
		Long orderId = javaUtilities.getLong();
		
		LOGGER.info("Would you like to ADD or DELETE?");
		String response = javaUtilities.getString();
			
		if (response.equals("add"))
		{
			LOGGER.info("What item Id would you like to add?");
			List<Long> itemList = new ArrayList<>();
			Long itemId = javaUtilities.getLong();
			
			String input = "";
			while(!input.equals("exit"))
			{
				LOGGER.info("Type exit to exit");
				input = javaUtilities.getString();
				orderDao.addItem(null, orderId, itemId);	
			}
		}
//		if (response.equals("delete"))
//		{
//			LOGGER.info("What item Id would you like to delete?");
//			
//		}
        LOGGER.info("Order Updated");
		return null;
	}
	
	public int delete() {
		Long orderId = javaUtilities.getLong();
		Long itemId = javaUtilities.getLong();
		LOGGER.info("Please enter the ID of the order you woud like to delete:");
		Long Id = javaUtilities.getLong();
		orderDao.addItem(null, orderId, itemId);
		return 0;
	}
	
	
	
	
	}

	