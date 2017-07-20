
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
import com.ensat.entities.User;
import com.ensat.services.CustomerService;
import com.ensat.services.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	
	private CustomerService customerService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	//we dont need this for now 
	
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
	public ModelAndView createNewUser(@Valid Customer customer, BindingResult bindingResult) {
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
			modelAndView.addObject("customer", new Customer());
			modelAndView.setViewName("addCustomer");
			
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