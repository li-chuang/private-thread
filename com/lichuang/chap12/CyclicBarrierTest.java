package com.lichuang.chap12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 集合点，集齐预设的数量后再运行
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
					//int result = cb.getNumberWaiting();
					// cb.
					System.out.println("--------A--------");
					business.getCount();
					System.out.println("--------B--------");
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

	public void getCount() {
		increase();
		System.out.println(Thread.currentThread() + ",已经进入第" + count + "个并发！");
	}
}
