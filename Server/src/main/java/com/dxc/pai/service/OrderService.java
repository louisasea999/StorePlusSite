package com.dxc.pai.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 修改订单中食物信息
	 * @param orderData 食物信息
	 * @param id  订单id
	 */
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
	
	/**
	 * 插入订单数据
	 * @param totalOrder
	 * @return
	 */
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
	
	
	/**
	 * 得到销售量或销售额
	 * @param num 判断是销售量还是销售额
	 * @return
	 */
	public Map<String, Integer> getSalesData(boolean num)
	{
		
		Map<String, Integer> saleData = new HashMap<String, Integer>();		
		List<OrderTable> orders = orderTableMapper.selectAll();
				
		for(int i=0; i<orders.size();i++)
		{		
			OrderTable temp = orders.get(i);
			String fooddetail = temp.getFooddetails();
			if(fooddetail!=null)
			{
				JSONArray foodDetails = JSONArray.fromObject(fooddetail);
				for(int j=0;j<foodDetails.size();j++)
				{
					int count = 0;
					JSONObject food=JSONObject.fromObject(foodDetails.get(j));
					String foodName = food.get("foodName").toString();
					if(saleData.containsKey(foodName))
					{
						count  = (int) saleData.get(foodName);					
					}
					if(num)
						saleData.put(foodName, count+1);
					else
					{
						String priceStr = food.getString("amtPrice");
					    int price = Integer.parseInt(priceStr)/100000;
						saleData.put(food.get("foodName").toString(), count + price);
					}
				}								
			}
			
		}
		
		System.out.println(saleData.toString());
		return saleData;
		
	}
	
	/**
	 * 得到销售额百分比或者销售量百分比
	 * @param num
	 * @return
	 */
	public Map<String, Double> getSalesDataPercent(boolean num)
	{
		Map<String, Double> saleDataPercent = new HashMap<String, Double>();
		int count = 0;
		Map<String, Integer> saleData = getSalesData(num);
		for (Integer value : saleData.values()) {  			  
		   count += value;		  
		} 
		System.out.println("count:"+ count);
		for(Map.Entry<String, Integer> entry : saleData.entrySet()) {  			  
			saleDataPercent.put(entry.getKey(),  ((double)entry.getValue()/count));	  
		}  
		
		System.out.println(saleDataPercent.toString());
		return saleDataPercent;
		
	}

	}

