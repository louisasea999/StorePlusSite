package com.dxc.pai;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.dxc.pai.util.SortChinese;

public class ETest {

	@Test
	public void soreCh() {
		List<String> li = Arrays.asList(new String[] {"招牌牛肉面","煎蛋","抄手面","打包盒","香辣牛肉面","500ml冰红茶","牛肉套饭","红汤抄手","香辣牛肉粉","老鸭汤面"});
		System.out.println(li.size());
		System.out.println(li);
		
		Collections.sort(li, new SortChinese());
		System.out.println(li);
		
		//Collections.sort(li);
		//System.out.println(li);
		//System.out.println(li.stream().sorted().collect(Collectors.toList()));
	}
	
}
