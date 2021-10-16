package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class DMLDQL {
    public static void main(String[] args) throws Exception {

        DataLoader.loadLoan("f:\\loan.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        String sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "INSERT INTO loan SELECT * FROM loan";
        stmt.executeUpdate(sql);

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        DataLoader.loadLoan("f:\\loan.txt");
        System.out.println("clear");

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "INSERT INTO loan SELECT * FROM loan WHERE amount > 1000";
        stmt.executeUpdate(sql);

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        DataLoader.loadLoan("f:\\loan.txt");
        System.out.println("clear");

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "INSERT INTO loan (loan_number,branch_name) SELECT loan_number, branch_name FROM loan";
        stmt.executeUpdate(sql);

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));


        sql = "DELETE FROM loan";
        stmt.executeUpdate(sql);

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        DataLoader.loadLoan("f:\\loan.txt");
        System.out.println("clear");

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "DELETE FROM loan WHERE amount = 2000";
        stmt.executeUpdate(sql);

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));
        conn.shutdown();

    }
}
