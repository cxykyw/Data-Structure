package com.kyw.connection;

/**
 * 静态内部类的单例模式
 * @author MR.W
 *
 */
public class InnerSingletion {

	private static class SingletionHolder{
		private static InnerSingletion single = new InnerSingletion();
	}
	
	public static InnerSingletion getInstance() {
		return SingletionHolder.single;
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				InnerSingletion is = new InnerSingletion();
				System.out.println(Thread.currentThread().getName()+":"+ is.getInstance().hashCode());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				InnerSingletion is = new InnerSingletion();
				System.out.println(Thread.currentThread().getName()+":"+ is.getInstance().hashCode());
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				InnerSingletion is = new InnerSingletion();
				System.out.println(Thread.currentThread().getName()+":"+ is.getInstance().hashCode());
			}
		},"t3");
		
		
		t1.start();
		t2.start();
		t3.start();
	}
}
