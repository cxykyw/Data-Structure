package com.kyw.productAndcustomer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producter implements Runnable {

	private volatile boolean isRunning = true;
	private BlockingQueue<PCData> queue;
	private static AtomicInteger count = new AtomicInteger();
	private static final int SLEEPTIME = 1000;

	public Producter(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		PCData data = null;
		Random r = new Random();
		System.out.println("start producting id:" + Thread.currentThread().getId());
		try {
			while (isRunning) {
				Thread.sleep(r.nextInt(SLEEPTIME));
				int id = count.incrementAndGet();
				
				data = new PCData(Integer.toString(id), "数据"+id); 
				System.out.println("当前线程："+Thread.currentThread().getName()+",获取了数据，id为："+id+",进行装载到公共的缓冲区中。。。");
				if(!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.err.println("加入队列失败");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop(){
		isRunning = false;
	}
}
