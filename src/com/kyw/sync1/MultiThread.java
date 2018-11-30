package com.kyw.sync1;

/**
 * 
 * 多个线程多把锁
 * 
 * @author MR.W
 *
 */
public class MultiThread {

	private static int num = 1;

	public static synchronized void printNum(String tag) {
		try {
			if ("a".equals(tag)) {
				//num = 100;
				num++;
				System.out.println("set num success,tag a,num="+num);
				Thread.sleep(1000);
			} else {
				//num = 200;
				num--;
				System.out.println("set num success,tag b,num="+num);
			}
			System.out.println("tag=" + tag + ",num=" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		MultiThread m1 = new MultiThread();
		MultiThread m2 = new MultiThread();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				m1.printNum("a");
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				m2.printNum("b");
			}
		};
		
		t1.start();
		t2.start();
		
	}
	
}
