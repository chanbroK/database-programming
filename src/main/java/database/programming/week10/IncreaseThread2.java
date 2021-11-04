package database.programming.week10;

public class IncreaseThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                App2.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            App2.count++;
            App2.semaphore.release();
        }
    }
}
