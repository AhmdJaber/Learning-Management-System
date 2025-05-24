package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase {
    public static Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/grading_system",
                    "root",
                    "1234578"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
