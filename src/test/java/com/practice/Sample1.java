package com.practice;

public class Sample1 {
	
	
	public static void main(String[] ab) {
		
		String amount = "PAY ₹ 10,453";
		
		String amount2 = "10453";
		String tr = amount.split(" ")[1].trim();
		
		System.out.println(String.format("%,d",Integer.parseInt(amount2)));
		System.out.println(tr);
		
		int a = Integer.parseInt(amount.split(" ")[2].replaceAll("[^0-9\\.]", "").trim());
		System.out.println(amount.split(" ")[2].replaceAll("[^0-9\\.]", "").trim());
	}
	

}
