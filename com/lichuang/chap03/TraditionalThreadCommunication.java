package com.lichuang.chap03;

/**
 * 1.线程间互斥与同步通信
 *   要求线程间交替运行，并且互不干涉
 *   
 *   互不干涉，则用synchronized保证互斥；
 *   它们之间也要有联系以确保相互交替运行，所以它们之间需要有共同的数据
 *
 */
public class TraditionalThreadCommunication {
	
	public static void main(String[] args) {
		final Business business = new Business();
		
		communication(business);
	}
	
	public static void communication(final Business business){
		new Thread(new Runnable() {		
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					business.main(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					business.sub(i);
				}
			}
		}).start();
	}
}

// 多线程中的通例，必须要有一个通例。要用到共同数据的若干方法，应该被归于同一个类上面，这就是资源类
// 互斥不是写在线程上，而是写在要访问的资源上。线程的作用必须要足够单纯。
class Business{
	private boolean isShouldSub = false;//标记sub线程是否运行，这就是两个线程间的联系，共同维护
	
	// 用synchronized提供调用方法间的互斥
	public synchronized void sub(int i){
		while(!isShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=0;j<5;j++){
			System.out.println("The "+(i+1)+" LifeCycle, Sub "+(j+1)+" Booming");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isShouldSub = false;
		this.notify();
	}
	
	public synchronized void main(int i){
		while(isShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=0;j<10;j++){
			System.out.println("The "+(i+1)+" LifeCycle, Main "+(j+1)+" Booming");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isShouldSub = true;
		this.notify();
	}
}
