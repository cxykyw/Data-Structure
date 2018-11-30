package com.kyw.sync1;

/**
 * 线程同步
 * @author MR.W
 *
 */
public class MyThread extends Thread{
	
	private int count = 5;
	
	@Override
	public synchronized void run() {
		count--;
		System.out.println(this.currentThread().getName()+"count="+count);
	}
	
	public static void main(String[] args) {
		
		MyThread m = new MyThread();
		Thread t1 = new Thread(m, "t1");
		Thread t2 = new Thread(m, "t2");
		Thread t3 = new Thread(m, "t3");
		Thread t4 = new Thread(m, "t4");
		Thread t5 = new Thread(m, "t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
}
