package com.wjk.base.java.java8.inter;

public class DefaulableTest {

	public static void main(String[] args) {
		Defaulable defaulable = new DefaultableImpl();
		
		System.out.println(defaulable.notRequired());
		System.out.println(defaulable.notRequired1());
		
		System.out.println(Defaulable.create());
		System.out.println(Defaulable.create1());
	}
	
}
