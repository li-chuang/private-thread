package com.lichuang.chap04;

/**
 * 线程中的本地变量。
 * 如多人同时转账，相互之间应该互不干涉，尤其是其中的变量，绝对不可以共享，
 * 只能在线程范围内单独占用。
 *
 */
public class ThreadLocalTest {
	

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
