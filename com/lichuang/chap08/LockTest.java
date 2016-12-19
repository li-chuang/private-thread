package com.lichuang.chap08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//线程锁Lock，与synchronized相比更加面向对象
//作用实际上与synchronized类似
public class LockTest {

	public static void main(String[] args) {
		final Business business = new Business(5);
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true){
					business.increse();
				}
			}
		}).start();
		new Thread(new Runnable() {		
			@Override
			public void run() {
				while(true){
					business.descrese();
				}
			}
		}).start();
	}
}

class Business{
	private int number = 0;
	Lock lock = new ReentrantLock();
	
	public Business(int number){
		this.number = number;
	}
	
	public void increse(){
		lock.lock();
		try {
			number++;
			System.out.println(Thread.currentThread()+" , number = "+ number+" , "+System.currentTimeMillis());
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void descrese(){
		lock.lock();
		try {
			number--;
			System.out.println(Thread.currentThread()+" , number = "+ number+" , "+System.currentTimeMillis());
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
}
