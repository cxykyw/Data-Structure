package com.kyw.collection;

import java.util.Iterator;
import java.util.Vector;

public class Tickets {

	public static void main(String[] args) {
		
		final Vector<String> tickets = new Vector<String>();
		
		for(int i=1;i<=1000;i++) {
			tickets.add("火车票"+i);
		}
		
		for(Iterator iterator = tickets.iterator();iterator.hasNext();) {
			String str = (String) iterator.next();
			tickets.remove(20);
		}
		
		for(int i=1;i<=10;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						if(tickets.isEmpty()) break;
						System.out.println(Thread.currentThread().getName()+"===="+tickets.remove(0));
					}
				}
			}).start();
		}
	}
	
}
