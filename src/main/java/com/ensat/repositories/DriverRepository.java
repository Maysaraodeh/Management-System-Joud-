package com.ensat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ensat.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
	
	 Driver findByEmail(String email);
	 
	 @Query("select d.id from Driver d where d.name = ?")
		Integer findDriverIdByName(String name);
}
