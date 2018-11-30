package com.kyw.sync1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * volatile关键字没有原子性，只具备多个线程之间的可见性
 * @author MR.W
 *
 */
public class VolatileAtomic extends Thread{

	//private static volatile int count;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	private static void addCount() {
		for(int i=0;i<1000;i++) {
			//count++;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	
	@Override
	public void run() {
		addCount();
	}
	
	
	public static void main(String[] args) {
		
		VolatileAtomic[] va = new VolatileAtomic[10];
		for(int i=0;i<10;i++) {
			va[i] = new VolatileAtomic();
		}
		
		for (int i = 0; i < va.length; i++) {
			va[i].start();
		}
	}
}
