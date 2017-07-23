package com.ensat.services;

import com.ensat.entities.Customer;

public interface CustomerService {
	public Customer findCustomerByEmail(String email);
	public void saveCustomer(Customer customer);
	Iterable<Customer> listAllCustomers();
	Customer getCustomerById(Integer id);
	public void updateCustomer(Customer customer, Integer id);
	public void deleteCustomer(Integer id);
}
