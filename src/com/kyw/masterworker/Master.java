 package com.kyw.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//1.应该有一个承装任务的集合
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
	
	//2.使用hashmap去承装所有的worker对象
	private HashMap<String,Thread> workers = new HashMap<String,Thread>();
	
	//3.使用一个容器承装每一个worker并发执行任务的结果集
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	//4.构造方法
	public Master(Worker worker,int workerCount) {
		
		//每一个worker都需要有Master的引用workQueue用于任务的领取，resultMap用于任务的提交
		worker.setWorkerQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		
		for(int i=0;i<workerCount;i++) {
			//key表示worker的名字，value表示线程执行对象
			workers.put("子节点"+Integer.toString(i),new Thread(worker));
		}
		
	}
	
	//5.提交方法
	public void submit(Task task) {
		this.workQueue.add(task);
	}
	
	//6.需要有一个执行的方法，启动所有的线程
	public void execute() {
		for(Entry<String, Thread>  me:workers.entrySet()) {
			me.getValue().start();
		}
	}

	//7.判断是否全部执行完毕
	public boolean isComplete() {
		for(Entry<String, Thread>  me:workers.entrySet()) {
			if(me.getValue().getState() == Thread.State.TERMINATED) {
				return true;
			}
		}
		return false;
	}
	
	//8.返回结果集数据
	public int getResult() {
		int ret = 0;
		for(Map.Entry<String, Object> me : resultMap.entrySet()) {
			ret += (Integer)me.getValue();
		}
		return ret;
	}
	
}








