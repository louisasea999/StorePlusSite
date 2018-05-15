package com.dxc.pai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.dxc.pai.model.User;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.service.UserService;
import com.dxc.pai.util.AnalogLogin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaiApplicationTests {

	@Autowired
	public UserService us;
	
	@Test
	public void contextLoads() {
		User u1 = new User();
		u1.setPassword("地的编码");
		u1.setUsername("地的编码");
		us.addUser(u1);
	}
	
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
