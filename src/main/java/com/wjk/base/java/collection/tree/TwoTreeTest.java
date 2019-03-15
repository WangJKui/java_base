package com.wjk.base.java.collection.tree;

public class TwoTreeTest {

	/**
	 * 前缀自增自减法(++a,--a): 先进行自增或者自减运算，再进行表达式运算。
	        后缀自增自减法(a++,a--): 先进行表达式运算，再进行自增或者自减运算 
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws
	 */
	public static void main(String[] args) {
		int a = 10>>1;//5
		int b = a++;//本以为=6，实际为5， 先表达式运算再自增，b=a=5,a=a+1=6
		int c = ++a;//7，先自增再表达式运算 a = a+1=7,c=a=7;
		int d = b * a++;//d = b*a = 5*7=35 ,a=a+1=8
		
		int e = b;
		
		System.out.println(a);//8 三次++
		System.out.println(b);//5
		System.out.println(c);//7
		System.out.println(d);//35 b=5 a=7 然后赋值
		System.out.println(e);
		
		int q = 8>>2;//5
		System.out.println(q);
		
	 
	}
}
