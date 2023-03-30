package bbdd;

import java.sql.*;

class ConnectionManager {
    public Connection conn;
    private final String URL = "jdbc:mysql://localhost:3306/artysophy";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
