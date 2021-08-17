package com.teillet.bibliothequeElement.interfaces.library;

import javafx.scene.image.Image;

public interface IElements {
    String getTitle();
    String getPath();
    Image  getPreview();
    Type getType();

    enum Type {
        Image, Game, Film, Book
    }
}
