package database.programming.week5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class USEDATABASE {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement sm = connection.createStatement();


        sm.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        // 현재 사용중인 DB 이름 반환
        System.out.println("[Current DB]" + connection.getCatalog());
        sm.executeUpdate("USE db");
        System.out.println("[Current DB]" + connection.getCatalog());

        connection.close();
    }
}
