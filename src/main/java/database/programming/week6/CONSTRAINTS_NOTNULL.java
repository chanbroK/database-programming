package database.programming.week6;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class CONSTRAINTS_NOTNULL {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        System.out.println("Connected to " + connection.getCatalog());

        stmt.executeUpdate("CREATE OR REPLACE TABLE account " + "(" + "account_number VARCHAR(50), "
                + "branch_name VARCHAR(50), " + "balance INTEGER " + ");");


        String sql = "DESCRIBE account;";
        showResultSet(sql, stmt.executeQuery(sql));

        stmt.executeUpdate("INSERT INTO account VALUES ('A-101', 'Downtown', 500);");
        stmt.executeUpdate("INSERT INTO account VALUES (null, 'Perryridge', 400);");
        stmt.executeUpdate("INSERT INTO account VALUES ('A-201', 'Brighton', null);");
        stmt.executeUpdate("INSERT INTO account VALUES (null, 'Mianus', null);");

        sql = "SELECT * FROM account;";
        showResultSet(sql, stmt.executeQuery(sql));

        // NOT NULL

        stmt.executeUpdate("CREATE OR REPLACE TABLE account " + "(" + "account_number VARCHAR(50) not null, "
                + "branch_name VARCHAR(50), " + "balance INTEGER " + ");");


        sql = "DESCRIBE account;";
        showResultSet(sql, stmt.executeQuery(sql));

        stmt.executeUpdate("INSERT INTO account VALUES ('A-101', 'Downtown', 500);");
        stmt.executeUpdate("INSERT INTO account VALUES (null, 'Perryridge', 400);");
        stmt.executeUpdate("INSERT INTO account VALUES ('A-201', 'Brighton', null);");
        stmt.executeUpdate("INSERT INTO account VALUES (null, 'Mianus', null);");

        sql = "SELECT * FROM account;";
        showResultSet(sql, stmt.executeQuery(sql));


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