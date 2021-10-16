package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class ORDERBYLIMIT {
    public static void main(String[] args) throws SQLException, IOException, ParseException {

        DataLoader.loadLoan2("D:\\project\\database-programming\\src\\main\\resources\\loan2.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        DataRetriever.getAllData("loan2", stmt);

        String sql = "SELECT loan_number, date FROM loan2 ORDER BY date ASC;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT loan_number, date FROM loan2 ORDER BY date DESC;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT loan_number, date FROM loan2 ORDER BY MONTH(date);";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT loan_number, date FROM loan2 ORDER BY MONTH(date) DESC, YEAR(date) DESC;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT loan_number, date FROM loan2 ORDER BY MONTH(date) DESC, YEAR(date) DESC LIMIT 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
