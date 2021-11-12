package database.programming.week11.ex1;

import java.util.concurrent.locks.ReentrantLock;

public class ExampleWithReentrantLock {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock  = new ReentrantLock();

        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                count++;
                lock.unlock();
            }
        };
        // Thread마다 10000씩 증가
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        // sleep 3 secs 각 쓰레드가 독립적으로 작동하기 때문에 main이 먼저 작업 끝나는 것을 방지
        Thread.sleep(3000);
        // Semaphore를 통해 40000이 찍힐 수 있음
        System.out.println(count);
    }
}
