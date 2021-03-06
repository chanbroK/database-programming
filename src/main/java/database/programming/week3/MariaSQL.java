package database.programming.week3;

import java.sql.*;

public class MariaSQL {
    public static void main(final String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "1234");
        // Each SQL can be executed a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        try {
            stmt.executeUpdate("CREATE OR REPLACE DATABASE db");
        } catch (final SQLException e) {
            System.out.println("DATABASE is already exist");
        }
        stmt.executeUpdate("USE db");
        try {
            stmt.executeUpdate("CREATE OR REPLACE TABLE loan (loan_number VARCHAR(10), branch_name VARCHAR(10), amount VARCHAR(10))");
        } catch (final SQLException e) {
            System.out.println("TABLE is already exist");
        }
        // DML
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-11', 'Round Hill', '900')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-14', 'Downtown', '1500')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-15', 'Perryridge', '1500')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-16', 'Perryridge', '1300')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-17', 'Downtown', '1000')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-23', 'Redwood', '2000')");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-93', 'Mianus', '500')");

        //DQL
        ResultSet resultSet = stmt.executeQuery("SELECT loan_number, branch_name, amount FROM loan");
        while (resultSet.next()) {
            String loan_number = resultSet.getString(1);
            String branch_name = resultSet.getString("branch_name");
            String amount = resultSet.getString("amount");
            System.out.println(loan_number + "\t" + branch_name + "\t" + amount);
        }

        resultSet = stmt.executeQuery("SELECT AVG(amount) FROM loan");
        while (resultSet.next()) {
            String avg = resultSet.getString("AVG(amount)");
            System.out.println("AVG(amount) = " + avg);
        }
        connection.close();
    }
}
