package database.programming.week5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SHOWDATABASES {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement sm = connection.createStatement();


        // DQL
        ResultSet rs = sm.executeQuery("SHOW DATABASES ");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        connection.close();
    }
}
