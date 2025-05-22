package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(ActionListener startGameAction) {
        setLayout(new GridBagLayout());
        JButton startButton = new JButton("EMPEZAR JUEGO");
        startButton.setFont(new Font("Roboto", Font.BOLD, 24));
        startButton.addActionListener(startGameAction);
        add(startButton);
    }
}