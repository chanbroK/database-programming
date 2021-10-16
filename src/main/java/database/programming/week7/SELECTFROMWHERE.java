package database.programming.week7;

import model.Connector;
import model.DataLoader;
import model.DataRetriever;

import java.sql.Statement;

public class SELECTFROMWHERE {
    public static void main(String[] args) throws Exception {
        DataLoader.loadLoan2("D:\\project\\database-programming\\src\\main\\resources\\loan2.txt");

        Connector connector = new Connector("root", "1234", "db");

        Statement stmt = connector.getStmt();

        String sql = "select * from loan2 where loan_number='L-11'";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where amount>1000";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where amount>800*2";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where year(date)=2020";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where month(date)=8";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where branch_name ='Perryridge' and amount<1400";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select loan_number from loan2 where dayofmonth(date)=29";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where loan_number like 'L-1_'";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where loan_number like '%Hill'";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where date like '%-03-%'";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "select * from loan2 where date like '%-03-%' or date like '%-08-%'";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM loan2 WHERE date REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}$';";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));
        connector.shutdown();
    }
}
