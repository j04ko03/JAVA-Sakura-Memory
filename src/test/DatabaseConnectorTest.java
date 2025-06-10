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
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Configurar conexión a la base de datos MySQL para pruebas
        dbConnector = new DatabaseConnector() {
            @Override
            public Connection getConnection() throws SQLException {
                return java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakura_test", // Cambia "sakura_test" según el nombre de tu BD
                    "root", // Usuario de MySQL
                    "mysql" // Contraseña de MySQL
                );
            }
        };

        // Inserta datos de prueba
        dbConnector.executeUpdate("INSERT INTO Users (username, password) VALUES ('testUser', 'testPass')");
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Limpia la base de datos de prueba para asegurar un estado limpio
        dbConnector.executeUpdate("DELETE FROM Users");
    }

    @Test
    void testExecuteQueryUserExists() throws SQLException {
        // Verifica si el usuario existe en la base de datos
        boolean exists = dbConnector.executeQuery(
            "SELECT COUNT(*) FROM Users WHERE username = ?",
            resultSet -> {
                resultSet.next(); // Avanza al primer resultado
                return resultSet.getInt(1) > 0;
            },
            "testUser" // Usuario que insertaste en setUp
        );

        assertTrue(exists, "El usuario debería existir en la base de datos.");
    }

    @Test
    void testExecuteUpdateInsertUser() throws SQLException {
        // Inserta un nuevo usuario en la base de datos
        dbConnector.executeUpdate("INSERT INTO Users (username, password) VALUES (?, ?)", "newUser", "newPass");

        // Verifica que el nuevo usuario fue insertado
        boolean exists = dbConnector.executeQuery(
            "SELECT COUNT(*) FROM Users WHERE username = ?",
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
        // Intenta ejecutar una consulta SQL inválida
        assertThrows(SQLException.class, () -> dbConnector.executeUpdate("INVALID SQL QUERY"));
    }

    @Test
    void testExecuteQueryEmptyTable() throws SQLException {
        // Limpia la tabla
        dbConnector.executeUpdate("DELETE FROM Users");

        // Verifica si la tabla está vacía
        boolean exists = dbConnector.executeQuery(
            "SELECT COUNT(*) FROM Users",
            rs -> {
                rs.next();
                return rs.getInt(1) > 0;
            }
        );

        assertFalse(exists, "La tabla debería estar vacía después de eliminar todos los usuarios.");
    }
}