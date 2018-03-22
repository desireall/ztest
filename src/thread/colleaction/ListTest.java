package thread.colleaction;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	
	
	
	public static void main(String[] args) {
		
		final List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		
		
		Runnable a = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				      try {
		                    Thread.sleep(1000);
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
				}
			}
		};

		Runnable b = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.remove(3);
				
//				for (int i = 0; i < 100; i++) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					list.remove(new Integer(i));
//				}
			}
		};
		
		
		new Thread(a).start();
		new Thread(b).start();
		
	}
}
