package com.kyw.sync1;

/**
 * 
 * 
 * @author MR.W
 *
 */
public class StringLock {
	
	private String lock = "StringLock";
	
	private void method() {
		//new String("字符串常量");
//		synchronized (new String("字符串常量"))
		synchronized (lock) {
			try {
				while(true) {
				System.out.println("当前线程："+Thread.currentThread().getName()+"开始");
				lock = "new Lock";
				Thread.sleep(2000);
				System.out.println("当前线程："+Thread.currentThread().getName()+"结束======");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final StringLock sl = new StringLock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sl.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sl.method();
			}
		},"t2");
		t1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
		
	}
	
}
