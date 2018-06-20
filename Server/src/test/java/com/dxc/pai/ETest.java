package com.dxc.pai;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.dxc.pai.model.FindAllfaces;
import com.dxc.pai.model.OrderTable;
import com.dxc.pai.util.SortChinese;

public class ETest {
	public static List<Connect> lc = new ArrayList<Connect>();


	@Test
	public void testGroup() {
		Integer it = new Integer(2);
		it.compareTo(2);
	}

	
	@Test
	public void soreCh() {
		System.out.println(new Date(1234));
		OrderTable ot = new OrderTable();
		FindAllfaces faf = new FindAllfaces();
		//connect();
	}
	public void connect() {
		List<FindAllfaces> fs = new ArrayList<FindAllfaces>();
		List<OrderTable> oo = new ArrayList<OrderTable>();

		
		//start -------------------------
		fs = fs.stream().sorted(Comparator.comparing(FindAllfaces::getPictime))
			.collect(Collectors.toList());
		oo = oo.stream().sorted(Comparator.comparing(OrderTable::getOpentime))
			.collect(Collectors.toList());
		
		for(OrderTable item : oo) {
			List<FindAllfaces> range = fs.stream()
					.filter(ele->ele.getPictime().getTime() >= item.getOpentime().getTime() - 2*1000)
					.filter(ele->ele.getPictime().getTime() <= item.getOpentime().getTime() + 2*1000)
					.collect(Collectors.toList());
			String targetName = getCon(range, item);
			fs = fs.stream().filter(ele->ele.getFacesetOuterid() != targetName)
					.collect(Collectors.toList());
		}
	}
	public String getCon(List<FindAllfaces> range, OrderTable item) {
		Map<String,List<FindAllfaces>> groups = range.stream()
				.peek(ele->ele.setPictime(new Date(Math.abs(ele.getPictime().getTime()-item.getOpentime().getTime()))))
				.sorted(Comparator.comparing(FindAllfaces::getPictime))
				.collect(Collectors.groupingBy((ele)->ele.getFacesetOuterid()));		
		List<List<FindAllfaces>> fsl = new ArrayList<List<FindAllfaces>>();
		for(String key : groups.keySet()){
			fsl.add(groups.get(key));
		}
		for(int i = fsl.size(); i >= 0; i--) {
			if(fsl.get(i).size()==fsl.get(0).size()) {
				fsl = fsl.subList(0, i);
			}
		}
		FindAllfaces finalFS = fsl.stream().reduce((li1,li2)->mergeList(li1,li2))
				.get()
				.stream()
				.sorted(Comparator.comparing(FindAllfaces::getPictime))
				.findFirst()
				.get();
		//persistence here
		//persistenceFunc(finalFS,item);
		return finalFS.getFacesetOuterid();
	}
	public List<FindAllfaces> mergeList(List<FindAllfaces> li1, List<FindAllfaces> li2){
		li1.addAll(li2);
		return li1;
	}

	/*
	class OrderO{
		Date dd;
		int num;
		public OrderO(Date dd, int num) {
			this.dd = dd;
			this.num = num;
		}
		public Date getDd() {
			return this.dd;
		}
		public int getNum() {
			return this.num;
		}
	}
	class Faceset{
		Date dd;
		String name;
		public Faceset(Date dd, String name) {
			this.dd = dd;
			this.name = name;
		}
		public Date getDd() {
			return this.dd;
		}
		public String getName() {
			return this.name;
		}
		public void setDd(Date dd) {
			this.dd = dd;
		}
	}*/
	
	class Connect{
		int num;
		String name;
		public Connect(int num, String name) {
			this.num = num;
			this.name = name;
		}
		public int getNum() {
			return this.num;
		}
		public String getName() {
			return this.name;
		}
	}
	
	public Map<Date,String> faceMap(){
		Map<Date, String> fm = new HashMap<Date, String>();
		Date nowTime = Calendar.getInstance().getTime();
		long nowl = nowTime.getTime();
		fm.put(nowTime, nowTime.toString());
		for(int i = 1; i < 50; i++) {
			fm.put(new Date(nowl+1000*i),""+i);
		}
		return fm;
	}
	public Map<Date, Integer> getOrderTime(){
		Map<Date, Integer> order = new HashMap<Date, Integer>();
		Date nowTime = Calendar.getInstance().getTime();
		long nowl = nowTime.getTime();
		order.put(nowTime, 0);
		for(int i = 1; i < 10; i++) {
			order.put(new Date(nowl+5000*i),i);
		}
		return order;
	}
}
