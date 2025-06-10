package main.java.db;

import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sakuramemory";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void executeUpdate(String query, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }

    public <T> T executeQuery(String query, ResultProcessor<T> processor, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                return processor.process(rs);
            }
        }
    }

    // Interfaz funcional para procesar ResultSet
    @FunctionalInterface
    public interface ResultProcessor<T> {
        T process(ResultSet rs) throws SQLException;
    }
}