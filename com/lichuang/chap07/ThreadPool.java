package com.lichuang.chap07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池技术，和数据库连接池类似
 *
 */
public class ThreadPool {
	
	public static void main(String[] args) {
		// 开了10个任务，但使用的线程只有3个
		// ExecutorService threadPool = getFixedExecutorService();
		
		// 开了10个任务，使用10个线程，但线程使用后没有销毁，等待下次的使用
		//ExecutorService threadPool = getCachedExecutorService();
		
		// 名义上开了10个任务，但活动的线程只有一个，每次去线程池都取的同一个线程
//		ExecutorService threadPool = getSingleExecutorService();
//		executeMethod(threadPool);
//		shutDownThreadPool(threadPool);
		
		//getSchedueAtFixedRate();
		
		getScheduleWithFixedDelay();
	}

	private static void executeMethod(ExecutorService threadPool) {
		for(int i = 0;i<10;i++){
			final int task = i;
			threadPool.execute(new Runnable() {			
				@Override
				public void run() {
					for(int j=0;j<10;j++){
						try {
							Thread.sleep(1000);
							System.out.println(Thread.currentThread()+", task = "+(task+1)+" , j = "+(j+1));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
		}
	}
	
	// 固定大小线程池
	public static ExecutorService getFixedExecutorService(){
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		return threadPool;
	}
	
	// 缓存线程池
	public static ExecutorService getCachedExecutorService(){
		ExecutorService threadPool = Executors.newCachedThreadPool();
		return threadPool;
	}
	
	// 单一线程池，里面线程死掉后，立即重新启动一个
	public static ExecutorService getSingleExecutorService(){
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		return threadPool;
	}
	
	//线程池启动定时器
	//支持间隔重复任务的定时方式，但不支持绝对定时任务，需要转换为相对时间方式
	public static void getSchedueAtFixedRate(){
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {			
			@Override
			public void run() {
				System.out.println("Boobing!!!");
			}
		}, 10, 5, TimeUnit.SECONDS);
	}
	
	//
	public static void getScheduleWithFixedDelay(){
		Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("Booming!!");
			}
		}, 10, 5, TimeUnit.SECONDS);
	}
	
	public static void shutDownThreadPool(ExecutorService threadPool){
		threadPool.shutdownNow();
	}
}

}
