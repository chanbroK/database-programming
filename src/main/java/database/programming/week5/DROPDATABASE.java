package database.programming.week5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DROPDATABASE {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement sm = connection.createStatement();


        // DDL

//        sm.executeUpdate("DROP DATABASE db");
        // 없을 때 SQL 실행 시 ERROR 발생

        // IF EXISTS
//        sm.executeUpdate("DROP DATABASE IF EXISTS db");

//        System.out.println("[Warning]" + sm.getWarnings());
//        connection.close();

        // 중요한 DATABASE를 제외하고 모든 DATABASE를 지우고 싶을때
        ResultSet rs = sm.executeQuery("SHOW DATABASES ");
        while (rs.next()) {
            String dbName = rs.getString(1);
            if (dbName.equals("information_schema") || dbName.equals("mysql") || dbName.equals("performance_schema")) {
                System.out.println(dbName + " CAN NOT BE DELETED");
            } else {
                sm.executeUpdate("DROP DATABASE IF EXISTS " + dbName);
                System.out.println(dbName + "DELETED");
            }
        }
        connection.close();
    }
}
