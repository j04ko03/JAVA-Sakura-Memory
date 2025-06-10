package test;

import main.java.model.SettingsManager;
import main.java.view.CardManager;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardManagerTest {
    @Test
    void testGenerateCardPairs() {
        // Configuramos el administrador de cartas
        SettingsManager settings = new SettingsManager();
        CardManager cardManager = new CardManager(settings);

        // Generamos pares de cartas
        int cardPairs = 5;
        List<JButton> cards = cardManager.generateCardPairs(cardPairs);

        // Verificamos que el número total de cartas es correcto (pares * 2)
        assertEquals(cardPairs * 2, cards.size());

        // Verificamos que cada carta contiene un id y propiedades asignadas correctamente
        for (JButton card : cards) {
            assertNotNull(card.getClientProperty("cardId"));
            assertNotNull(card.getClientProperty("frontIcon"));
            assertNotNull(card.getClientProperty("backIcon"));
        }
    }
}