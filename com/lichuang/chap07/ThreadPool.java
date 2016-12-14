package com.lichuang.chap07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	public static void main(String[] args) {
		// 开了10个任务，但使用的线程只有3个
		// ExecutorService threadPool = getFixedExecutorService();
		// 开了10个任务，使用10个线程，但线程使用后没有销毁，等待下次的使用
		ExecutorService threadPool = getCachedExecutorService();
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
	
	public static void shutDownThreadPool(ExecutorService threadPool){
		threadPool.shutdownNow();
	}

}
