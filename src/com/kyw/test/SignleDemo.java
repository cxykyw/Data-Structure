package com.kyw.test;

/**
 * 单例模式
 * 
 * @author MR.W
 *
 */
public class SignleDemo {

	private SignleDemo() {

	}

	private static volatile SignleDemo signle;

	public static SignleDemo getInstance() {
		if (signle == null) {
			synchronized (SignleDemo.class) {
				if (signle == null) {
					signle = new SignleDemo();
				}

			}
		}
		return signle;
	}


}
