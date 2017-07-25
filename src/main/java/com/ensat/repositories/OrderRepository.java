package com.ensat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ensat.entities.Order;

public interface OrderRepository extends CrudRepository<Order , Integer>{
	
	

}
