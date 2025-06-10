package test;

import main.java.db.DatabaseConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    private DatabaseConnector dbConnector;

    @BeforeEach
    void setUp() throws SQLException {
        dbConnector = new DatabaseConnector() {
            @Override
            public Connection getConnection() throws SQLException {
                return java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sakuramemory",
                        "root",
                        "mysql"
                );
            }
        };

        dbConnector.executeUpdate(
                "INSERT INTO Usuarios (nombre_usuario, contrasena) VALUES (?, ?)",
                "testUser",
                "testPass"
        );
    }

    @AfterEach
    void tearDown() throws SQLException {

        dbConnector.executeUpdate("DELETE FROM Puntuaciones");
        dbConnector.executeUpdate("DELETE FROM Usuarios");
    }

    @Test
    void testExecuteQueryUserExists() throws SQLException {

        boolean exists = dbConnector.executeQuery(
                "SELECT COUNT(*) FROM Usuarios WHERE nombre_usuario = ?",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getInt(1) > 0;
                },
                "testUser"
        );

        assertTrue(exists, "El usuario debería existir en la base de datos.");
    }

    @Test
    void testExecuteUpdateInsertUser() throws SQLException {

        dbConnector.executeUpdate(
                "INSERT INTO Usuarios (nombre_usuario, contrasena) VALUES (?, ?)",
                "newUser",
                "newPass"
        );

        // Verifica la inserción
        boolean exists = dbConnector.executeQuery(
                "SELECT COUNT(*) FROM Usuarios WHERE nombre_usuario = ?",
                rs -> {
                    rs.next();
                    return rs.getInt(1) > 0;
                },
                "newUser"
        );

        assertTrue(exists, "El usuario 'newUser' debería haberse insertado correctamente.");
    }

    @Test
    void testExecuteUpdateHandlesExceptions() {

        assertThrows(SQLException.class, () -> dbConnector.executeUpdate("INVALID SQL QUERY"));
    }

    @Test
    void testExecuteQueryEmptyTable() throws SQLException {

        dbConnector.executeUpdate("DELETE FROM Usuarios");


        boolean exists = dbConnector.executeQuery(
                "SELECT COUNT(*) FROM Usuarios",
                rs -> {
                    rs.next();
                    return rs.getInt(1) > 0;
                }
        );

        assertFalse(exists, "La tabla Usuarios debería estar vacía.");
    }
}