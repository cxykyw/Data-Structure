package com.kyw.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	
	@Override
	public void run() {
		while(true) {
			Task input = this.workQueue.poll();
			if(input == null) break;
			
			Object output = handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}

	public Object handle(Task input) {
		Object output = null;
		try {
			//表示任务的耗时，可能是查询数据库，也可能数据的加工
			Thread.sleep(500);
			
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
