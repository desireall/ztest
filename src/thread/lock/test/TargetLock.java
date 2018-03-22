package thread.lock.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TargetLock {

	public final ReentrantLock lock = new ReentrantLock();
	
	public final Condition increaseCondition = lock.newCondition();
	public final Condition decreaseCondition = lock.newCondition();

	public int count;

	public void increase() {
		try {
			lock.lock();
			while(count >= 2){
				try {
					System.err.println(Thread.currentThread().getName()+"  await");
					increaseCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count++;
			System.err.println(Thread.currentThread().getName() + ": " + count);
			decreaseCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public void decrease() {
		try {
			lock.lock();
			while(count <= 0){
				try {
					System.err.println(Thread.currentThread().getName()+"  await");
					decreaseCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count--;
			System.err.println(Thread.currentThread().getName() + ": " + count);
			increaseCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final TargetLock target = new TargetLock();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					target.increase();
				}
			}
		});
		t1.setName("increase1");

		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					target.increase();
				}
			}
		});
		t4.setName("increase2");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					target.decrease();
				}
			}
		});
		t2.setName("decrease1");

		Thread t3 = new Thread(new Runnable() {

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
		t4.start();
	}
}
