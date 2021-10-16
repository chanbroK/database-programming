package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class DataLoader {
    public static void loadLoan(String fileLoc) throws IOException, SQLException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Loan> list = new ArrayList<Loan>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String loanNumber = array[0];
            String branchName = array[1];
            int amount = Integer.parseInt(array[2]);
            list.add(new Loan(loanNumber, branchName, amount));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE loan (loan_number VARCHAR(10), branch_name VARCHAR(10), amount INTEGER(10))");
        // DML
        for (Loan loan : list) {
            stmt.executeUpdate("INSERT INTO loan VALUES ('" + loan.getLoanNumber() + "','" + loan.getBranchName() + "',"
                    + loan.getAmount() + ");");
        }
        // DQL
        ResultSet rs = stmt.executeQuery("SELECT * FROM loan;");
        while (rs.next()) {

            String loan_number = rs.getString("loan_number");
            String branch_name = rs.getString("branch_name");
            String amount = rs.getString("amount");
            System.out.println(loan_number + "\t" + branch_name + "\t" + amount);
        }
        connection.close();
    }

    public static void loadLoan2(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Loan2> list = new ArrayList<Loan2>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String loanNumber = array[0];
            String branchName = array[1];
            int amount = Integer.parseInt(array[2]);
            String date = array[3];

            list.add(new Loan2(loanNumber, branchName, amount, date));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE loan2 (loan_number VARCHAR(10), branch_name VARCHAR(10), amount INTEGER(10), date DATE)");
        // DML
        for (Loan2 loan : list) {
            stmt.executeUpdate("INSERT INTO loan2 VALUES ('" + loan.getLoanNumber() + "','" + loan.getBranchName()
                    + "'," + loan.getAmount() + ",'" + loan.getDate() + "');");
        }

        String sql = "SELECT * FROM loan2;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadBorrower(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Borrower> list = new ArrayList<Borrower>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split("\t");
            String customerName = array[0];
            String loanNumber = array[1];

            list.add(new Borrower(customerName, loanNumber));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate("CREATE OR REPLACE TABLE borrower (customer_name VARCHAR(30), loan_number VARCHAR(30))");
        // DML
        for (Borrower borrower : list) {
            stmt.executeUpdate("INSERT INTO borrower VALUES ('" + borrower.getCustomerName() + "','"
                    + borrower.getLoanNumber() + "');");
        }

        String sql = "SELECT * FROM borrower;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadDepositor(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Depositor> list = new ArrayList<Depositor>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String customerName = array[0];
            String accountNumber = array[1];

            list.add(new Depositor(customerName, accountNumber));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate("CREATE OR REPLACE TABLE depositor (customer_name VARCHAR(30), account_number VARCHAR(30))");
        // DML
        for (Depositor depositor : list) {
            stmt.executeUpdate("INSERT INTO depositor VALUES ('" + depositor.getCustomerName() + "','"
                    + depositor.getAccountNumber() + "');");
        }

        String sql = "SELECT * FROM depositor;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadPtWorks(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<PtWorks> list = new ArrayList<PtWorks>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String employeeName = array[0];
            String branchName = array[1];
            int salary = Integer.parseInt(array[2]);

            list.add(new PtWorks(employeeName, branchName, salary));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE pt_works (employee_name VARCHAR(30), branch_name VARCHAR(30), salary INTEGER)");
        // DML
        for (PtWorks depositor : list) {
            stmt.executeUpdate("INSERT INTO pt_works VALUES ('" + depositor.getEmployeeName() + "','"
                    + depositor.getBranchName() + "'," + depositor.getSalary() + ");");
        }

        String sql = "SELECT * FROM pt_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadAccount(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Account> list = new ArrayList<Account>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String accountNumber = array[0];
            String branchName = array[1];
            int balance = Integer.parseInt(array[2]);

            list.add(new Account(accountNumber, branchName, balance));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE account (account_number VARCHAR(30), branch_name VARCHAR(30), balance INTEGER)");
        // DML
        for (Account account : list) {
            stmt.executeUpdate("INSERT INTO account VALUES ('" + account.getAccountNumber() + "','"
                    + account.getBranchName() + "'," + account.getBalance() + ");");
        }

        String sql = "SELECT * FROM account;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadCustomer(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Customer> list = new ArrayList<Customer>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String customer = array[0];
            String customerStreet = array[1];
            String customerCity = array[2];

            list.add(new Customer(customer, customerStreet, customerCity));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE customer (customer VARCHAR(30), customer_street VARCHAR(30), customer_city VARCHAR(30))");
        // DML
        for (Customer customer : list) {
            stmt.executeUpdate("INSERT INTO customer VALUES ('" + customer.getCustomer() + "','"
                    + customer.getCustomerStreet() + "','" + customer.getCustomerCity() + "');");
        }

        String sql = "SELECT * FROM customer;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadEmployee(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<Employee> list = new ArrayList<Employee>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            if (line.startsWith("#"))
                continue;
            String[] array = line.split("\\|");
            String employeeName = array[0];
            String street = array[1];
            String city = array[2];

            list.add(new Employee(employeeName, street, city));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE employee (employee_name VARCHAR(30), street VARCHAR(30), city VARCHAR(30))");
        // DML
        for (Employee employee : list) {
            stmt.executeUpdate("INSERT INTO employee VALUES ('" + employee.getEmployeeName() + "','"
                    + employee.getStreet() + "','" + employee.getCity() + "');");
        }

        String sql = "SELECT * FROM employee;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }

    public static void loadFtWorks(String fileLoc) throws IOException, SQLException, ParseException {
        BufferedReader r = new BufferedReader(new FileReader(fileLoc));
        ArrayList<FtWorks> list = new ArrayList<FtWorks>();
        while (true) {
            String line = r.readLine();
            if (line == null)
                break;
            String[] array = line.split(",");
            String employeeName = array[0];
            String branchName = array[1];
            Integer salary = Integer.parseInt(array[2]);

            list.add(new FtWorks(employeeName, branchName, salary));
        }
        r.close();
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
        stmt.executeUpdate(
                "CREATE OR REPLACE TABLE ft_works (employee_name VARCHAR(30), branch_name VARCHAR(30), salary INTEGER)");
        // DML
        for (FtWorks ftWorks : list) {
            stmt.executeUpdate("INSERT INTO ft_works VALUES ('" + ftWorks.getEmployeeName() + "','"
                    + ftWorks.getBranchName() + "','" + ftWorks.getSalary() + "');");
        }

        String sql = "SELECT * FROM ft_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.close();
    }
}