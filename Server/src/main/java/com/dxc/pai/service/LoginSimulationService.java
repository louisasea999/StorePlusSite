package com.dxc.pai.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.model.Foodcomb;
import com.dxc.pai.util.AnalogLogin;
import com.dxc.pai.util.SortChinese;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		
		//get fooddetails
		List<String> fds = new ArrayList<String>();
		
		//save data	to database	
		List<String> orderIds = orderService.insertOrder(postOrderData.toString());
		for(int i=0;i<orderIds.size();i++)
		{
			String orderData = login.GetOrderDetailByID(orderIds.get(i));
			orderService.updateOrderDetail(orderData, orderIds.get(i), fds);
		}
		updateFoodcombEveryDay(fds);
		
	}
	
	public void updateFoodcombEveryDay(List<String> fds) {
		try {
			fds.stream()
			.map(ele->JSONArray.fromObject(ele.toString()))
			.filter(ele->ele.size()>1)
			.map(ele -> JSONArrayToList(ele))
			.filter(ele->ele.size()>1)
			.peek(ele->Collections.sort(ele, new SortChinese()))
			.map(ele -> getConbinations(ele,new ArrayList<String>()))
			.reduce((li1,li2) -> mergeList(li1,li2))
			.get()
			.stream()
			.collect(Collectors.groupingBy(e->e)).forEach((key,val) -> updateComb(key.trim(), val.size()));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//util for Func updateFoodcombEveryDay()
	public List<String> JSONArrayToList(JSONArray ja) {		
		return (List<String>) ja.stream()
			.peek(ele->JSONObject.fromObject(ele))
			.map(ele -> ((JSONObject) ele).getString("foodName"))
			.peek(ele -> ele.toString().trim())
			.filter(ele -> !"打包盒".equals(ele))
			.collect(Collectors.toList());
	}
	public List<String> getConbinations(List<String> li, List<String> result){
		if(li.size() <= 1) {
			return result;
		}else {
			for(int i = 1; i < li.size(); i++) {
				result.add(li.get(0)+"+"+li.get(i));
			}
		}
		return getConbinations(li.stream().skip(1).collect(Collectors.toList()), result);
	}
	public List<String> mergeList(List<String> li1, List<String> li2){
		li1.addAll(li2);
		return li1;
	}
	public void updateComb(String conbName, int count) {
		Foodcomb fc = orderService.selectByComb(conbName);
		if(fc == null) {
			orderService.insertNewFC(conbName,count);
		}
		else{
			fc.setCount(fc.getCount()+count);
			fc.setUpdatetime(new Date());
			orderService.updateFC(fc);
		}		
	}
	//util for Func updateFoodcombEveryDay()
}
