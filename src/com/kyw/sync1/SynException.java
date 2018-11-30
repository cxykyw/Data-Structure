package com.kyw.sync1;

public class SynException {

	private int i = 0;
	public synchronized void operation() {
		while(true) {
			try {
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+",i = "+i);
				if(i == 10) {
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception loginfo, i="+i);
				throw new RuntimeException();
				//InterruptedException
				//continue;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		final SynException se = new SynException();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
	
}
