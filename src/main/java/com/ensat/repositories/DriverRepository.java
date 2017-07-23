package com.ensat.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ensat.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
	
	 Driver findByEmail(String email);
}
