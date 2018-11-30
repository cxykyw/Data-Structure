package com.kyw.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁
 * @author MR.W
 *
 */
public class LockTest {
	
	public static void main(String[] args) {
		Outputter1 out = new Outputter1();
		new Thread() {
			public void run() {
				out.output("zhangsan");
			}
		}.start();
		new Thread() {
			public void run() {
				out.output("lisi");
			}
		}.start();
	}
}


class Outputter1{
	
	private Lock lock = new ReentrantLock();
	public void output(String name) {
		//线程输出方法
		//得到锁
		lock.lock();
		try {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i)+"==");
			}
		} finally {
			// 手动释放锁
			lock.unlock();
		}
	}
	
}