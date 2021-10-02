package database.programming.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

    public static void main(String[] args) throws Exception {
        // DatLoading :)
        BufferedReader r = new BufferedReader(new FileReader("D:\\project\\database-programming\\src\\main\\resources\\서울특별시 광진구_자전거도로_20210126.csv", Charset.forName("euc-kr")));
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
//        System.out.println(dataList);

        // create database and table :)
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "1234");
        Statement sm = connection.createStatement();
        sm.executeUpdate("CREATE OR REPLACE DATABASE DB CHARACTER SET = 'euckr' COLLATE ='euckr_Korean_ci'");
        sm.executeUpdate("USE DB");
        sm.executeUpdate("CREATE OR REPLACE TABLE bicycle_load(name VARCHAR(50), type VARCHAR(50), start_point VARCHAR(50), end_point VARCHAR(50),stopover_point VARCHAR(50),is_two_way BOOLEAN,length DOUBLE, normal_width DOUBLE, bicycle_width DOUBLE, install_year INTEGER, texture VARCHAR(50) )");


        // store data :)
        for (BicycleLoad data : dataList) {
            String sql = "INSERT INTO bicycle_load VALUES (" + "'" + data.getName() + "','" + data.getType() + "','" + data.getStartPoint() + "','" + data.getEndPoint() + "','" + data.getStopoverPoint() + "'," + data.getTwoWay() + ',' + data.getLength() + ',' + data.getNormalWidth() + ',' + data.getBicycleWidth() + ',' + data.getInstallYear() + ",'" + data.getTexture() + "')";
            System.out.println(sql);
            sm.executeUpdate(sql);
        }


        // data query 1. 방향이 양방향인 도로의 개수는? :)
        ResultSet rs = sm.executeQuery("SELECT COUNT(*) FROM bicycle_load WHERE is_two_way=true ");
        while (rs.next()) {
            System.out.println("[개수] = " + rs.getString(1));
        }

        // data query 2. 길이가 3km인 도로의 노선명,기점,종점,도로재질을 설치연도로 오름차순 정렬 :)
        rs = sm.executeQuery("SELECT name,start_point,end_point,texture FROM bicycle_load WHERE length>=3 ORDER BY install_year ");
        while (rs.next()) {
            System.out.println("[노선명] = " + rs.getString(1) + " [기점] = " + rs.getString(2) + " [종점] = " + rs.getString(3) + " [도로재질] = " + rs.getString(4));
        }
    }
}
