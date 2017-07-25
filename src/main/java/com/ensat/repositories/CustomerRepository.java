package com.ensat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ensat.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer , Integer> {
	
	Customer findByEmail(String email);
	
	@Query("select c.id from Customer c where c.identity = ?")
	Integer findCustomerIdByIdentity(String identity);

}
