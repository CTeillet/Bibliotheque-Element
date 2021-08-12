package com.teillet.bibliothequeElement.library;

import java.io.File;

public class Image extends Elements{
    public Image(String title, String path) {
        super(title, path);
    }

    @Override
    public javafx.scene.image.Image getPreview() {
        try {
            File file = new File(getPath());
            String localUrl = file.toURI().toURL().toString();
            return new javafx.scene.image.Image(localUrl);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getType() {
        return null;
    }
}
