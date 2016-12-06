package com.lichuang.chap04;

import java.util.Random;

/**
 * 线程中的本地变量。
 * 如多人同时转账，相互之间应该互不干涉，尤其是其中的变量，绝对不可以共享，
 * 只能在线程范围内单独占用。
 *
 */
public class ThreadLocalTest {
	
	/*private static ThreadLocal<MyThreadScopeData> myThreadScopeData = 
			new ThreadLocal<MyThreadScopeData>();*/
	
	public static void threadLocal(){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				int data = new Random().nextInt(99);
				MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
				myData.setName("name"+ data);
				myData.setAge(""+data);
				new First().get();
				new Second().get();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		for(int i = 0; i <2; i++){ // 启动两个线程
			threadLocal();
		}
	}

}

class First{
	public void get(){
		MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
		System.out.println(myData.getName()+" : "+myData.getAge());
	}
}

class Second{
	public void get(){
		MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
		System.out.println(myData.getName()+" : "+myData.getAge());
	}
}

class MyThreadScopeData{
	private MyThreadScopeData(){
		
	}
	
	public static MyThreadScopeData getThreadInstance(){
		MyThreadScopeData instance = map.get();
		if( instance == null){
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	// 此处的意义在于，对于不同的Thread,都有一个其专属的MyThreadScopeData
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
	
	private String name;
	private String age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}		
	
}
