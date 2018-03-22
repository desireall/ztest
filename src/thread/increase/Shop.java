package thread.increase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {

	List<Integer> id =new ArrayList<>();
	
	
	public synchronized void add(Integer value){
		if(id.size() >= 5){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		id.add(value);
		System.err.println(Thread.currentThread().getName()+", size :"+id.size());
		notify();
	}
	
	
	public synchronized void remove(){
		if(id.size() == 0){
			try{
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		id.remove(0);
		System.err.println(Thread.currentThread().getName()+", size :"+id.size());
		notify();
	}
	
	
	public static void main(String[] args) {
		final Shop shop = new Shop();
		Thread in = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					shop.add(new Random().nextInt(100));
				}
			}
		});
		in.setName("in");

		Thread buy = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					shop.remove();
				}
			}
		});
		buy.setName("buy");
		
		buy.start();
		in.start();
		
	}
	
}
