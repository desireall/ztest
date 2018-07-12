package timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {
    public static void main(String[] args) {
        TestThreadFactory threadFactory = new TestThreadFactory("TcpServer");

//        long tickDuration, // tick的时长，也就是指针多久转一格
//        TimeUnit unit, // tickDuration的时间单位
//        int ticksPerWheel, // 一圈有几格
//        boolean leakDetection // 是否开启内存泄露检测
        final HashedWheelTimer timer = new HashedWheelTimer(threadFactory, 1, TimeUnit.MILLISECONDS,
                1000, true);

        long startTime = System.currentTimeMillis();

        TimerTask timerTask1 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  timeout 1 " + (System.currentTimeMillis() - startTime));
                Thread.sleep(10000);
            }
        };

        TimerTask timerTask2 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  timeout 2 " + (System.currentTimeMillis() - startTime));
            }
        };

        Timeout timeout1 = timer.newTimeout(timerTask1, 2000, TimeUnit.MILLISECONDS);
//        timeout1.cancel();
        System.out.println("task1 isCancelled = " + timeout1.isCancelled());
        System.out.println("task1 isExpired = " + timeout1.isExpired());

        Timeout timeout3 = timer.newTimeout(timerTask2, 2000, TimeUnit.MILLISECONDS);
        
        Timeout timeout2 = timer.newTimeout(timerTask2, 3000, TimeUnit.MILLISECONDS);
        System.out.println("task2 isCancelled = " + timeout2.isCancelled());
        System.out.println("task2 isExpired = " + timeout2.isExpired());


    }
}
