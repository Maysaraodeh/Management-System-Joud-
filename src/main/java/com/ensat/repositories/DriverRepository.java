package com.ensat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ensat.entities.Driver;

@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {
	 Driver findByEmail(String email);
}
