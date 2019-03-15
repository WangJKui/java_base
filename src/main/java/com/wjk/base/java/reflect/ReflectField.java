package com.wjk.base.java.reflect;

import java.lang.reflect.Field;

public class ReflectField {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.wjk.base.java.reflect.Student");
		//获取指定字段名称的Field类,注意字段修饰符必须为public而且存在该字段,
		// 否则抛NoSuchFieldException
		Field field = clazz.getField("age");
		System.out.println("field:"+field);

		//获取所有修饰符为public的字段,包含父类字段,注意修饰符为public才会获取
		Field fields[] = clazz.getFields();
		for (Field f:fields) {
			System.out.println("f:"+f.getDeclaringClass());
		}

		System.out.println("================getDeclaredFields====================");
		//获取当前类所字段(包含private字段),注意不包含父类的字段
		Field fields2[] = clazz.getDeclaredFields();
		for (Field f:fields2) {
			System.out.println("f2:"+f.getDeclaringClass());
		}
		//获取指定字段名称的Field类,可以是任意修饰符的自动,注意不包含父类的字段
		Field field2 = clazz.getDeclaredField("desc");
		System.out.println("field2:"+field2);
		
		System.out.println("====================================================");

		//获取Class对象引用
		Class<?> clazz1 = Class.forName("reflect.Student");

		Student st= (Student) clazz1.newInstance();
		//获取父类public字段并赋值
		Field ageField = clazz1.getField("age");
		ageField.set(st,18);
		Field nameField = clazz1.getField("name");
		nameField.set(st,"Lily");

		//只获取当前类的字段,不获取父类的字段
		Field descField = clazz1.getDeclaredField("desc");
		descField.set(st,"I am student");
		Field scoreField = clazz1.getDeclaredField("score");
		//设置可访问，score是private的
		scoreField.setAccessible(true);
		scoreField.set(st,88);
		System.out.println(st.toString());

		//输出结果：Student{age=18, name='Lily ,desc='I am student', score=88} 

		//获取字段值
		System.out.println(scoreField.get(st));
		// 88
	}
}
