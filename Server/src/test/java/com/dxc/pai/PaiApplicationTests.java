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
//}
