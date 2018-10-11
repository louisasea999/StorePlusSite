package com.dxc.pai.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.AllFacesMapper;
import com.dxc.pai.dao.FoodcombMapper;
import com.dxc.pai.dao.OrderTableMapper;
import com.dxc.pai.model.AllFaces;
import com.dxc.pai.model.Foodcomb;
import com.dxc.pai.model.OrderTable;
import com.dxc.pai.model.RestResult;
import com.dxc.pai.util.CommonTool;
import com.dxc.pai.util.Util;

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
	private AllFacesMapper allFacesMapper;

	@Autowired
	private FoodcombMapper foodcombMapper;
	
	public List<OrderTable> selectFirst(int num){
		return orderTableMapper.selectFirst(num);
	}
	
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
			updateOrderTable(order);
		}
	}
	public void updateOrderTable(OrderTable order) {
		orderTableMapper.updateByPrimaryKey(order);
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
	public Map<String, Object> getSalesData(boolean num)
	{
		
		Map<String, Object> saleData = new HashMap<String, Object>();		
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
	
	public JSONArray changeMapToJSONArray(Map<String, Object> saleData)
	{
		JSONArray result = new JSONArray();
		for(Map.Entry<String, Object> entry : saleData.entrySet()) { 
			JSONObject temp = new JSONObject();
			temp.put("foodName",entry.getKey());
			temp.put("number", entry.getValue());
			result.add(temp);
		}
		return result;
		
	}
	/**
	 * 得到销售额百分比或者销售量百分比
	 * @param num
	 * @return
	 */
	public Map<String, Object> getSalesDataPercent(boolean num)
	{
		Map<String, Object> saleDataPercent = new HashMap<String, Object>();
		int count = 0;
		Map<String, Object> saleData = getSalesData(num);
		for (Object value : saleData.values()) {  
		   count += Integer.parseInt(value.toString());		  
		} 
		System.out.println("count:"+ count);
		for(Map.Entry<String, Object> entry : saleData.entrySet()) {  
			double price = Double.parseDouble(entry.getValue().toString());
			saleDataPercent.put(entry.getKey(),  (price/count));	  
		}  
		
		System.out.println(saleDataPercent.toString());
		return saleDataPercent;
		
	}
	public List<Foodcomb> getComb() {
		List<Foodcomb> selectAll = foodcombMapper.selectAll();
		return selectAll;
	}
	public JSONArray getOrderAndFace() {
		
		JSONArray result = JSONArray.fromObject(orderTableMapper.selectAll().stream()
			.filter(ele -> ele.getFindAllfacesId() != null)
			.map(ele -> getMap(ele))
			.filter(ele -> ele != null)
			.collect(Collectors.toList()));		
		return result;
	}
	private JSONObject getMap(OrderTable ot) {
		try {
			AllFaces af = allFacesMapper.selectByPrimaryKey(ot.getFindAllfacesId());
			JSONObject temp = new JSONObject();
			temp.put("fooddetails", ot.getFooddetails());
			temp.put("opentime", Util.getFormatDateAll(ot.getOpentime()));
			temp.put("age", af.getAge());
			temp.put("pictime", Util.getFormatDateAll(af.getPictime()));
			temp.put("ethnicity", af.getEthnicity());
			temp.put("femaleScore", af.getFemaleScore());
			temp.put("maleScore", af.getMaleScore());
			return temp;
		}catch(Exception ex) {
			return null;
		}
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
	
	public Map<String,Long> getTopTen(Date start,Date end,int numberOfFood){
		//List<OrderTable> ots = getRange(new Date(), new Date(new Date().getTime() - 72*60*60*1000));
		List<OrderTable> orderTables = getRange(start,end);
		/*
		JSONArray foodDetails = JSONArray.fromObject(ots.get(0).getFooddetails());
		for(int j=0;j<foodDetails.size();j++)
		{
			int count = 0;
			JSONObject food=JSONObject.fromObject(foodDetails.get(j));
			String foodName = food.get("foodName").toString();
		}
		*/
		Map<String,Long> TopTen = orderTables.stream()
				//.map(ele->ele.getFooddetails())
		.map(ele->generateFoodNameList(ele.getFooddetails()))
		.reduce((ele1,ele2)->mergeList(ele1,ele2))
		.get()
		.stream()
		.collect(Collectors.groupingBy(ele->ele, Collectors.counting()))
		.entrySet()
		.stream()
		//.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.sorted((Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) -> (int)(o2.getValue() - o1.getValue()))
		.limit(numberOfFood)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		/*
		List<Long> top = TopTen.values().stream()
			.sorted()
			.limit(numberOfFood)
			//.forEach(ele -> filterMap(ele,TopTen));
			.collect(Collectors.toList());
		for(int i = 0; i < top.size(); i++) {
			
		}
		*/
		return TopTen;
	}
	/*private void filterMap(Long value, Map<String,Long> map) {
		
	}*/
	private List<String> generateFoodNameList(String foodDetails){
		JSONArray JSfoodDetails = JSONArray.fromObject(foodDetails);
		
		return (List<String>) JSfoodDetails
				.stream()
				.map(ele->(JSONObject.fromObject(ele)).get("foodName").toString())
				.collect(Collectors.toList());
	}
	
	public <T> List<T> mergeList(List<T> li1, List<T> li2){
		li1.addAll(li2);
		return li1;
	}

	//for test. will be useless
	public List<String> sele(int number){
		return orderTableMapper.selectLatest(number);
	}
	
	public List<OrderTable> getRange(Date start, Date end){
		return orderTableMapper.getRange(start, end);
	}
	
	}

