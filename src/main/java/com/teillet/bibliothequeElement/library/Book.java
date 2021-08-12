package com.teillet.bibliothequeElement.library;

import javafx.scene.image.Image;

public class Book extends Elements{
    public Book(String title, String path) {
        super(title, path);
    }

    @Override
    public Image getPreview() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }
}
