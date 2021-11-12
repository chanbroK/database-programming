package database.programming.week10;

import java.util.concurrent.Semaphore;

public class ExampleWithSemaphore {
    public static int count = 0;

    // Semaphore 적용 -> 화장실 키와 같은 느낌, release 할때 까지 다른 쓰레드는 사용 불가
    public static Semaphore semaphore = new Semaphore(1);

    // 인자 : 허용 개수
    public static void main(String[] args) throws InterruptedException {
        // Thread마다 10000씩 증가
        new IncreaseThread2().start();
        new IncreaseThread2().start();
        new IncreaseThread2().start();
        new IncreaseThread2().start();

        // sleep 3 secs 각 쓰레드가 독립적으로 작동하기 때문에 main이 먼저 작업 끝나는 것을 방지
        Thread.sleep(5000);
        // Semaphore를 통해 40000이 찍힐 수 있음
        System.out.println(count);
    }
}
