package thread.lock.test;

import java.util.concurrent.locks.ReentrantLock;

public class Test_Lock {
    static ReentrantLock lock = new ReentrantLock();
    static class Runner implements Runnable {
        @Override
        public void run() {
            lock.lock(); //设置断点
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        lock.lock();
        Thread t1 = new Thread(new Runner());
        t1.start();
        Thread t2 = new Thread(new Runner());
        t2.start();
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
