package com.ensat.services;

import java.util.List;

import com.ensat.entities.Customer;
import com.ensat.entities.Order;

public interface CustomerService {
	public Customer findCustomerByEmail(String email);
	public void saveCustomer(Customer customer);
	Iterable<Customer> listAllCustomers();
	Customer getCustomerById(Integer id);
	public void updateCustomer(Customer customer, Integer id);
	public void deleteCustomer(Integer id);
	int getCustomerIdByIdentity(String identity);
	
	Iterable<Customer> findCustomersWithOrder(List<Order> order);
}
