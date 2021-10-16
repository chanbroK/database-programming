package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class JOIN {
    public static void main(String[] args) throws Exception {

        DataLoader.loadBorrower("D:\\project\\database-programming\\src\\main\\resources\\borrower.txt");
        DataLoader.loadCustomer("D:\\project\\database-programming\\src\\main\\resources\\customer2.txt");
        DataLoader.loadAccount("D:\\project\\database-programming\\src\\main\\resources\\account.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        DataRetriever.getAllData("borrower", stmt);
        DataRetriever.getAllData("loan", stmt);

        String sql = "SELECT * FROM borrower CROSS JOIN loan;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM borrower CROSS JOIN loan WHERE branch_name = 'Perryridge';";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM borrower CROSS JOIN loan WHERE branch_name = 'Perryridge' AND borrower.loan_number = loan.loan_number;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_name FROM borrower CROSS JOIN loan WHERE branch_name = 'Perryridge' AND borrower.loan_number = loan.loan_number;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM account;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM account AS a1 CROSS JOIN account AS a2;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT DISTINCT a1.balance FROM account AS a1 CROSS JOIN account AS a2 WHERE a1.balance < a2.balance;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT balance FROM account  EXCEPT SELECT DISTINCT a1.balance FROM account AS a1 CROSS JOIN account AS a2 WHERE a1.balance < a2.balance;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM customer;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM customer WHERE customer='Smith';";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer_street, customer_city FROM customer WHERE customer='Smith';";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM customer CROSS JOIN (SELECT customer_street, customer_city FROM customer WHERE customer='Smith') AS smith_address;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));
//        (SELECT customer_street, customer_city FROM customer WHERE customer='Smith') AS smith_address 소괄호를 통해 select문을 묶을 수 있다.
        sql = "SELECT * FROM customer CROSS JOIN (SELECT customer_street, customer_city FROM customer WHERE customer='Smith') AS smith_address WHERE customer.customer_street=smith_address.customer_street AND customer.customer_city = smith_address.customer_city;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT customer FROM customer CROSS JOIN (SELECT customer_street, customer_city FROM customer WHERE customer='Smith') AS smith_address WHERE customer.customer_street=smith_address.customer_street AND customer.customer_city = smith_address.customer_city;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));
        conn.shutdown();

    }
}
