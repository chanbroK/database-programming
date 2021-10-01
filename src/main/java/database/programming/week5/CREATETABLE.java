package database.programming.week5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CREATETABLE {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement sm = connection.createStatement();


        sm.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        // 현재 사용중인 DB 이름 반환
        sm.executeUpdate("USE db");
//        String sql = "CREATE OR REPLACE TABLE customer2(customer VARCHAR(50),customer_street VARCHAR(50),customer_city VARCHAR(50),latitude DOUBLE,longitude DOUBLE, last_update DATE)";
        String sql = "CREATE TABLE IF NOT EXISTS customer2(customer VARCHAR(50),customer_street VARCHAR(50),customer_city VARCHAR(50),latitude DOUBLE,longitude DOUBLE, last_update DATE)";
        sm.executeUpdate(sql);
        System.out.println("[Warning]" + sm.getWarnings());

        ResultSet rs = sm.executeQuery("DESCRIBE customer2");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }


        connection.close();
    }
}
