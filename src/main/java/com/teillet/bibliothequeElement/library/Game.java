package com.teillet.bibliothequeElement.library;

import javafx.scene.image.Image;

public class Game extends Elements{
    public Game(String title, String path) {
        super(title, path);
    }

    @Override
    public Image getPreview() {
        return null;
    }

    @Override
    public Type getType() {
        return Type.Game;
    }
}
