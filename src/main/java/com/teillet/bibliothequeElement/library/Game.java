package com.teillet.bibliothequeElement.library;

import javafx.scene.image.Image;

public class Game extends Elements{
    public Game(String title, String path) {
        super(title, path);
    }

    @Override
    public void play() {

    }

    @Override
    public Image getPreview() {
        return null;
    }

    @Override
    public String getType() {
        return "Game";
    }
}
