package com.ensat.controllers;

import com.ensat.entities.Customer;
import com.ensat.entities.Driver;
import com.ensat.entities.Order;
import com.ensat.entities.OrderInfo;
import com.ensat.entities.Product;
import com.ensat.services.CustomerService;
import com.ensat.services.DriverService;
import com.ensat.services.OrderInfoService;
import com.ensat.services.OrderService;
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
	private DriverService driverService;
	private OrderService orderService;
	private OrderInfoService orderInfoService;

	@Autowired
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

//	@RequestMapping(value = "/reports/{orderId}", method = RequestMethod.GET)
//	public String listReportsByOrderId(Model model, @PathVariable Integer orderId) {
//		model.addAttribute("reports", reportService.getReportByOrderId(orderId));
//		return "report";
//	}

	@RequestMapping(value = "/reports/{id}", method = RequestMethod.GET)
	public String listAllReports(Model model,@PathVariable Integer id) {
		model.addAttribute("orderinfo", orderInfoService.getOrderInfoById(id));
		return "report";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String listAllCustomers(Model model) {
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
		customerService.updateCustomer(customer, id);
		return "redirect:/customers";
	}

	@RequestMapping("customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}

	@RequestMapping(value = "/drivers", method = RequestMethod.GET)
	public String listDriver(Model model) {
		model.addAttribute("Drivers", driverService.listAllDrivers());
		return "drivers";

	}

	@RequestMapping("updateDriver/edit/{id}")
	public String updateDriver(@PathVariable Integer id, Model model) {
		model.addAttribute("driver", driverService.findDriverById(id));
		return "updateDriver";
	}

	@RequestMapping(value = "updateDriver", method = RequestMethod.POST)
	public String updatedriver(@RequestParam Integer id, Driver driver) {
		driverService.updateDriver(driver, id);
		return "redirect:/drivers ";
	}

	@RequestMapping("driver/delete/{id}")
	public String DeleteDriver(@PathVariable Integer id) {
		driverService.deleteDriver(id);
		return "redirect:/drivers";
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.GET)
	public String newOrder(Model model) {

		model.addAttribute("order", new Order());
		model.addAttribute("driver", new Driver());
		model.addAttribute("customer", new Customer());
		model.addAttribute("product", new Product());
		return "newOrder";

	}

	@RequestMapping(value = "newOrderSubmit", method = RequestMethod.POST)
	public String newOrderSubmit(Customer customer, Order order, Driver driver , Product product) {

		Order newOrder = new Order();
		OrderInfo orderInfo = new OrderInfo();
		Customer orderInfoCustomer = customerService
				.getCustomerById(customerService.getCustomerIdByIdentity(customer.getIdentity()));
		Product orderInfoProduct = productService.getProductById(productService.getProductIdByName(product.getProductName()));
		Driver orderInfoDriver = driverService.findDriverById(driverService.findDriverIdByName(driver.getName()));
		orderInfo.setCustomerName(orderInfoCustomer.getName());
		orderInfo.setDriverName(orderInfoDriver.getName());
		orderInfo.setOrderDate(order.getOrderDate());
		orderInfo.setEndDate(order.getEndDate());
		orderInfo.setShipmentDate(order.getShipmentDate());
		orderInfo.setProductName(orderInfoProduct.getProductName());
		orderInfo.setQuantity(product.getQuantity());
		orderInfo.setRentSell(order.getRentSell());

		newOrder.setCustomerId(customerService.getCustomerIdByIdentity(customer.getIdentity()));
		newOrder.setDriverId(driverService.findDriverIdByName(driver.getName()));
		newOrder.setProductId(productService.getProductIdByName(product.getProductName()));
		newOrder.setOrderDate(order.getOrderDate());
		newOrder.setEndDate(order.getEndDate());
		newOrder.setShipmentDate(order.getShipmentDate());
		newOrder.setRentSell(order.getRentSell());
		newOrder.setQuantity(product.getQuantity());

		orderService.saveOrder(newOrder);
		orderInfoService.saveOrderInfo(orderInfo);

		return "redirect:/orders";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String listOrders(Model model) {
		model.addAttribute("orders", orderInfoService.listAllOrdersInfo());
		return "orders";
	}

	@RequestMapping("/orders/delete/{id}")
	public String deleteOrder(@PathVariable Integer id) {
		orderService.DeleteOrder(id);
		orderInfoService.deleteOrderInfo(id);
		return "redirect:/orders";
	}
	
	
	
	@RequestMapping("order/edit/{id}")
	public String editOrder(@PathVariable Integer id, Model model) {
		model.addAttribute("editedOrder", orderService.getOrderById(id));
		return "updateOrder";
	}
	

	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		System.out.println("Returning products:");
		return "products";
	}

	@RequestMapping("product/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}
	@RequestMapping(value = "updateproduct", method = RequestMethod.POST)
	public String updateProduct(Product product, @RequestParam Integer id){
		productService.updateProduct(product,id);
		return"redirect:/products";
	}

	@RequestMapping("/addproduct")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct";
	}

	@RequestMapping(value = "addproduct", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}

	@RequestMapping("product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
