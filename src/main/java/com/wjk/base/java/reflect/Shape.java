package com.wjk.base.java.reflect;

public class Shape {

	public void draw(){
		System.out.println("draw");
	}

	public void draw(int count , String name){
		System.out.println("draw "+ name +",count="+count);
	}	
}
