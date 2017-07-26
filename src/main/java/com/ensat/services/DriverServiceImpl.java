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

	@Override
	public void updateDriver(Driver driver,Integer id) {
		Driver uDriver=driverRepository.findOne(id);
		uDriver.setName(driver.getName());
		uDriver.setAddress(driver.getAddress());
		uDriver.setEmail(driver.getEmail());
		uDriver.setPhone(driver.getPhone());
		uDriver.setVehicleNo(driver.getVehicleNo());
		uDriver.setDriverIdentity(driver.getIdentity());
		driverRepository.save(uDriver);
		
	}

	@Override
	public Driver findDriverById(Integer id) {
		
		return driverRepository.findOne(id);
	}

	@Override
	public void deleteDriver(Integer id) {
		
		driverRepository.delete(id);
		
	}

	@Override
	public Integer findDriverIdByName(String name) {
		Integer id = driverRepository.findDriverIdByName(name);
		return id;
	}
	

}
