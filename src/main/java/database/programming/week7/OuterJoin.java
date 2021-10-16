package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class OuterJoin {
    public static void main(String[] args) throws Exception {

        DataLoader.loadEmployee("f:\\employee.txt");
        DataLoader.loadFtWorks("f:\\ft_works.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        String sql = "SELECT * FROM employee;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM ft_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM employee NATURAL JOIN ft_works;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM employee LEFT OUTER JOIN ft_works ON employee.employee_name = ft_works.employee_name ;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM employee RIGHT OUTER JOIN ft_works ON employee.employee_name = ft_works.employee_name ;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM employee LEFT OUTER JOIN ft_works ON employee.employee_name = ft_works.employee_name UNION SELECT * FROM employee RIGHT OUTER JOIN ft_works ON employee.employee_name = ft_works.employee_name ;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
