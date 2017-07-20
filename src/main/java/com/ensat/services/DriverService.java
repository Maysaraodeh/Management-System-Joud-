package com.ensat.services;

import com.ensat.entities.Driver;

public interface DriverService {
	public Driver findDriverByEmail(String email);
	public void saveDriver(Driver driver);
}
