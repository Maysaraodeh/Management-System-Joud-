
package com.ensat.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ensat.entities.Customer;
import com.ensat.entities.Driver;
import com.ensat.entities.User;
import com.ensat.services.CustomerService;
import com.ensat.services.DriverService;
import com.ensat.services.UserService;


@Controller
public class LoginAndRegistrationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DriverService driverService;
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value="/addCustomer", method = RequestMethod.GET)
	public ModelAndView registerCustomer(){
		ModelAndView modelAndView = new ModelAndView();
		Customer customer = new Customer();
		modelAndView.addObject("customer", customer);
		modelAndView.setViewName("addCustomer");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ModelAndView createNewCustomer(@Valid Customer customer, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Customer customerExists = customerService.findCustomerByEmail(customer.getEmail());
		if (customerExists != null) {
			bindingResult
					.rejectValue("email", "error.customer",
							"the customer is Already exist");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("addCustomer");
		} else {
			customerService.saveCustomer(customer);
			modelAndView.addObject("successMessage", "Customer has been Added");
			modelAndView.addObject("redirectMessage", "Go to Customers page");
			modelAndView.addObject("customer", new Customer());
			modelAndView.setViewName("addCustomer");
			
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/addDriver", method = RequestMethod.GET)
	public ModelAndView registerDriver(){
		ModelAndView modelAndView = new ModelAndView();
		Driver driver = new Driver();
		modelAndView.addObject("driver", driver);
		modelAndView.setViewName("addDriver");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addDriver", method = RequestMethod.POST)
	public ModelAndView createNewDriver(@Valid Driver driver, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Driver driverExists = driverService.findDriverByEmail(driver.getEmail());
		if (driverExists != null) {
			bindingResult
					.rejectValue("email", "error.driver",
							"the Driver is Already exist");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("addDriver");
		} else {
			driverService.saveDriver(driver);
			modelAndView.addObject("successMessage", "Driver has been Added");
			modelAndView.addObject("driver", new Driver());
			modelAndView.setViewName("addDriver");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"the Admin is Already exist");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Admin has been Added");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/AdminPage/", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.setViewName("AdminPage");
		return modelAndView;
	}
	

}