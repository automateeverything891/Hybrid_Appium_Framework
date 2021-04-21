package com.practice;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample2 {
	
	public static void main(String[] args) {
		/*
		 * for(char ch='A'; ch<='Z'; ch++) {
		 * 
		 * for(char ch1='Z'; ch1>ch; ch1--) {
		 * 
		 * System.out.print(" "); } for(char ch2='A'; ch2<ch; ch2++) {
		 * 
		 * System.out.print(ch); }
		 * 
		 * System.out.println(ch);
		 * 
		 * }
		 */
		
		TreeSet<String> treeset = new TreeSet<String>(Arrays.asList("A","Z","B","O","Y","S","J","H"));
		
		for(String s :treeset) {
			
			System.out.println(s);
		}
		
		Map<String , Integer> map = Stream.of(
				  new AbstractMap.SimpleEntry<>("idea", 100), 
				  new AbstractMap.SimpleEntry<>("mobile", 2))
				  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		System.out.println(map.get("idea"));
		
		map.put("YHA", 1000);
		System.out.println(map.get("YHA"));
		
		
		Map<String , Integer> map1 = Stream.of(new Object[][] { 
		     { "data1", 1 }, 
		     { "data2", 2 }, 
		 }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
		
		System.out.println(map1.get("data2"));
		
		map1.put("data3", 1000);
		System.out.println(map1.get("data3"));
	
		
		System.out.println(map1);
		
		List<String> strs = new ArrayList<String>(Arrays.asList("THA","DIKMS"));
		strs.add("OHH");
		strs.add("OJJ");
		
		
		strs.stream().forEach(data-> System.out.println(data));
		
		map1.entrySet().stream().collect(Collectors.toMap(ele-> ele.getKey(), ele-> ele.getValue()));
		
		map1.forEach((k,v)->System.out.println(k+" : "+v));
		
		
	}

}
