package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataRetriever {
    public static void getAllData(String tableName, Statement stmt) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

        System.out.println();

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