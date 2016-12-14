package com.lichuang.chap07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	public static void main(String[] args) {
		
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
