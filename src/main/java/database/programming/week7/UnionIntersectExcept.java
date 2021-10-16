package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class UnionIntersectExcept {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        DataLoader.loadBorrower("D:\\project\\database-programming\\src\\main\\resources\\borrower.txt");
        DataLoader.loadDepositor("D:\\project\\database-programming\\src\\main\\resources\\depositor.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        DataRetriever.getAllData("borrower", stmt);

        String sql = "SELECT customer_name FROM borrower UNION SELECT customer_name FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM borrower UNION ALL SELECT customer_name FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM borrower UNION DISTINCT SELECT customer_name FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM borrower INTERSECT SELECT customer_name FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM borrower EXCEPT SELECT customer_name FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM depositor EXCEPT SELECT customer_name FROM borrower;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
