package com.teillet.bibliothequeElement.library;

import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;

public class Image extends Elements{
    public Image(String title, String path) {
        super(title, path);
    }

    @Override
    public void play() {

    }

    @Override
    public javafx.scene.image.Image getPreview() {
        try {
            if (previewGenerated()){
                File file = new File(generateNamePreview());
                String localUrl = file.toURI().toURL().toString();
                return new javafx.scene.image.Image(localUrl);
            }else{
                String nameFile = generateNamePreview();
                File outputFile = new File(nameFile);
                File inputFile = new File(getPath());
                Thumbnailator.createThumbnail(inputFile, outputFile, 100, 100);
                String localUrl = outputFile.toURI().toURL().toString();
                return new javafx.scene.image.Image(localUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getType() {
        return "Image";
    }
}
