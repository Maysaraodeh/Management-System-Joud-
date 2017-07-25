package com.ensat.services;

import com.ensat.entities.OrderInfo;

public interface OrderInfoService {
	
	public void saveOrderInfo(OrderInfo orderInfo);
	Iterable<OrderInfo> listAllOrdersInfo();
	public void deleteOrderInfo(Integer id);
	public void updateOrderInfo(OrderInfo orderInfo , Integer id);
	OrderInfo getOrderInfoById(Integer id);

}
