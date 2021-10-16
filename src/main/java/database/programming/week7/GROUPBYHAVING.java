package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class GROUPBYHAVING {
    public static void main(String[] args) throws Exception {

        DataLoader.loadPtWorks("D:\\project\\database-programming\\src\\main\\resources\\ptworks.txt");

        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        DataRetriever.getAllData("pt_works", stmt);

        String sql = "SELECT branch_name, AVG(salary) FROM pt_works GROUP BY branch_name;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT branch_name, AVG(salary) AS avg_salary FROM pt_works GROUP BY branch_name;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT branch_name FROM pt_works GROUP BY branch_name HAVING AVG(salary) >= 2000;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT AVG(amount) FROM loan GROUP BY branch_name HAVING COUNT(*) > 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        conn.shutdown();

    }
}
