package com.lichuang.chap11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * 信号灯
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);
		final Business business = new Business(0);
		
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						sp.acquire();
						//System.out.println("已经进入第"+(i+1)+"个并发！");
						business.getCount();
						sp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		
	}
}

class Business{
	private int count=0;
	public Business(){
		
	}
	public Business(int count){
		this.count = count;
	}
	public void increase(){
		count++;
	}
	public void descrease(){
		count--;
	}
	
	public void getCount(){
		increase();
		System.out.println("已经进入第"+count+"个并发！");
	}
}
