package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private Connection connection;
    private Statement stmt;

    public Connector(String id, String password, String dbName) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, password);
        // Each SQL can be executed with a Statement instance
        stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
        stmt.executeUpdate("USE db");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void shutdown() throws SQLException {
        connection.close();
    }

}
