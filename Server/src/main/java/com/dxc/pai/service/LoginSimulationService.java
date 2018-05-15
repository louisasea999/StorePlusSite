package com.dxc.pai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.util.AnalogLogin;

@Service("loginSimulationService")
public class LoginSimulationService {

	@Autowired
	public OrderService orderService;
	
	public AnalogLogin login;
	
	public LoginSimulationService()
	{
		
	}
	public void Login()
	{
		login = new AnalogLogin();
	}
	
	public void requestData(Long startTime, Long endTime)
	{	
		//post request and return order data
		String postOrderData = login.GetOrderDataByTime(startTime,endTime);
		
		//save data	to database	
		List<String> orderIds = orderService.insertOrder(postOrderData.toString());
		for(int i=0;i<orderIds.size();i++)
		{
			String orderData = login.GetOrderDetailByID(orderIds.get(i));
			orderService.updateOrderDetail(orderData, orderIds.get(i));
		}	
	}
	
}
