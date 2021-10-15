package database.programming.week6;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class CONSTRAINTS_CHECK {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        System.out.println("Connected to " + connection.getCatalog());

        // PRIMARY KEY AND CHECK
        stmt.executeUpdate("CREATE OR REPLACE TABLE student " + "(" + "name VARCHAR(50) NOT NULL, "
                + "student_id VARCHAR(50), " + "degree_level VARCHAR(50), " + " PRIMARY KEY (student_id), "
                + "CHECK ( degree_level in ('Bachelors','Masters','Doctorate'))" + ");");

        String sql = "DESCRIBE account;";
        showResultSet(sql, stmt.executeQuery(sql));

        stmt.executeUpdate("INSERT INTO student VALUES ('Jack', '615458', 'Doctorate');");
        stmt.executeUpdate("INSERT INTO student VALUES ('Stevie', '2XXXXXXX', 'Bachelors');");

        sql = "SELECT * FROM student;";
        showResultSet(sql, stmt.executeQuery(sql));

        // Check
        // EXCEPTION
        // stmt.executeUpdate("INSERT INTO student VALUES ('Sting', '2YYYYYYY', 'MSc');");

        stmt.executeUpdate("CREATE OR REPLACE TABLE account " + "(" + "account_number VARCHAR(50) null, "
                + "branch_name VARCHAR(50), " + "balance INTEGER not null, " + " CHECK ( balance >= 0 ) " + ");");

        sql = "DESCRIBE account;";
        showResultSet(sql, stmt.executeQuery(sql));

        // balance < 0 is not allowed
        stmt.executeUpdate("INSERT INTO account VALUES ('A-101', 'Downtown', 500);");
        stmt.executeUpdate("INSERT INTO account VALUES ('A-102', 'Perryridge', 400);");

        sql = "SELECT * FROM account;";
        showResultSet(sql, stmt.executeQuery(sql));

        // EXCEPTION
        stmt.executeUpdate("INSERT INTO account VALUES ('A-201', 'Brighton', -300);");
        connection.close();
    }

    public static void showResultSet(String sql, ResultSet rs) throws SQLException {
        System.out.println();
        System.out.println("SQL> " + sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.print(rsmd.getColumnTypeName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rs.getObject(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}