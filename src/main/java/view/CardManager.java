package main.java.view;

import main.java.model.SettingsManager;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    private static final String CARD_IMAGE_PREFIX = "card";
    private static final String CARD_BACK_IMAGE = "Reverso.png";
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 145;

    private final SettingsManager settings;

    public CardManager(SettingsManager settings) {
        this.settings = settings;
    }

    public List<JButton> generateCardPairs(int pairs) {
        List<JButton> cards = new ArrayList<>();
        List<Integer> cardIds = generateCardIds(pairs);

        cardIds.forEach(id -> {
            JButton card = createCard(id);
            cards.add(card);
        });

        return cards;
    }

    private List<Integer> generateCardIds(int pairs) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= pairs; i++) {
            ids.add(i);
            ids.add(i);
        }
        Collections.shuffle(ids);
        return ids;
    }

    private JButton createCard(int id) {
        ImageIcon frontIcon = loadScaledIcon(CARD_IMAGE_PREFIX + id + ".png", CARD_WIDTH, CARD_HEIGHT);
        ImageIcon backIcon = loadScaledIcon(CARD_BACK_IMAGE, CARD_WIDTH, CARD_HEIGHT);

        JButton card = new JButton(backIcon);
        card.setPreferredSize(new java.awt.Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setMaximumSize(new java.awt.Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setMinimumSize(new java.awt.Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setSize(new java.awt.Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setContentAreaFilled(false);
        card.setBorderPainted(false);
        card.setFocusPainted(false);

        card.putClientProperty("frontIcon", frontIcon);
        card.putClientProperty("backIcon", backIcon);
        card.putClientProperty("cardId", id);
        return card;
    }

    private ImageIcon loadScaledIcon(String imageName, int width, int height) {
        try {
            return main.java.utils.ImageLoader.loadIcon(imageName, width, height);
        } catch (main.java.exceptions.GameInitializationException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}