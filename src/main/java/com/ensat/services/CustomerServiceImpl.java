package com.ensat.services;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Customer;
import com.ensat.repositories.CustomerRepository;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired 
	private CustomerRepository customerRepository;

	@Override
	public Customer findCustomerByEmail(String email) {
		
		return customerRepository.findByEmail(email);
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();

		Date date = new Date(currentDate.getTime());
		SimpleDateFormat ft = 
			      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		customer.setRegestraionDate(ft.format(date));
		customerRepository.save(customer);
	}

}
