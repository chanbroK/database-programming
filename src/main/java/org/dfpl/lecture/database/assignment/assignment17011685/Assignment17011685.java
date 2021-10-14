package org.dfpl.lecture.database.assignment.assignment17011685;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Assignment17011685 {

    public static void main(String[] args) throws Exception {
        // DatLoading :)
        BufferedReader r = new BufferedReader(new FileReader("D:\\project\\database-programming\\src\\main\\java\\org\\dfpl\\lecture\\database\\assignment\\assignment17011685\\assignment.csv", Charset.forName("euc-kr")));
        // remove column's name row :)
        r.readLine();
        List<BicycleLoad> dataList = new ArrayList<>();
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            String[] lineArr = line.split(",");
            String name = lineArr[0];
            String type = lineArr[1];
            String startPoint = lineArr[2];
            String endPoint = lineArr[3];
            String stopoverPoint = lineArr[4];
            Boolean isTwoWay = lineArr[5].equals("양방");
            Double length = Double.parseDouble(lineArr[6]);
            Double normalWidth = Double.parseDouble(lineArr[7]);
            Double bicycleWidth = Double.parseDouble(lineArr[8]);
            Integer installYear = Integer.parseInt(lineArr[9]);
            String texture = lineArr[10];
            BicycleLoad bicycleLoad = new BicycleLoad(name, type, startPoint, endPoint, stopoverPoint, isTwoWay, length, normalWidth, bicycleWidth, installYear, texture);
            dataList.add(bicycleLoad);
        }
        System.out.println(dataList);
        System.out.println("\n");
        // create database and table :)
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "1234");
        Statement sm = connection.createStatement();
        sm.executeUpdate("CREATE OR REPLACE DATABASE DB CHARACTER SET = 'euckr' COLLATE ='euckr_Korean_ci'");
        sm.executeUpdate("USE DB");
        sm.executeUpdate("CREATE OR REPLACE TABLE bicycle_load(name VARCHAR(50), type VARCHAR(50), start_point VARCHAR(50), end_point VARCHAR(50),stopover_point VARCHAR(50),is_two_way BOOLEAN,length DOUBLE, normal_width DOUBLE, bicycle_width DOUBLE, install_year INTEGER, texture VARCHAR(50) )");

        // describe table :)
        ResultSet rs = sm.executeQuery("DESCRIBE bicycle_load");
        System.out.println("SQL> DESCRIBE bicycle");
        System.out.println("COLUMN_NAME\tCOLUMN_TYPE\tIS_NULLABLE\tCOLUMN_KEY\tCOLUMN_DEFAULT\tEXTRA");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
        }
        System.out.println("\n");

        // store data :)
        for (BicycleLoad data : dataList) {
            String sql = "INSERT INTO bicycle_load VALUES (" + "'" + data.getName() + "','" + data.getType() + "','" + data.getStartPoint() + "','" + data.getEndPoint() + "','" + data.getStopoverPoint() + "'," + data.getTwoWay() + ',' + data.getLength() + ',' + data.getNormalWidth() + ',' + data.getBicycleWidth() + ',' + data.getInstallYear() + ",'" + data.getTexture() + "')";
            sm.executeUpdate(sql);
        }


        // data query 1. 방향이 양방향인 도로의 개수는? :)
        rs = sm.executeQuery("SELECT COUNT(*) FROM bicycle_load WHERE is_two_way=true ");
        System.out.println("SQL> SELECT COUNT(*) FROM bicycle_load WHERE is_two_way=true");
        while (rs.next()) {
            System.out.println("[count(*)] = " + rs.getString(1));
        }
        System.out.println("\n");
        // data query 2. 길이가 3km인 도로의 노선명,기점,종점,도로재질,설치연도르를 설치연도로 오름차순 정렬 :)
        System.out.println("SQL> SELECT name,start_point,end_point,texture,install_year FROM bicycle_load WHERE length>=3 ORDER BY install_year ");
        rs = sm.executeQuery("SELECT name,start_point,end_point,texture,install_year FROM bicycle_load WHERE length>=3 ORDER BY install_year ");
        while (rs.next()) {
            System.out.println("[name] = " + rs.getString(1) + " [start_point] = " + rs.getString(2) + " [end_point] = " + rs.getString(3) + " [texture] = " + rs.getString(4) + " [install_year] = " + rs.getString(5));
        }
    }
}
