package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class NaturalThetaJoin {

    public static void main(String[] args) throws Exception {
//
//        DataLoader.loadBorrower("f:\\borrower.txt");
//        DataLoader.loadLoan("f:\\loan.txt");
//        DataLoader.loadCustomer("f:\\customer.txt");
        DataLoader.loadDepositor("D:\\project\\database-programming\\src\\main\\resources\\depositor.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        String sql = "SELECT * FROM borrower;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM customer;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name, borrower.loan_number, branch_name, amount FROM borrower JOIN loan WHERE borrower.loan_number = loan.loan_number;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM borrower NATURAL JOIN loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM borrower JOIN loan ON borrower.loan_number = loan.loan_number;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM customer JOIN depositor ON customer.customer = depositor.customer_name;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
