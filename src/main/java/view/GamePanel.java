package main.java.view;

import main.java.exceptions.GameInitializationException;
import main.java.model.GameMechanics;
import main.java.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GamePanel extends JPanel {
    private static final int TIMER_SECONDS = 60;
    private JLabel timerLabel;
    private Timer gameTimer;
    private JButton firstCard = null;
    private JButton secondCard = null;
    private boolean isProcessing = false;
    private final Runnable exitAction; // <-- Añade esto

    public GamePanel(List<JButton> cards, GameMechanics mechanics, Runnable exitAction) {
        setLayout(new BorderLayout());

        this.exitAction = exitAction; // <-- Guarda exitAction para usarlo donde quieras

        // Timer
        timerLabel = new JLabel(String.valueOf(TIMER_SECONDS), SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        add(timerLabel, BorderLayout.NORTH);

        // Cartas
        JPanel cardPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        cards.forEach(card -> {
            card.addActionListener(e -> handleCardClick(e, mechanics));
            cardPanel.add(card);
        });
        add(cardPanel, BorderLayout.CENTER);

        // Botón Salir
        JButton exitButton = new JButton("Salir al Menú");
        exitButton.addActionListener(e -> exitAction.run());
        add(exitButton, BorderLayout.SOUTH);

        startTimer();
    }

    private void startTimer() {
        gameTimer = new Timer(1000, e -> {
            int timeLeft = Integer.parseInt(timerLabel.getText());
            if (timeLeft > 0) {
                timerLabel.setText(String.valueOf(timeLeft - 1));
            } else {
                gameTimer.stop();
                JOptionPane.showMessageDialog(this, "¡Tiempo agotado!");
            }
        });
        gameTimer.start();
    }

    private void handleCardClick(ActionEvent e, GameMechanics mechanics) {
        if (isProcessing) return;

        JButton clickedCard = (JButton) e.getSource();
        flipCard(clickedCard, true); // Voltea la carta al hacer clic

        if (firstCard == null) {
            firstCard = clickedCard;
        } else {
            secondCard = clickedCard;
            isProcessing = true;
            javax.swing.Timer t = new Timer(1000, ev -> {
                checkMatch(mechanics);
                isProcessing = false;
                ((Timer) ev.getSource()).stop(); // Detén el timer después de llamar a checkMatch
            });
            t.setRepeats(false);
            t.start();
        }
    }

    private void flipCard(JButton card, boolean showFront) {
        try {
            ImageIcon icon = showFront
        ? (ImageIcon) card.getClientProperty("frontIcon")
        : ImageLoader.loadIcon("Reverso.png", 100, 100);
    card.setIcon(icon);
        } catch (GameInitializationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void checkMatch(GameMechanics mechanics) {
        int id1 = (int) firstCard.getClientProperty("cardId");
        int id2 = (int) secondCard.getClientProperty("cardId");

        if (id1 == id2) {
            mechanics.processMatch(id1); // Actualiza puntuación
            firstCard.setEnabled(false);
            secondCard.setEnabled(false);

            // Aquí comprobamos si todas las cartas están emparejadas
            boolean allMatched = true;
            Component[] components = ((JPanel) getComponent(1)).getComponents();
            for (Component c : components) {
                if (c instanceof JButton && c.isEnabled()) {
                    allMatched = false;
                    break;
                }
            }
            if (allMatched) {
                if (gameTimer != null) gameTimer.stop();
                JOptionPane.showMessageDialog(this, "¡Has completado todas las parejas!");
                // Llama a la acción de volver al menú/empezar juego
                SwingUtilities.invokeLater(exitAction);
            }
        } else {
            flipCard(firstCard, false); // Vuelve a voltear si no hay coincidencia
            flipCard(secondCard, false);
        }
        firstCard = null;
        secondCard = null;
    }

}