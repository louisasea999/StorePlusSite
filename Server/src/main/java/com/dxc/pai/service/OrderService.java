package com.dxc.pai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.FoodcombMapper;
import com.dxc.pai.dao.OrderTableMapper;
import com.dxc.pai.model.Foodcomb;
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
	
	@Autowired
	private FoodcombMapper foodcombMapper;
	
	public void updateOrderDetail(String orderData, String id, List<String> fds)
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
			fds.add(order.getFooddetails());
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
	
	
	public Foodcomb selectByComb(String comb) {
		return foodcombMapper.selectByComb(comb);
	}
	public void updateFC(Foodcomb fc) {
		foodcombMapper.updateByPrimaryKey(fc);
	}	
	public void insertFC(Foodcomb fc) {
		foodcombMapper.insert(fc);
	}
	public void insertNewFC(String conbName, int count) {
		Foodcomb fc = new Foodcomb();
		fc.setComb(conbName);
		fc.setCount(count);
		insertFC(fc);
	}
	
	//for test. will be useless
	public List<String> sele(int number){
		return orderTableMapper.selectLatest(number);
	}
	
	}

