package com.dxc.pai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
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

import com.dxc.pai.service.OrderService;
import com.dxc.pai.util.AnalogLogin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaiApplicationTests {

	@Autowired
	public OrderService orderService;
	
	@Test
	public void contextLoads() {
		orderService.getSalesData(true);
		orderService.getSalesData(false);
		orderService.getSalesDataPercent(true);
		
		
		orderService.getSalesDataPercent(false);
		
//		AnalogLogin al = new AnalogLogin(orderService);
//		al.loginAndGetData();
		
		
	}
	
//	@Test
//	public void test() {
//
//		int[] li = generateRandomArray(10,new int[]{});
//		System.out.println(li.length);
//		for(int i = 0; i < li.length; i++) {
//			System.out.print(li[i]+",");
//		}
//		//addOne({1});
//		
//	}
//	
//	private int[] generateRandomArray(int len, int[] array){
//		if(len == 0) {
//			return array;
//		}else {
//			return generateRandomArray(len-1,ArrayUtils.add(array, new Random().nextInt(100)));
//		}
//	}
//	
//	@Test
//	public void testqs() {
//		int[] li = generateRandomArray(10,new int[]{});
//		//int[] li = {5,4,3,7,8,9,11,23,1,14};
//		//IntStream.of(li).forEach(System.out::println);
//		int[] lb = quickSort(li);
//		IntStream.of(lb).forEach(x -> System.out.print(x+","));
//	}
//	private int[] quickSort(int[] arr) {
//		if(arr.length<=1) {
//			return arr;
//		}else {
//			//return ArrayUtils.addAll(ArrayUtils.addAll(quickSort(lesser(IntStream.of(arr).skip(1).toArray(),arr[0])),new int[] {arr[0]}),quickSort(greater(IntStream.of(arr).skip(1).toArray(),arr[0])));
//			return concatArray(quickSort(lesser(IntStream.of(arr).skip(1).toArray(),arr[0])),new int[] {arr[0]},quickSort(greater(IntStream.of(arr).skip(1).toArray(),arr[0])));
//		}
//	}
//	private int[] concatArray(int[] arr1, int[] arr2, int[] arr3) {
//		return Stream.of(arr1,arr2,arr3).flatMapToInt(arr -> IntStream.of(arr)).toArray();
//	}
//	private int[] lesser(int[] array, int cp) {
//		return IntStream.of(array).filter(x -> x < cp).toArray();
//	}
//	private int[] greater(int[] array,int cp) {
//		//List<Integer> li = Arrays.asList(1,2,3,4,5);
//		return IntStream.of(array).filter(x -> x >= cp).toArray();
//	}
//	@Test
//	public void rett() {
//		
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
//		
//		
//		
//	}
//	private class PersonSupplier implements Supplier<Person> {
//
//		@Override
//		public Person get() {
//			return new Person();
//		}
//		
//	}
//	class Person{
//		public Person pg() {
//			return new Person();
//		}
//	}

}
