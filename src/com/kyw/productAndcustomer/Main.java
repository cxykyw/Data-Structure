package com.kyw.productAndcustomer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
		
		Producter p1 = new Producter(queue);
		Producter p2 = new Producter(queue);
		Producter p3 = new Producter(queue);
		
		Customer c1 = new Customer(queue);
		Customer c2 = new Customer(queue);
		Customer c3 = new Customer(queue);
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		
		Thread.sleep(3000);
		
		p1.stop();
		p2.stop();
		p3.stop();
		
		Thread.sleep(2000);
		
		service.shutdown();
	}
}
