package com.lichuang.chap02;

import java.io.File;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


/**
 * 2.定时器
 *   有两个方法类，Timer为定时器类，TimerTask为任务类，
 *   Timer类通过调度方法执行任务类
 *   任务类可以有多个，即有多个任务，至于执行那个，就由业务决定了。
 *
 */
public class TraditionalTimer {

	public static void main(String[] args) {
		/*Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 23);		
		executeDate(new MyTimerTask(),cal);*/
		
		executeDelay(new MyTimerTask(),5000);
	}

	//在预定的时间点执行任务，以后可以有定时发邮件，定时保存数据库数据等。作用很广
	public static void executeDate(TimerTask task,Calendar cal){
		new Timer().schedule(task, cal.getTime());
	}	
	
	// 推迟预定的时间执行任务
	public static void executeDelay(TimerTask task,long delay){
		new Timer().schedule(task, delay);
	}
	
}
//任务类，自己想写多少任务都可以
class MyTimerTask extends TimerTask{
	@Override
	public void run() {
		System.out.println(Calendar.getInstance().getTime());
		playMusic();
		System.out.println("Booming! ");
	}
	
	//Java本身只支持mid格式的音频，用JMF可以播放其他格式的音频文件
	public void playMusic(){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("music/255.mid"));
		    AudioFormat aif = ais.getFormat();
		    SourceDataLine sdl = null;
		    DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
		    sdl = (SourceDataLine) AudioSystem.getLine(info);
		    sdl.open(aif);
		    sdl.start();
		    int nByte = 0;
		    byte[] buffer = new byte[128];
		    while (nByte != -1){
			    nByte = ais.read(buffer, 0, 128);
			    if (nByte >= 0){
			     sdl.write(buffer, 0, nByte);
			    }
		    }
		    sdl.stop();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		/*//URL mus=this.getClass().getResource("/music/Keepup.wav");
		URL mus=null;
		try {
			mus = new File("/董贞 - 两两相忘.mp3").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		AudioClip snd =JApplet.newAudioClip(mus);
		//snd.loop();
		snd.play();*/
	}
}
