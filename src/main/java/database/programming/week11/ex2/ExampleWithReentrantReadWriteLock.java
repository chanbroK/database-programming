package database.programming.week11.ex2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExampleWithReentrantReadWriteLock {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock  = new ReentrantReadWriteLock();

        long pre = System.currentTimeMillis();

        new IncreaseThreadWithReadWriteLock(0,lock,pre).start();
        new IncreaseThreadWithReadWriteLock(1,lock,pre).start();
        new IncreaseThreadWithReadWriteLock(2,lock,pre).start();
        new IncreaseThreadWithReadWriteLock(3,lock,pre).start();

        System.out.println("Main Terminated");
    }
}
