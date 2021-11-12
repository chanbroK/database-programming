package database.programming.week11.ex2;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IncreaseThreadWithReadWriteLock extends Thread {

    private int indent;
    private ReentrantReadWriteLock lock;
    private long startTime;

    public IncreaseThreadWithReadWriteLock(int indent, ReentrantReadWriteLock lock, long startTime) {
        this.indent = indent;
        this.lock = lock;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        String indentation = "";
        for (int i = 0; i < 10; i++) {

            // READ
            lock.readLock().lock();
            int tCount = ExampleWithReentrantLock.count;
            tCount++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readLock().unlock();
            indentation = "";
            for (int j = 0; j < indent; j++) {
                indentation += "------";
            }
            System.out.println(indentation + " " + (System.currentTimeMillis() - startTime) + "R");

            // WRITE
            lock.writeLock().lock();
            ExampleWithReentrantLock.count = tCount;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.writeLock().unlock();
            indentation = "";
            for (int j = 0; j < indent; j++) {
                indentation += "------";
            }
            System.out.println(indentation + " " + (System.currentTimeMillis() - startTime) + "W");

        }
        indentation = "";
        for (int j = 0; j < indent; j++) {
            indentation += "------";
        }
        System.out.println(indentation +" "+ (System.currentTimeMillis() - startTime));
    }
}

