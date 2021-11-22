package database.programming.week12;

import model.Connector;
import model.DataRetriever;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class P4PhantomReadT1 {

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector("root", "1234", "db");
        Connection connection = conn.getConnection();
        Statement stmt = conn.getStmt();

        stmt.executeUpdate("CREATE OR REPLACE TABLE users (id INTEGER, name VARCHAR(50), age INTEGER); ");
        stmt.executeUpdate("INSERT INTO users VALUES (1,'Joe',20), (2, 'Jill', 25);");

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        String sql = "SELECT * FROM users WHERE age BETWEEN 10 and 30;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT * FROM users  WHERE age BETWEEN 10 and 30;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.commit();

    }

}

