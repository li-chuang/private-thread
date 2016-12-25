package com.lichuang.chap12;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 集合点，集齐预设的数量后再运行
 * 作用类似于集齐7颗龙珠召唤神龙。。
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		final Business business = new Business(0);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					String name = business.getCount();
					try {  
			            Thread.sleep(1000 * (new Random()).nextInt(8));  
			            System.out.println(name + " 准备好了...");  
			            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。  
			            cb.await();  
			        } catch (InterruptedException e) {  
			            e.printStackTrace();  
			        } catch (BrokenBarrierException e) {  
			            e.printStackTrace();  
			        }  
			        System.out.println(name + " 起跑！");  
					//business.getCount();
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}

}

class Business {
	private int count = 0;

	public Business() {

	}

	public Business(int count) {
		this.count = count;
	}

	public void increase() {
		count++;
	}

	public void descrease() {
		count--;
	}

	public String getCount() {
		increase();
		System.out.println(Thread.currentThread() + ",已经进入第" + count + "个并发！");
		return count+"号";
	}
}
