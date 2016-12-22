package com.lichuang.chap10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 执行条件condition
 * 类似于wait/notify的作用，用于线程通信
 * 只不过wait/notify只能实现一路的等待/唤醒，condition可以实现多路
 *
 */
public class BoundeBufferTest {

}

/**
 * 下面的模型类似一个寻呼机。
 * 有一个items构成的堆栈作为信息呼入池，
 * 信息可以进入池中，信息也可以从池中呼出
 *
 */
class BoundeBuffer{
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	final Object[] items = new Object[100];
	int putptr,takeptr,count;
	
	public void put(Object x){
		lock.lock();
		try {
			while(count == items.length){
				notFull.await();// 不为满条件进入等待状态
			}
			items[putptr] = x;
			if(++putptr == items.length){
				putptr = 0;
			}
			++count;
			notEmpty.signal();//不为空条件唤醒
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public Object take(){
		lock.lock();
		Object x = null;
		try {
			while(count == 0){
				notEmpty.await();//不为空条件等待
			}
			x = items[takeptr];
			if(++takeptr == items.length){
				takeptr = 0;
			}
			--count;
			notFull.signal();//不为满条件唤醒
			//return x;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return x;
	}
}


