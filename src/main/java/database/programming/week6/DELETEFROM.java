package database.programming.week6;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class DELETEFROM {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        System.out.println("Connected to " + connection.getCatalog());

        stmt.executeUpdate("create table if not exists grade (id integer, name varchar(10), attendance double, midterm double, assinment double,final double);");

        ResultSet resultSet = stmt.executeQuery("DESCRIBE grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
        }

        // INSERT INTO
        stmt.executeUpdate("insert into grade values (615453,'J.B',10,30,30,30),(615453,'J.B',10,30,30,30),(615453,'J.B',10,30,30,30);");

        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6));
        }

        // DELETE FROM
        stmt.executeUpdate("delete from grade;");
        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6));
        }
        System.out.println("DONE");
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