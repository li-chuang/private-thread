package com.lichuang.chap09;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读写锁比单纯的Lock锁更进一步，比Lock控制更精细，区分了不同的隔离级别
 * 原则：线程中不存放线程控制逻辑，控制逻辑都放在资源文件中
 * 
 *
 */
public class ReadWriterLockTest {
	
	public static void main(String[] args) {
		final Queue queue = new Queue();
		for(int i= 0;i<10 ; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						Object obj = queue.get();
						System.out.println(Thread.currentThread()+" , obj = "+obj);
					}
				}
			}).start();
			new Thread(new Runnable() {		
				@Override
				public void run() {
					while(true){
						int data = new Random().nextInt(100);
						queue.put(data);
						System.out.println(Thread.currentThread()+" , put data to Queue. data = "+data);
					}
				}
			}).start();
		}
	}

}
//此为共享数据，只能有一个线程能写该数据，但可以有多个线程同时读取该数据
class Queue{
	private Object data = null;
	//此为读写锁，加读锁后可以多线程同时读，但加写锁后，只能一个线程单独写
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Object get(){
		rwl.readLock().lock();//加读锁
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
		return data;
	}
	
	public void put(Object data){
		rwl.writeLock().lock();//加写锁
		try {
			Thread.sleep((long) (Math.random()*1000));
			this.data = data;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			rwl.writeLock().unlock();
		}
	}
}
