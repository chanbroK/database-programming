import java.sql.*;

public class App {
    public static void main(final String[] args) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", "root", "1234");
        // Each SQL can be executed a Statement instance
        final Statement stmt = connection.createStatement();
        // DDL
        try {
            stmt.executeUpdate("CREATE DATABASE db");
        } catch (final SQLException e) {
            System.out.println("DATABASE is already exist");
        }
        stmt.executeUpdate("USE db");
        try {
            stmt.executeUpdate("CREATE OR REPLACE TABLE loan (loan_number VARCHAR(10), branch_name VARCHAR(10), amount VARCHAR(10))");
        } catch (final SQLException e) {
            System.out.println("TABLE is already exist");
        }
        // DML
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-11', 'Round Hill', '900');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-14', 'Downtown', '1500');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-15', 'Perryridge', '1500');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-16', 'Perryridge', '1300');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-17', 'Downtown', '1000');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-23', 'Redwood', '2000');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-93', 'Mianus', '500');");

        //DQL
        final ResultSet resultSet = stmt.executeQuery("SELECT * FROM loan");
        while (resultSet.next()) {
            final String loan_number = resultSet.getString("loan_number");
            final String branch_name = resultSet.getString("branch_name");
            final String amount = resultSet.getString("amount");
            System.out.println(loan_number + "\t" + branch_name + "\t" + amount);
        }
        connection.close();
    }
}
