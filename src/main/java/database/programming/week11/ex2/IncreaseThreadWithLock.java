package database.programming.week11.ex2;

import java.util.concurrent.locks.ReentrantLock;

public class IncreaseThreadWithLock extends Thread {

    private int indent;
    private ReentrantLock lock;
    private long startTime;

    public IncreaseThreadWithLock(int indent, ReentrantLock lock, long startTime) {
        this.indent = indent;
        this.lock = lock;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        String indentation = "";
        for (int i = 0; i < 10; i++) {
            lock.lock();

            // READ
            int tCount = ExampleWithReentrantLock.count;
            tCount++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            indentation = "";
            for (int j = 0; j < indent; j++) {
                indentation += "------";
            }
            System.out.println(indentation + " " + (System.currentTimeMillis() - startTime) + "R");

            // WRITE
            ExampleWithReentrantLock.count = tCount;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
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

