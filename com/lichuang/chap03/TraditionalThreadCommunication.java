package com.lichuang.chap03;

/**
 * 1.线程间互斥与同步通信
 *   要求线程间交替运行，并且互不干涉
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

class Business{
	private boolean isShouldSub = false;
	
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
