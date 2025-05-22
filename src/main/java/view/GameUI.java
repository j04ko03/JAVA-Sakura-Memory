package main.java.view;

import main.java.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

public class GameUI {
    private static final int CARD_PAIRS = 5;
    private static final int TIMER_SECONDS = 60;

    private final JFrame mainFrame;
    private final GameMechanics gameMechanics;
    private final CardManager cardManager;

    public GameUI(GameConfig config) {
        this.mainFrame = new JFrame("Sakura Memory Game");
        this.gameMechanics = new GameMechanics(config);
        this.cardManager = new CardManager(config.getSettings());
    }

    public void initialize() {
        configureMainFrame();
        showMainMenu();
    }

    private void configureMainFrame() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
    }

    private void showMainMenu() {
        MainMenuPanel menuPanel = new MainMenuPanel(this::startGame);
        mainFrame.setContentPane(menuPanel);
        mainFrame.setVisible(true);
    }


    private void startGame(ActionEvent actionEvent) {
        GamePanel gamePanel = new GamePanel(
                cardManager.generateCardPairs(CARD_PAIRS),
                gameMechanics,
                this::showMainMenu
        );

        mainFrame.setContentPane(gamePanel);
        mainFrame.revalidate();
    }
}