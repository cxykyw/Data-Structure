package com.kyw.collection;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueue1 {

	public static void main(String[] args) throws Exception {
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();
		
		Task t1 = new Task();
		t1.setId(1);
		t1.setName("task 111");
		Task t4 = new Task();
		t4.setId(4);
		t4.setName("task 444");
		Task t2 = new Task();
		t2.setId(2);
		t2.setName("task 222");
		
		q.add(t2);
		q.add(t1);
		q.add(t4);
		
		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			System.out.println(task.getName());
		}
		
		System.out.println("容量："+q);
		System.out.println(q.take().getName());
		System.out.println("容量："+q);
		System.out.println(q.take().getName());
	}
	
}
