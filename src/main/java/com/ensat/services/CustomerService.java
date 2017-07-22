package com.ensat.services;

import com.ensat.entities.Customer;

public interface CustomerService {
	public Customer findCustomerByEmail(String email);
	public void saveCustomer(Customer customer);
	Iterable<Customer> listAllCustomers();
}
