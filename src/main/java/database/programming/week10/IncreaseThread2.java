package database.programming.week10;

public class IncreaseThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                ExampleWithSemaphore.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ExampleWithSemaphore.count++;
            ExampleWithSemaphore.semaphore.release();
        }
    }
}
