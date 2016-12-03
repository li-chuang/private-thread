package com.lichuang.chap01;

/**
 * 1.最基础的线程使用
 *   这里有两个方法类Tread与Runnable，一个是线程类，一个是任务类，分工相当明确
 *   希望在以后的工作中，有关线程的使用时也可以按这样明确的分工写代码。
 *
 */
public class TraditionalThread {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {			
			@Override
			public void run() {
				System.out.println("Hello World!!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
