package main.java.utils;

import main.java.exceptions.GameInitializationException;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {
    private static final String IMAGE_PATH = "/images/";

    public static ImageIcon loadIcon(String filename, int width, int height) throws GameInitializationException {
        URL resource = ImageLoader.class.getResource(IMAGE_PATH + filename);
        if (resource == null) {
            throw new GameInitializationException("Imagen no encontrada: " + filename);
        }
        return new ImageIcon(
                new ImageIcon(resource).getImage()
                        .getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
    }
}