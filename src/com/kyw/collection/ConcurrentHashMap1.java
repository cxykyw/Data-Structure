package com.kyw.collection;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMap1 {

	public static void main(String[] args) throws Exception {
		//fun2();
		//fun3();
		//fun4();
		fun5();
		//fun5();
	}
	
	
	public void fun1() {
		
		CopyOnWriteArrayList<String> cwa = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cws = new CopyOnWriteArraySet<String>();
		
		cwa.add("aaa");
		cwa.remove(0);
	}
	
	public static void fun2() {
		
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k3", "vvvvv");
		chm.putIfAbsent("k4", "vvvvv");
		
		for(Entry<String, Object> map:chm.entrySet()) {
			System.out.println(map.getKey()+":"+map.getValue());
		}
	}
	
	public static void  fun3() {
		
		//高性能无阻塞无界队列
		//ConcurrentLinkedQueue中的add() 和 offer() 完全一样，都是往队列尾部添加元素
		//peek() 获取但不移除此队列的头；如果此队列为空，则返回 null
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();
		clq.offer("a");
		clq.offer("b");
		clq.offer("c");
		clq.offer("d");
		clq.add("e");
		
		System.out.println(clq.poll()); //a  从头部去除元素，并从队列里删除
		System.out.println(clq.size());	//4
		System.out.println(clq.peek());	//b
		System.out.println(clq.size());	//4
		
	}
	
	public static void fun4() throws Exception {
		
		//有界阻塞队列
		ArrayBlockingQueue<String> arr = new ArrayBlockingQueue<>(5);
		
		arr.add("a");
		arr.put("b");
		arr.put("c");
		arr.put("d");
		arr.put("e");
		
		System.out.println(arr.offer("f", 2, TimeUnit.SECONDS));
		
	}
	public static void fun5() throws Exception {
		
		//无界阻塞队列
		LinkedBlockingQueue<String> arr = new LinkedBlockingQueue<>();
		
		arr.add("a");
		arr.put("b");
		arr.put("c");
		arr.put("d");
		arr.put("e");
		arr.offer("1");
		
		System.out.println(arr.offer("f", 4, TimeUnit.SECONDS));
		System.out.println(arr.size());
		Iterator<String> iterator = arr.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
		
		SynchronousQueue<String> sq = new SynchronousQueue<>();
	}
}
