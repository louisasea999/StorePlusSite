package com.dxc.pai.util;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortChinese implements Comparator<String>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> li = Arrays.asList(new String[] {"招牌牛肉面","煎蛋","抄手面","打包盒","香辣牛肉面","500ml冰红茶","牛肉套饭","红汤抄手","香辣牛肉粉","老鸭汤面"});
		System.out.println("before sort:");
		System.out.println(li);
		Collections.sort(li, new SortChinese());
		System.out.println("sorted:");
		System.out.println(li);
		
	}

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		Collator cpc = Collator.getInstance(java.util.Locale.CHINA);
		if(cpc.compare(o1, o2)>0) {
			return 1;
		}else if(cpc.compare(o1, o2)<0) {
			return -1;
		}
		return 0;
	}
	

}
