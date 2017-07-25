package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.OrderInfo;
import com.ensat.repositories.OrderInfoRepository;

@Service("orderTestService")
public class OrderInfoServiceImpl implements OrderInfoService {

	private OrderInfoRepository orderInfoRepository;

	@Autowired
	public void setOrderInfoRepository(OrderInfoRepository orderInfoRepository) {
		this.orderInfoRepository = orderInfoRepository;
	}

	@Override
	public void saveOrderInfo(OrderInfo orderTest) {

		orderInfoRepository.save(orderTest);
	}

	@Override
	public Iterable<OrderInfo> listAllOrdersInfo() {

		return orderInfoRepository.findAll();
	}

	@Override
	public void deleteOrderInfo(Integer id) {
		orderInfoRepository.delete(id);
		
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo, Integer id) {
		OrderInfo uOrderInfo = orderInfoRepository.findOne(id);
		uOrderInfo.setCustomerName(orderInfo.getCustomerName());
		uOrderInfo.setDriverName(orderInfo.getDriverName());
		uOrderInfo.setEndDate(orderInfo.getEndDate());
		uOrderInfo.setOrderDate(orderInfo.getOrderDate());
		uOrderInfo.setShipmentDate(orderInfo.getShipmentDate());
		uOrderInfo.setProductName(orderInfo.getProductName());
		uOrderInfo.setQuantity(orderInfo.getQuantity());
		orderInfo.setRentSell(orderInfo.getRentSell());
		
		orderInfoRepository.save(uOrderInfo);
		
	}

	@Override
	public OrderInfo getOrderInfoById(Integer id) {
		return orderInfoRepository.findOne(id);
	}
	
	
	

}
