package com.ensat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//    @RequestMapping("/")
//    String index() {
//        return "login";
//    } dksckdcscnskc
	
	

	
	@RequestMapping("/orders")
	String listAllOrders(){
		return "orders";
	}
	
	
	
	

}
