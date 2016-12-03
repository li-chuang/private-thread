package com.lichuang.chap01;

/**
 * 1.最基础的线程使用
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
