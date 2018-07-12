package timer;

import io.netty.channel.DefaultEventLoop;

import java.util.concurrent.TimeUnit;

public class NettyTaskTest3 {
    public static void main(String[] args) {
        DefaultEventLoop handlerGroup = new DefaultEventLoop(new TestThreadFactory("TcpServer"));

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " task-1!");
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " task-2!");
            }
        };
        handlerGroup.schedule(task1, 1000, TimeUnit.MILLISECONDS);
        handlerGroup.schedule(task2, 3000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
