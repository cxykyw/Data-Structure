package com.kyw.connection;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞的消息队列
 * 
 * @author MR.W
 *
 */
public class MyQueue {

	// 1.需要集合来承装元素
	private final LinkedList<Object> list = new LinkedList<Object>();

	// 2.需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);

	// 3.需要上限和下限
	private final int minSize = 0;

	private final int maxSize;

	// 构造方法来处理最大值
	public MyQueue(int size) {
		this.maxSize = size;
	}

	// 初始化一个对象用于加锁
	private final Object lock = new Object();

	// put
	public void put(Object obj) {
		synchronized (lock) {
			while (count.get() == this.maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 添加元素
			list.add(obj);
			// 计数器累加
			count.incrementAndGet();
			// 通知另外一个线程
			lock.notify();
			System.out.println("新加入的元素为：" + obj);
		}
	}

	// take
	public Object take() {
		Object ret = null;
		synchronized (lock) {
			while (count.get() == minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 移除元素
			ret = list.removeFirst();

			// 计数器递减
			count.decrementAndGet();

			// 唤醒另外一个线程
			lock.notify();
		}

		return ret;
	}

	public int getSize() {
		return count.get();
	}

	public static void main(String[] args) {
		
		//在jdk8以后匿名内部类调用外部变量的时候，变量不用加final关键字
		final MyQueue mq = new MyQueue(5);
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");

		System.out.println("当前容器的长度：" + mq.getSize());

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		},"t1");

		t1.start();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				Object o1 = mq.take();
				System.out.println("移除的元素为："+o1);
				Object o2 = mq.take();
				System.out.println("移除的元素为："+o2);
				
			}
		},"t2");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
	}
}
