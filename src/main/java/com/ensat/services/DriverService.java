package com.ensat.services;

import com.ensat.entities.Driver;

public interface DriverService {
	public Driver findDriverByEmail(String email);
	public void saveDriver(Driver driver);
	Iterable<Driver> listAllDrivers();
	public void updateDriver(Driver driver,Integer id);
	Driver findDriverById(Integer id);
	public void deleteDriver(Integer id);
	
	Integer findDriverIdByName(String name);
	
	
	
}
