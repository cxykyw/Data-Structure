package com.kyw.sync1;

/**
 * 脏读
 * @author MR.W
 *
 */
public class DirtyRead {

	private String username = "kyw";
	private String password = "123";
	
	public synchronized void setValue(String username,String password) {
		this.username = username;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.password = password;
		
		System.out.println("setValue的最终结果是：username = "+username+",password = "+password);
	}
	
	public synchronized void getValue() {
		System.out.println("getValue的最终结果是：username = "+this.username+",password = "+this.password);
	}
	
	public static void main(String[] args) throws Exception {
		
		DirtyRead dr = new DirtyRead();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				dr.setValue("kyw2222", "2222");
			}
		};
		t1.start();
		Thread.sleep(1000);
		
		dr.getValue();
	}
}
