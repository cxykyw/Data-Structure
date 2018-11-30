package com.kyw.sync1;
/**
 * 
 * synchronized重入
 * @author MR.W
 *
 */
public class SynDubbo1 {

	public synchronized void method1() {
		System.out.println("method1....");
		this.method2();
	}
	
	public synchronized void method2() {
		System.out.println("method2....");
		this.method3();
	}
	
	public synchronized void method3() {
		System.out.println("method3....");
	}
	
	public static void main(String[] args) {
		
		final SynDubbo1 sd = new SynDubbo1();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sd.method1();
			}
		});
		
		t1.start();
	}
	
}
