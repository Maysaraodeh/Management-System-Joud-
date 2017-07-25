package com.ensat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Order;
import com.ensat.repositories.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);

	}

	@Override
	public List<Order> countOrders() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public Iterable<Order> listAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void DeleteOrder(Integer Id) {
		orderRepository.delete(Id);

	}

	@Override
	public Order getOrderById(Integer id) {

		return orderRepository.findOne(id);
	}

	@Override
	public void updateOrder(Order order, Integer id) {
		Order uOrder = orderRepository.findOne(id);
		uOrder.setCustomerId(order.getCustomerId());
		uOrder.setDriverId(order.getDriverId());
		uOrder.setEndDate(order.getEndDate());
		uOrder.setOrderDate(order.getOrderDate());
		uOrder.setShipmentDate(order.getShipmentDate());
		uOrder.setProductId(order.getProductId());
		uOrder.setQuantity(order.getQuantity());
		uOrder.setRentSell(order.getRentSell());
		
		orderRepository.save(uOrder);
		
	}

}
