package com.ensat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ensat.entities.OrderInfo;


public interface OrderInfoRepository extends CrudRepository<OrderInfo , Integer> {
	
	

}
