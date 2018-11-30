package com.kyw.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd2 {

	private volatile static List list = new ArrayList();

	public void add() {
		list.add("kyw");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd2 la = new ListAdd2();

		// 定义一个Object作为锁
		//final Object lock = new Object();

		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//synchronized (lock) {
						for (int i = 0; i < 10; i++) {
							la.add();
							System.out.println("当前线程" + Thread.currentThread().getName() + "添加一个新元素.....");
							Thread.sleep(500);
							if (la.size() == 5) {
								System.out.println("已发出通知..");
								//lock.notify();
								countDownLatch.countDown();
							}
						}
					//}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				//synchronized (lock) {

					if (la.size() != 5) {
						try {
							//lock.wait();
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + ", 线程停止...");
					throw new RuntimeException();

				}
			//}
		}, "t2");

		t2.start();
		t1.start();
	}

}
