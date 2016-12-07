package com.lichuang.chap06;

/**
 * 多线程共享数据
 * 例如售票，共用一个票池，所有线程都在这个票池中取票
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(new Product(shareData)).start();
		new Thread(new Comsumer(shareData)).start();
	}
	
}

class Product implements Runnable{
	private ShareData shareData;
	
	public Product(ShareData shareData){
		this.shareData = shareData;
	}
	@Override
	public void run() {
		try {
			shareData.increase();
			System.out.println("Product : "+shareData.getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

class Comsumer implements Runnable{
	private ShareData shareData;
	
	public Comsumer(ShareData shareData) {
		this.shareData = shareData;
	}
	@Override
	public void run() {
		try {
			shareData.descrease();
			System.out.println("Comsumer : "+shareData.getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

class ShareData{
	private int number= 0;
	
	public synchronized void increase() throws Exception{
		if(number > 10){
			Thread.sleep(5000);
		}else{
			number++;
		}
	}
	public synchronized void descrease() throws Exception{
		if(number < 1){
			Thread.sleep(5000);
		}else {
			number--;
		}
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
