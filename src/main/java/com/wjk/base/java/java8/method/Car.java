package com.wjk.base.java.java8.method;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Car {
	public static Car create( final Supplier< Car > supplier ) {
		return supplier.get();
	}              

	public static void collide( final Car car ) {
		System.out.println( "Collided " + car.toString() );
	}

	public void follow( final Car another ) {
		System.out.println( "Following the " + another.toString() );
	}

	public void repair() {   
		System.out.println( "Repaired " + this.toString() );
	}
	
	public static void main(String[] args) {
		final Car car = Car.create( Car::new );
		final List< Car > cars = Arrays.asList( car );
		
		cars.forEach( Car::collide );
		
		cars.forEach( Car::repair );
		
		final Car police = Car.create( Car::new );
		cars.forEach( police::follow );
		
		//=======================================
		Supplier<String> s = String::new;
		System.out.println(s.get());
		
		//================================
		List<String> list= Arrays.asList("a", "b", "c", "d","a");
		System.out.println(list);

		List<String> collect =list.stream().map(String::toUpperCase).collect(Collectors.toList());
		List<String> collect1 =list.stream().collect(Collectors.toList());
		Set<String> set =list.stream().collect(Collectors.toSet());
		
		System.out.println("set:"+set);

		System.out.println(collect);
		System.out.println(collect1);

	}
}
