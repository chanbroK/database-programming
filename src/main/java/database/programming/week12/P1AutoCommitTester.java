package database.programming.week12;


import model.Connector;
import model.DataRetriever;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class P1AutoCommitTester {

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();
        // P1 AutoCommit autocommit을 끈 상태로 실행하는 도중에 Tester 실행시 정상 작동 Read Uncommited level
        conn.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        // P1 AutoCommit autocommit을 끈 상태로 실행하는 도중에 Tester 실행시 정상 작동 X Serializable level
        // conn.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        String sql = "SELECT * FROM student";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));
    }
}


