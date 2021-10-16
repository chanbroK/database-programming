package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class SELECTFROM {
    public static void main(String[] args) throws IOException, ParseException, SQLException {

        DataLoader.loadLoan("D:\\project\\database-programming\\src\\main\\resources\\loan.txt");

        Connector connector = new Connector("root", "1234", "db");
        Statement stmt = connector.getStmt();

        DataRetriever.getAllData("loan", stmt);

        String sql = "select * from loan";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select loan_number from loan";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select loan_number, branch_name from loan";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select distinct branch_name from loan";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connector.shutdown();
    }
}