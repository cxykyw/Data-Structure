package com.kyw.test;

public class Test {

	public static void main(String[] args) {
		SignleDemo instance1 = SignleDemo.getInstance();
		SignleDemo instance2 = SignleDemo.getInstance();
		
		System.out.println(instance1);
		System.out.println(instance2);
		
	}
	
}
