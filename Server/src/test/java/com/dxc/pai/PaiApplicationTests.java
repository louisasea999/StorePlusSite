<<<<<<< HEAD
//package com.dxc.pai;
//
//import java.text.Collator;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Random;
//import java.util.Set;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//import java.util.stream.DoubleStream;
//import java.util.stream.IntStream;
//import java.util.stream.LongStream;
//import java.util.stream.Stream;
//
//import org.apache.commons.lang.ArrayUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.dxc.pai.model.User;
//import com.dxc.pai.service.OrderService;
//import com.dxc.pai.service.UserService;
//import com.dxc.pai.util.AnalogLogin;
//import com.dxc.pai.util.SortChinese;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PaiApplicationTests {
//
//	@Autowired
//	public UserService us;
//
//	@Autowired
//	public OrderService os;
//	
=======
package com.dxc.pai;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.pai.model.FindAllfaces;
import com.dxc.pai.model.OrderTable;
import com.dxc.pai.model.User;
import com.dxc.pai.service.FaceServices;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.service.UserService;
import com.dxc.pai.util.AnalogLogin;
import com.dxc.pai.util.SortChinese;
import com.dxc.pai.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaiApplicationTests {

	@Autowired
	public UserService us;

	@Autowired
	public OrderService os;
	
	@Autowired
	public FaceServices fs;
	
	@Test
	public void getRange() {
		/*List<OrderTable> li = os.getRange(new Date(new Date().getTime() - 24*60*60*1000),new Date(new Date().getTime() - 1*60*60*1000));
		System.out.println(li.size());*/
		
		///* the two list should be two params
		List<FindAllfaces> facesToBeConn = fs.selectAllFaceData().stream()
				.sorted(Comparator.comparing(FindAllfaces::getPictime))
				.collect(Collectors.toList());
		/*List<OrderTable> ordersToBeConn = os.selectFirst(50).stream()
		.sorted(Comparator.comparing(OrderTable::getOpentime))
		.filter(ele->ele.getOpentime().getTime()>=1524801743296l && ele.getOpentime().getTime()<=1524802070296l)
		.collect(Collectors.toList());*/
		System.out.println(facesToBeConn.get(0).getPictime());
		System.out.println(facesToBeConn.get(facesToBeConn.size()-1).getPictime());
		List<OrderTable> ordersToBeConn = os.getRange(facesToBeConn.get(0).getPictime(),facesToBeConn.get(facesToBeConn.size()-1).getPictime());
		System.out.println(ordersToBeConn.size());
		//*/
		
	}
	
	@Test
	public void getAllData() {
		/*
		List<FindAllfaces> fsd = fs.selectAllFaceData();
		fsd.stream()
		.peek(ele->ele.setPictime(new Date(ele.getPictime().getTime()-1*24*60*60*1000)))
		.forEach(ele->fs.updateFace(ele));
		*/
		/*fs.selectAllFaceData()
		.stream()
		.map(ele->ele.getPictime().getTime())
		.sorted()
		.forEach(System.out::println);*/
		/*
		fs.selectAllFaces().forEach(System.out::println);
		*/
		
		
		///* the two list should be two params
		List<FindAllfaces> facesToBeConn = fs.selectAllFaceData().stream()
				.sorted(Comparator.comparing(FindAllfaces::getPictime))
				.collect(Collectors.toList());
		/*List<OrderTable> ordersToBeConn = os.selectFirst(50).stream()
		.sorted(Comparator.comparing(OrderTable::getOpentime))
		.filter(ele->ele.getOpentime().getTime()>=1524801743296l && ele.getOpentime().getTime()<=1524802070296l)
		.collect(Collectors.toList());*/
		System.out.println(facesToBeConn.get(0).getPictime());
		System.out.println(facesToBeConn.get(facesToBeConn.size()-1).getPictime());
		List<OrderTable> ordersToBeConn = os.getRange(facesToBeConn.get(0).getPictime(),facesToBeConn.get(facesToBeConn.size()-1).getPictime());
		//*/
		
		//os.sele(10);
		for(OrderTable item : ordersToBeConn) {
			List<FindAllfaces> faceRange = facesToBeConn.stream()
					.filter(ele->ele.getPictime().getTime() >= item.getOpentime().getTime() - 30*1000)
					.filter(ele->ele.getPictime().getTime() <= item.getOpentime().getTime() + 30*1000)
					//.map(ele->new minFaceObj(ele.getId(), ele.getPictime(), ele.getFacesetOuterid()))
					.map(ele->(FindAllfaces)ele.clone())
					.collect(Collectors.toList());
			FindAllfaces targetMinFaceObj = getTargetFace(faceRange, item);
			if(targetMinFaceObj == null) {
				item.setFindAllfacesId(-1);
				continue;
			}
			item.setFindAllfacesId(targetMinFaceObj.getId());
			
			///* for print
			FindAllfaces targetFace = facesToBeConn.stream()
								.filter(ele->ele.getId() == targetMinFaceObj.getId())
								.findFirst().get();
			System.out.println("\n-----------------------------------------------------------------------");
			System.out.println("FaceID: "+targetFace.getId()+"---orderId: "+item.getId());
			System.out.println("faceOuterID: "+targetFace.getFacesetOuterid()+"---order: "+item.getFooddetails());
			System.out.println("faceTime: "+targetFace.getPictime()+"---orderTime: "+item.getOpentime());
			//*/
			
			facesToBeConn = facesToBeConn.stream()
						.filter(ele->ele.getFacesetOuterid() != targetMinFaceObj.getFacesetOuterid())
						.collect(Collectors.toList());
		}
		//ordersToBeConn.stream().forEach(ele->os.updateOrderTable(ele));
		facesToBeConn.stream().forEach(ele->System.out.println(ele.getPictime()));
		
	}
	public FindAllfaces getTargetFace(List<FindAllfaces> faceRange, OrderTable item) {
		if(faceRange.size()==0 || item == null) {
			return null;
		}
		List<List<FindAllfaces>> fsl = new ArrayList<List<FindAllfaces>>();
		try {
			fsl = (List<List<FindAllfaces>>) faceRange.stream()
					.peek(ele->ele.setPictime(new Date(Math.abs(ele.getPictime().getTime()-item.getOpentime().getTime()))))
					.sorted(Comparator.comparing(FindAllfaces::getPictime))
					.collect(Collectors.groupingBy((ele)->ele.getFacesetOuterid()))
					.values()
					.stream()
					.sorted((n1,n2)->((Integer)n2.size()).compareTo(n1.size()))
					.collect(Collectors.toList());
			
			for(int i = fsl.size()-1; i >= 0; i--) {
				if(fsl.get(i).size()==fsl.get(0).size()) {
					fsl = fsl.subList(0, i+1);
					break;
				}
			}
		}catch(Exception ex) {
			return null;
		}
		return fsl.stream().reduce((li1,li2)->mergeList(li1,li2))
				.get()
				.stream()
				.sorted(Comparator.comparing(FindAllfaces::getPictime))
				.findFirst()
				.get();
	}
	public <T> List<T> mergeList(List<T> li1, List<T> li2){
		li1.addAll(li2);
		return li1;
	}

	
	
	class minFaceObj{
		private Integer id;
		private Date pictime;
		private String facesetOuterid;
		public minFaceObj() {}
		public minFaceObj(int id, Date pictime, String facesetOuterid) {
			this.id = id;
			this.pictime = pictime;
			this.facesetOuterid = facesetOuterid;
		}
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
	    public String getFacesetOuterid() {
	        return facesetOuterid;
	    }

	    public void setFacesetOuterid(String facesetOuterid) {
	        this.facesetOuterid = facesetOuterid == null ? null : facesetOuterid.trim();
	    }

	    public Date getPictime() {
	        return pictime;
	    }

	    public void setPictime(Date pictime) {
	        this.pictime = pictime;
	    }
	}
	//new Date(1524801743296l)), Util.getFormatDateAll(new Date(1524802070296l)))

	
	
	
	
	@Test
	public void contextLoads1() {
		os.getSalesData(true);
		os.getSalesData(false);
		os.getSalesDataPercent(true);
		
		
		os.getSalesDataPercent(false);

		
	}
	
>>>>>>> cf6e0c9919bc0de5bceaeb6ec7950af59919b26b
//	@Test
//	public void contextLoads1() {
//		os.getSalesData(true);
//		os.getSalesData(false);
//		os.getSalesDataPercent(true);
//		
//		
//		os.getSalesDataPercent(false);
//		
////		AnalogLogin al = new AnalogLogin(orderService);
////		al.loginAndGetData();
//		
//		
//	}
//	
////	@Test
////	public void test() {
////
////		int[] li = generateRandomArray(10,new int[]{});
////		System.out.println(li.length);
////		for(int i = 0; i < li.length; i++) {
////			System.out.print(li[i]+",");
////		}
////		//addOne({1});
////		
////	}
////	
////	private int[] generateRandomArray(int len, int[] array){
////		if(len == 0) {
////			return array;
////		}else {
////			return generateRandomArray(len-1,ArrayUtils.add(array, new Random().nextInt(100)));
////		}
////	}
////	
////	@Test
////	public void testqs() {
////		int[] li = generateRandomArray(10,new int[]{});
////		//int[] li = {5,4,3,7,8,9,11,23,1,14};
////		//IntStream.of(li).forEach(System.out::println);
////		int[] lb = quickSort(li);
////		IntStream.of(lb).forEach(x -> System.out.print(x+","));
////	}
////	private int[] quickSort(int[] arr) {
////		if(arr.length<=1) {
////			return arr;
////		}else {
////			//return ArrayUtils.addAll(ArrayUtils.addAll(quickSort(lesser(IntStream.of(arr).skip(1).toArray(),arr[0])),new int[] {arr[0]}),quickSort(greater(IntStream.of(arr).skip(1).toArray(),arr[0])));
////			return concatArray(quickSort(lesser(IntStream.of(arr).skip(1).toArray(),arr[0])),new int[] {arr[0]},quickSort(greater(IntStream.of(arr).skip(1).toArray(),arr[0])));
////		}
////	}
////	private int[] concatArray(int[] arr1, int[] arr2, int[] arr3) {
////		return Stream.of(arr1,arr2,arr3).flatMapToInt(arr -> IntStream.of(arr)).toArray();
////	}
////	private int[] lesser(int[] array, int cp) {
////		return IntStream.of(array).filter(x -> x < cp).toArray();
////	}
////	private int[] greater(int[] array,int cp) {
////		//List<Integer> li = Arrays.asList(1,2,3,4,5);
////		return IntStream.of(array).filter(x -> x >= cp).toArray();
////	}
////	@Test
////	public void rett() {
////		
////		/*int[] arr1 = new int[]{1,2,3,4};
////		int[] arr2 = new int[]{6,7,8};
////		IntStream.of(ArrayUtils.addAll(arr1,arr2)).forEach(x -> System.out.print(x+","));
////		System.out.println();
////		IntStream.of(arr1).forEach(x -> System.out.print(x+","));
////		System.out.println();
////		IntStream.of(arr2).forEach(x -> System.out.print(x+","));*/
////		//arr1.
////		//return new int[]{1};
////		//List<Integer> li = new ArrayList<Integer>();
////		Map<Boolean,List<Integer>> mapp = Stream.generate(()->new Random().nextInt(5))
////				.limit(15)
////				.collect(Collectors.partitioningBy((e) -> e>2));
////		mapp.forEach((key,val) -> System.out.println(key+","+val));
////		
////		
////		
////	}
////	private class PersonSupplier implements Supplier<Person> {
////
////		@Override
////		public Person get() {
////			return new Person();
////		}
////		
////	}
////	class Person{
////		public Person pg() {
////			return new Person();
////		}
////	}
//
//	public void testnewselect() {
//		String comb = "oo1+oo2000";
//		os.insertNewFC(comb, 10);
//	}
//	
//	@Test
//	public void updateFoodcombEveryDay() {
//		os.sele(25).stream()
//		.map(ele->JSONArray.fromObject(ele.toString()))
//		.filter(ele->ele.size()>1)
//		.map(ele -> JSONArrayToList(ele))
//		.filter(ele->ele.size()>1)
//		//.collect(Collectors.toList())
//		//.stream()
//		.peek(ele->Collections.sort(ele, new SortChinese()))
//		//.collect(Collectors.toList())
//		.map(ele -> getConbinations(ele,new ArrayList<String>()))
//		//.map(ele -> ele.toArray())
//		.reduce((li1,li2) -> mergeList(li1,li2))
//		.get()
//		.stream()
//		.collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
//		//.map(ele -> Arrays.asList(ele));
//		
//		//.reduce((li1,li2) ->(li1.addAll(li2)));
//	}
//	public List<String> JSONArrayToList(JSONArray ja) {		
//		return (List<String>) ja.stream()
//			.peek(ele->JSONObject.fromObject(ele))
//			.map(ele -> ((JSONObject) ele).getString("foodName"))
//			.peek(ele -> ele.toString().trim())
//			.filter(ele -> !"打包盒".equals(ele))
//			.collect(Collectors.toList());
//	}
//	
//	@Test
//	public void testcol() {
//		List<List<String>> li = new ArrayList<List<String>>();
//		List<String> li1 = Arrays.asList(new String[] {"红烧狮子头饭", "台式卤肉饭", "香辣牛肉面"});
//		List<String> li2 = Arrays.asList(new String[] {"500ml冰红茶", "600ml可乐", "600ml可乐"});
//		li.add(li1);
//		li.add(li2);
//		 li.stream()
//		.reduce((arr1,arr2) -> mergeList(arr1,arr2))
//		.get()
//		.stream()
//		.collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
//	}
//	public List<String> mergeList(List<String> li1, List<String> li2){
//		li1.addAll(li2);
//		return li1;
//	}
//	
//	@Test
//	public void contextLoads() {
//		//os.sele(5).stream().forEach(System.out::println);
//		/*os.sele(5).stream().map(ele->JSONArray.fromObject(ele.toString()))
//		.filter(ele->ele.size()>1).map(ele -> tojo(ele))
//		.forEach(System.out::println);*/
//		System.out.println("*************************************************************");
//		List<List<String>> ol = getOrderList(25);
//		System.out.println(ol.size());
//		System.out.println("unsort:");
//		System.out.println(ol);
//		System.out.println("sorted:");
//		List<List<String>> sortedol = ol.stream().peek(ele->Collections.sort(ele, new SortChinese())).collect(Collectors.toList());
//		System.out.println(sortedol);
//		sortedol.stream().map(ele -> getConbinations(ele,new ArrayList<String>())).forEach(System.out::println);
//		System.out.println("*************************************************************");
//		
//		//get a List<List<List<String>>> something;
//	}
//	//size is li.size()*(li.size()-1)/2
//	@Test
//	public void testGen() {
//		List<String> li = Arrays.asList(new String[] {"500ml冰红茶", "600ml可乐", "600ml可乐", "红烧狮子头饭", "台式卤肉饭", "香辣牛肉面"});
//		System.out.println(li);
//		List<String> getCo = getConbinations(li, new ArrayList<String>());
//		System.out.println(getCo.size());
//		System.out.println(getCo);
//		//getCo.stream().collect(Collectors.groupingBy(e->e)).forEach((key,val) -> System.out.println(key+","+val.size()));
//		getCo.stream().collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
//		/*Set<String> se1 = new HashSet<String>(getCo);
//		System.out.println(se1.size());
//		System.out.println(se1);*/
//	}
//
//	public void aFuncToSave(String conb, int count) {
//		System.out.println(conb+":"+count);
//	}
//	public List<String> getConbinations(List<String> li, List<String> result){
//		if(li.size() <= 1) {
//			return result;
//		}else {
//			for(int i = 1; i < li.size(); i++) {
//				result.add(li.get(0)+"+"+li.get(i));
//			}
//		}
//		return getConbinations(li.stream().skip(1).collect(Collectors.toList()), result);
//	}
//
//	public List<List<String>> getOrderList(int number) {
//		return os.sele(number).stream().map(ele->JSONArray.fromObject(ele.toString()))
//				.filter(ele->ele.size()>1).map(ele -> tojo(ele))
//				.collect(Collectors.toList());
//	}
//	public List<String> tojo(JSONArray ja) {
//		
//		return (List<String>) ja.stream()
//			.peek(ele->JSONObject.fromObject(ele))
//			.map(ele -> ((JSONObject) ele).getString("foodName"))
//			.collect(Collectors.toList());
//	}
//	//combination num:size()*(size()-1)/2
//	
//	@Test
//	public void test() {
//		System.out.println(new Date(1355331143000L));		
//	}
//	
//	@Test
//	public void testqs() {
//		int[] li = IntStream.generate(() -> new Random().nextInt(10)).limit(10).sorted().toArray();
//		Arrays.stream(li).forEach(System.out::print);
//	}
//	
//	@Test
//	public void rett() {		
//		/*int[] arr1 = new int[]{1,2,3,4};
//		int[] arr2 = new int[]{6,7,8};
//		IntStream.of(ArrayUtils.addAll(arr1,arr2)).forEach(x -> System.out.print(x+","));
//		System.out.println();
//		IntStream.of(arr1).forEach(x -> System.out.print(x+","));
//		System.out.println();
//		IntStream.of(arr2).forEach(x -> System.out.print(x+","));*/
//		//arr1.
//		//return new int[]{1};
//		//List<Integer> li = new ArrayList<Integer>();
//		Map<Boolean,List<Integer>> mapp = Stream.generate(()->new Random().nextInt(5))
//				.limit(15)
//				.collect(Collectors.partitioningBy((e) -> e>2));
//		mapp.forEach((key,val) -> System.out.println(key+","+val));
//	}
<<<<<<< HEAD
//}
=======

	public void testnewselect() {
		String comb = "oo1+oo2000";
		os.insertNewFC(comb, 10);
	}
	
	@Test
	public void updateFoodcombEveryDay() {
		os.sele(25).stream()
		.map(ele->JSONArray.fromObject(ele.toString()))
		.filter(ele->ele.size()>1)
		.map(ele -> JSONArrayToList(ele))
		.filter(ele->ele.size()>1)
		//.collect(Collectors.toList())
		//.stream()
		.peek(ele->Collections.sort(ele, new SortChinese()))
		//.collect(Collectors.toList())
		.map(ele -> getConbinations(ele,new ArrayList<String>()))
		//.map(ele -> ele.toArray())
		.reduce((li1,li2) -> mergeList(li1,li2))
		.get()
		.stream()
		.collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
		//.map(ele -> Arrays.asList(ele));
		
		//.reduce((li1,li2) ->(li1.addAll(li2)));
	}
	public List<String> JSONArrayToList(JSONArray ja) {		
		return (List<String>) ja.stream()
			.peek(ele->JSONObject.fromObject(ele))
			.map(ele -> ((JSONObject) ele).getString("foodName"))
			.peek(ele -> ele.toString().trim())
			.filter(ele -> !"打包盒".equals(ele))
			.collect(Collectors.toList());
	}
	
	@Test
	public void testcol() {
		List<List<String>> li = new ArrayList<List<String>>();
		List<String> li1 = Arrays.asList(new String[] {"红烧狮子头饭", "台式卤肉饭", "香辣牛肉面"});
		List<String> li2 = Arrays.asList(new String[] {"500ml冰红茶", "600ml可乐", "600ml可乐"});
		li.add(li1);
		li.add(li2);
		 li.stream()
		.reduce((arr1,arr2) -> mergeList(arr1,arr2))
		.get()
		.stream()
		.collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
	}
	/*
	public List<String> mergeList(List<String> li1, List<String> li2){
		li1.addAll(li2);
		return li1;
	}*/
	
	@Test
	public void contextLoads() {
		//os.sele(5).stream().forEach(System.out::println);
		/*os.sele(5).stream().map(ele->JSONArray.fromObject(ele.toString()))
		.filter(ele->ele.size()>1).map(ele -> tojo(ele))
		.forEach(System.out::println);*/
		System.out.println("*************************************************************");
		List<List<String>> ol = getOrderList(25);
		System.out.println(ol.size());
		System.out.println("unsort:");
		System.out.println(ol);
		System.out.println("sorted:");
		List<List<String>> sortedol = ol.stream().peek(ele->Collections.sort(ele, new SortChinese())).collect(Collectors.toList());
		System.out.println(sortedol);
		sortedol.stream().map(ele -> getConbinations(ele,new ArrayList<String>())).forEach(System.out::println);
		System.out.println("*************************************************************");
		
		//get a List<List<List<String>>> something;
	}
	//size is li.size()*(li.size()-1)/2
	@Test
	public void testGen() {
		List<String> li = Arrays.asList(new String[] {"500ml冰红茶", "600ml可乐", "600ml可乐", "红烧狮子头饭", "台式卤肉饭", "香辣牛肉面"});
		System.out.println(li);
		List<String> getCo = getConbinations(li, new ArrayList<String>());
		System.out.println(getCo.size());
		System.out.println(getCo);
		//getCo.stream().collect(Collectors.groupingBy(e->e)).forEach((key,val) -> System.out.println(key+","+val.size()));
		getCo.stream().collect(Collectors.groupingBy(e->e)).forEach((key,val) -> aFuncToSave(key, val.size()));
		/*Set<String> se1 = new HashSet<String>(getCo);
		System.out.println(se1.size());
		System.out.println(se1);*/
	}

	public void aFuncToSave(String conb, int count) {
		System.out.println(conb+":"+count);
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

	public List<List<String>> getOrderList(int number) {
		return os.sele(number).stream().map(ele->JSONArray.fromObject(ele.toString()))
				.filter(ele->ele.size()>1).map(ele -> tojo(ele))
				.collect(Collectors.toList());
	}
	public List<String> tojo(JSONArray ja) {
		
		return (List<String>) ja.stream()
			.peek(ele->JSONObject.fromObject(ele))
			.map(ele -> ((JSONObject) ele).getString("foodName"))
			.collect(Collectors.toList());
	}
	//combination num:size()*(size()-1)/2
	
	@Test
	public void test() {
		System.out.println(new Date(1355331143000L));		
	}
	
	@Test
	public void testqs() {
		int[] li = IntStream.generate(() -> new Random().nextInt(10)).limit(10).sorted().toArray();
		Arrays.stream(li).forEach(System.out::print);
	}
	
	@Test
	public void rett() {
		/*int[] arr1 = new int[]{1,2,3,4};
		int[] arr2 = new int[]{6,7,8};
		IntStream.of(ArrayUtils.addAll(arr1,arr2)).forEach(x -> System.out.print(x+","));
		System.out.println();
		IntStream.of(arr1).forEach(x -> System.out.print(x+","));
		System.out.println();
		IntStream.of(arr2).forEach(x -> System.out.print(x+","));*/
		//arr1.
		//return new int[]{1};
		//List<Integer> li = new ArrayList<Integer>();
		Map<Boolean,List<Integer>> mapp = Stream.generate(()->new Random().nextInt(5))
				.limit(15)
				.collect(Collectors.partitioningBy((e) -> e>2));
		mapp.forEach((key,val) -> System.out.println(key+","+val));
	}
}
>>>>>>> cf6e0c9919bc0de5bceaeb6ec7950af59919b26b
