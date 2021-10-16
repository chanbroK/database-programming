package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class AggregationFunction {
    public static void main(String[] args) throws SQLException, IOException, ParseException {

        DataLoader.loadPtWorks("D:\\project\\database-programming\\src\\main\\resources\\ptworks.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        DataRetriever.getAllData("pt_works", stmt);

        String sql = "SELECT SUM(salary) FROM pt_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT COUNT(branch_name) FROM pt_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT COUNT(DISTINCT branch_name) FROM pt_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
