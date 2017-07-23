package com.ensat.controllers;

import com.ensat.entities.Customer;
import com.ensat.entities.Product;
import com.ensat.services.CustomerService;
import com.ensat.services.ProductService;
import com.ensat.services.ReportService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrudController {

	private ProductService productService;
	private ReportService reportService;
	private CustomerService customerService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@Autowired
	public void setCustomerService(CustomerService customerService){
		this.customerService = customerService;
	}
	
	
	
	@RequestMapping(value ="/reports/{orderId}" , method = RequestMethod.GET)
	public String listReportsByOrderId(Model model ,@PathVariable Integer orderId ){
		model.addAttribute("reports", reportService.getReportByOrderId(orderId));
		return "report";
	}
	
	@RequestMapping(value ="/reports" , method = RequestMethod.GET)
	public String listAllReports(Model model){
		model.addAttribute("reports", reportService.listAllReports());
		return "report";
	}
	
	@RequestMapping(value ="/customers" , method = RequestMethod.GET)
	public String listAllCustomers(Model model){
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customers";
	}
	
	
	@RequestMapping("customer/edit/{id}")
	public String editCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "updatecustomer";
	}
	
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String saveCustomer(Customer customer, @RequestParam Integer id) {
		customerService.updateCustomer(customer , id);
		return "redirect:/customers";
	}
	
	@RequestMapping("customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		System.out.println("Returning products:");
		return "products";
	}
	

	@RequestMapping("product/{id}")
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productshow";
	}

	
	@RequestMapping("product/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}

	@RequestMapping("product/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}

	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productService.saveProduct(product);
		return "redirect:/product/" + product.getId();
	}

	@RequestMapping("product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
