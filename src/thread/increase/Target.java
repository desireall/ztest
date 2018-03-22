package thread.increase;

import java.util.Random;

public class Target {

	public int count;
	
	public synchronized void increase(){
//		if(count >= 2){     //开多个相同的线程的时候存在虚假唤醒的情况
		while(count >= 2){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.err.println(Thread.currentThread().getName()+": "+ count);
		notifyAll();
	}
	
	public synchronized void decrease(){
//		if(count <= 0){
		while(count <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		System.err.println(Thread.currentThread().getName()+" , count : "+ count);
		notifyAll();
	}
	
	
	public static void main(String[] args) {
		final Target target = new Target();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for (int i = 0; i < 60; i++) {
					target.increase();
				}
			}
		});
		t1.setName("increase");

		
		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					target.decrease();
				}
			}
		});
		t2.setName("decrease1");
		
		Thread t3 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					target.decrease();
				}
			}
		});
		t3.setName("decrease2");
		
		t1.start();
		t2.start();
		t3.start();
		
		if(t1.isAlive()){
			t1.interrupt();
		}
	}
}
