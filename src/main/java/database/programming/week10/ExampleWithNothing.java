package database.programming.week10;

public class ExampleWithNothing {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // Thread마다 10000씩 증가
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();

        // sleep 3 secs 각 쓰레드가 독립적으로 작동하기 때문에 main이 먼저 작업 끝나는 것을 방지
        Thread.sleep(5000);
        // 40000이 되지 않는다.
        System.out.println(count);
    }
}
