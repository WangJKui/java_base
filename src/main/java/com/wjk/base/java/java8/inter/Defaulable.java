package com.wjk.base.java.java8.inter;

public interface Defaulable {

	/**
	 * 默认方法
	 * @return
	 */
	default String notRequired() { 
		return "Defaulable 接口 默认方法： notRequired"; 
	}  
	/**
	 * 默认方法
	 * @return
	 */
	default String notRequired1() { 
		return "Defaulable 接口 默认方法： notRequired1"; 
	}   

	/**
	 * 静态方法
	 * @return
	 */
	static String create() {
		return "Defaulable 接口 默认方法： create";
	}
	
	/**
	 * 静态方法1
	 * @return
	 */
	static String create1() {
		return "Defaulable 接口 默认方法： create1";
	}
}
