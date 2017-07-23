package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Driver;
import com.ensat.repositories.DriverRepository;

@Service("driverService")
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver findDriverByEmail(String email) {

		return driverRepository.findByEmail(email);
	}

	@Override
	public void saveDriver(Driver driver) {

		driverRepository.save(driver);

	}

	@Override
	public Iterable<Driver> listAllDrivers() {
	
		return driverRepository.findAll();
	}
	

}
