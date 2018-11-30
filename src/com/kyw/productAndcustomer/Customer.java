package com.kyw.productAndcustomer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable{

	private BlockingQueue<PCData> queue;
	private static final int SLEEPTIME = 1000;
	
	public Customer(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		
		System.out.println("start customer id :"+Thread.currentThread().getId());
		
		Random r = new Random();
		
		try {
			
			while(true) {
				PCData data = queue.take();
				if(data != null) {
					Thread.sleep(r.nextInt(SLEEPTIME));
					System.out.println("当前消费线程："+Thread.currentThread().getName()+",消费成功，消费数据为id："+data.getId());
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
	}

	
	
	
}
