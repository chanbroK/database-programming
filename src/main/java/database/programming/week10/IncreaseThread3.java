package database.programming.week10;

public class IncreaseThread3 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            App3.count.getAndIncrement();
        }
    }
}
