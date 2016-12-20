package com.lichuang.chap09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 原则：线程中不存放线程控制逻辑，控制逻辑都放在资源文件中
 * 
 *
 */
public class ReadWriterLockTest {
	
	public static void main(String[] args) {
		
	}

}
//此为共享数据，只能有一个线程能写该数据，但可以有多个线程同时读取该数据
class Queue{
	private Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Object get(){
		rwl.readLock().lock();
		try {
			Thread.sleep((long)Math.random()*1000);
			//return data;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
		return data;
	}
	
	public void put(Object data){
		rwl.writeLock().lock();
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
