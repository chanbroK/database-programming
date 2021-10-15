package database.programming.week6;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class UPDATE {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        System.out.println("Connected to " + connection.getCatalog());

        stmt.executeUpdate("create table if not exists grade (id integer, name varchar(10), attendance double, midterm double, assignment double,final double);");

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

        // ALTER
        stmt.executeUpdate("alter table grade add column (total double);");
        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7));
        }
//        UPDATE
        stmt.executeUpdate("update grade set total = attendance + midterm + assignment + final;");

        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7));
        }

        // ALTER
        stmt.executeUpdate("alter table grade add column (last_update DATE);");
        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" + resultSet.getString(8));
        }
//        UPDATE
        stmt.executeUpdate("update grade set last_update= curdate();");

        resultSet = stmt.executeQuery("select * from grade");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" + resultSet.getDate(8));
        }


        stmt.executeUpdate("drop table grade");

        connection.close();


    }
}