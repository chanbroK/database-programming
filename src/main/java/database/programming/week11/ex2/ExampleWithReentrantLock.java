package database.programming.week11.ex2;

import java.util.concurrent.locks.ReentrantLock;

public class ExampleWithReentrantLock {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock  = new ReentrantLock();

        long pre = System.currentTimeMillis();

        new IncreaseThreadWithLock(0,lock,pre).start();
        new IncreaseThreadWithLock(1,lock,pre).start();
        new IncreaseThreadWithLock(2,lock,pre).start();
        new IncreaseThreadWithLock(3,lock,pre).start();

        System.out.println("Main Terminated");
    }
}
