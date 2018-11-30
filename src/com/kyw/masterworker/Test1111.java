package com.kyw.masterworker;

import java.util.Random;

public class Test1111 {
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("本机器可用线程数："+Runtime.getRuntime().availableProcessors());
		Master m = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
		
		Random r = new Random();
		for(int i=1;i<=100;i++) {
			Task t = new Task();
			t.setId(i);
			t.setName("任务"+i);
			t.setPrice(r.nextInt(1000));
			m.submit(t);
		}
		m.execute();
		
		long start =  System.currentTimeMillis();
		
		while(true) {
			if(m.isComplete()) {
				long end = System.currentTimeMillis() - start;
				
				int ret = m.getResult();
				Thread.sleep(1000);
				System.out.println("最终结果："+ret+",执行耗时："+end);
				break;
			}
		}
	}

}
