package com.lichuang.chap05;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 同一线程内数据共享
 * 变量线程间不共享，线程内共享
 * 也可以将此类看做ThreadLocal的手动实现 
 *
 */
public class ThreadScopeShareData {

	private static int data = 0;
	
	private static Map<Thread,Integer> threadData = new HashMap<Thread,Integer>();
	
	public static void main(String[] args) {
		for(int i = 0; i< 2; i++){
			threadLocal();
		}
	}
	
	public static void threadLocal(){
		new Thread(new Runnable() {		
			@Override
			public void run() {
				int data = new Random().nextInt(99);
				System.out.println(Thread.currentThread().getName()+data);
				threadData.put(Thread.currentThread(), data);
				new First().get();
				new Second().get();
			}
		}).start();
	}
	
	static class First{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println(data);
		}
	}
	
	static class Second{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println(data);
		}
	}
}

