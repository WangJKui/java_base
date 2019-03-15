package com.wjk.base.java;

public class javaTest {

	public static void main(String[] args) {
		String oldno = "09999";
		
		if(oldno.startsWith("0")) {

			int num = Integer.valueOf("1"+ oldno);
			
			String newnum = String.valueOf(++num);
			
			String newno = newnum.substring(1, newnum.length());
			
			System.out.println(newno);
		}else {
			int num = Integer.valueOf(oldno);
			
			int newnum = ++num;
			
			System.out.println(newnum);

		}
		
		
		 int batchSize = 9991 / 999 + (9991 % 999 == 0 ? 0 : 1);
		System.out.println(batchSize);
		
	}
}
