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
	
	
	
	private CustomerRepository customerRepository;
	

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	@Override
	public Customer findCustomerByEmail(String email) {
		
		return customerRepository.findByEmail(email);
	}
	
	  @Override
	    public Customer getCustomerById(Integer id) {
	        return customerRepository.findOne(id);
	    }
	
	 @Override
	    public Iterable<Customer> listAllCustomers() {
	        return customerRepository.findAll();
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

	@Override
	public void updateCustomer(Customer customer , Integer id) {

		Customer c = customerRepository.findOne(id);
		c.setName(customer.getName());
		c.setAddress(customer.getAddress());
		c.setEmail(customer.getEmail());
		c.setPhone(customer.getPhone());
		customerRepository.save(c);
		
		
		
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.delete(id);
		
	}

}
