package main.java.controller;

import main.java.db.DatabaseConnector;
import main.java.model.*;
import main.java.view.GameUI;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SakuraMemoryGame {
    public SakuraMemoryGame() {
        SwingUtilities.invokeLater(this::initialize);
    }

    private void startGame() {
        GameConfig config = new GameConfig.Builder().build();
        GameUI gameUI = new GameUI(config);
        gameUI.initialize();
    }

    private void initialize() {
        try {
            Object[] options = {"Iniciar Sesión", "Registrarse"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Bienvenido a Sakura Memory",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 0) {
                if (handleLogin()) {
                    startGame();
                }
            } else if (choice == 1) {
                if (handleRegistration()) {
                    startGame();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private boolean handleLogin() {
        String username = JOptionPane.showInputDialog("Usuario:");
        String password = JOptionPane.showInputDialog("Contraseña:");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña no pueden estar vacíos");
            return false;
        }

        if (authenticate(username, password)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            return false;
        }
    }

    private boolean handleRegistration() {
        String username = JOptionPane.showInputDialog("Nuevo Usuario:");
        String password = JOptionPane.showInputDialog("Nueva Contraseña:");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña no pueden estar vacíos");
            return false;
        }

        try {
            DatabaseConnector db = new DatabaseConnector();

            // Verificar si el usuario ya existe
            boolean exists = db.executeQuery(
            "SELECT id_usuario FROM Usuarios WHERE nombre_usuario = ?",
            rs -> rs.next(),
            username
        );
        if (exists) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
        }

        db.executeUpdate(
            "INSERT INTO Usuarios (nombre_usuario, contrasena) VALUES (?, ?)",
            username, password
        );
        JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        return true;
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + ex.getMessage());
        return false;
    }
}

    private boolean authenticate(String username, String password) {
        try {
            DatabaseConnector db = new DatabaseConnector();
            return db.executeQuery(
                    "SELECT id_usuario FROM Usuarios WHERE nombre_usuario = ? AND contrasena = ?",
                    rs -> rs.next(),
                    username, password
            );
        } catch (SQLException ex) {
            return false;
        }
    }


    public static void main(String[] args) {
        new SakuraMemoryGame();
    }
}