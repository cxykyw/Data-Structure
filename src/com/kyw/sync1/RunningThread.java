package com.kyw.sync1;

public class RunningThread extends Thread{

	private volatile boolean flag = true;

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	@Override
	public void run() {
		System.out.println("进入run方法....");
		while(flag == true) {
			//...
		}
		System.out.println("结束run方法。。。");
	}	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		RunningThread rt = new RunningThread();
		rt.start();
		
		Thread.sleep(1000);
		rt.setFlag(false);
		System.out.println("flag已经被修改为false了");
		
		Thread.sleep(1000);
		System.out.println(rt.flag);
	}
}
