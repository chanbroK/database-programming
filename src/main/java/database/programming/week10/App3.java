package database.programming.week10;

import java.util.concurrent.atomic.AtomicInteger;

public class App3 {
    //  AtomicInteger 사용
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // Thread마다 10000씩 증가
        new IncreaseThread3().start();
        new IncreaseThread3().start();
        new IncreaseThread3().start();
        new IncreaseThread3().start();

        // sleep 3 secs 각 쓰레드가 독립적으로 작동하기 때문에 main이 먼저 작업 끝나는 것을 방지
        Thread.sleep(5000);
        // Atomic 한 자료형
        System.out.println(count);
    }
}
