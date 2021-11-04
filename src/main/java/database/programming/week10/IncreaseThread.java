package database.programming.week10;

public class IncreaseThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            App.count++;
        }
    }
}
