package com.dxc.pai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.OrderTableMapper;
import com.dxc.pai.model.OrderTable;
import com.dxc.pai.util.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("orderService")
public class OrderService {
	
	private String serName;
	public OrderService() {}
	public OrderService(String serName) {
		this.serName = serName;
	}
	
	public String getSer() {
		return this.serName;
	}
	
	@Autowired
	private OrderTableMapper orderTableMapper;
	
	public void updateOrderDetail(String orderData, String id)
	{		
		JSONObject orderDetail = (JSONObject) JSONObject.fromObject(orderData.toString()).get("data");
		JSONArray foodDetails = JSONArray.fromObject( orderDetail.getJSONObject("foodDetals").get("foodDetails"));		
		JSONArray foodData = new JSONArray();
		OrderTable order = orderTableMapper.selectByPrimaryKey(id);
		if(order!=null)
		{
			for(int i=0;i<foodDetails.size();i++)
			{
				JSONObject foodDetail=JSONObject.fromObject(foodDetails.get(i));
				JSONObject food = new JSONObject();
				food.put("foodName", foodDetail.get("foodName"));
				food.put("amtPrice", foodDetail.get("amtPrice"));		
				foodData.add(food);
			}
			order.setFooddetails(foodData.toString());
			System.out.println(foodData.toString());
			orderTableMapper.updateByPrimaryKey(order);
		}
	}
	
	
	public List<String> insertOrder(String totalOrder)
	{
		List<String> orderIds = new ArrayList<String>();
		JSONArray orders = JSONArray.fromObject(JSONObject.fromObject(totalOrder.toString()).get("data"));
		
		for(int i=0;i<orders.size();i++)
		{
			JSONObject orderJsonObj=JSONObject.fromObject(orders.get(i));
			OrderTable order = new OrderTable();
			order.setId(orderJsonObj.getString("id"));
			order.setOpentime(CommonTool.strToUtilDate(orderJsonObj.getString("openTime")));
			order.setTablecode(orderJsonObj.getString("tableCode"));
			
			if(orderTableMapper.selectByPrimaryKey(order.getId())==null)			
			{
				orderTableMapper.insert(order);
				
			}
			else
			{
				System.out.println("id:"+order.getId()+"已存在");
			}
			orderIds.add(order.getId());
		}
		return orderIds;
	}

	}

