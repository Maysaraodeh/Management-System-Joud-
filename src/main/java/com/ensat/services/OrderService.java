package com.ensat.services;

import java.util.List;

import com.ensat.entities.Order;

public interface OrderService {
	
	
	public void saveOrder(Order order);
	
	List <Order> countOrders();
	
	Iterable <Order> listAllOrders(); 
	
	public void DeleteOrder(Integer Id);
	
	Order getOrderById(Integer id);
	
	public void updateOrder(Order order , Integer id);

}
