package com.lichuang.chap06;

/**
 * 多线程共享数据
 * 例如售票，共用一个票池，所有线程都在这个票池中取票
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		ShareData shareData = new ShareData(5);
		new Thread(new Product(shareData)).start();
		new Thread(new Comsumer(shareData)).start();
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
			while(true){
				shareData.increase();
				Thread.sleep(1000);
			}
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
			while(true){
				shareData.descrease();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

class ShareData{
	private int number= 0;
	
	public ShareData(int number){
		this.number = number;
	}
	
	public synchronized void increase() throws Exception{
		if(number > 10){
			System.out.println("number数量大于10，休息一会儿！");
			Thread.sleep(5000);
		}else{
			number++;
			System.out.println("number++"+ ", number = "+number);
		}
	}
	public synchronized void descrease() throws Exception{
		if(number < 1){
			System.out.println("number数量小于1，休息一会儿！");
			Thread.sleep(5000);
		}else {
			number--;
			System.out.println("number--"+ ", number = "+number);
		}
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
