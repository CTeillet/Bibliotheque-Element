package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.IFilm;
import com.teillet.bibliothequeElement.utils.FfmpegUse;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Film extends Elements implements IFilm {
    private final String director;
    private final String duration;
    private final List<String> actors;
    private final Object foo = new Object();


    public Film(String title, String path){
        super(title, path);
        this.director = "";
        this.duration = "0";
        this.actors = new ArrayList<>();
    }

    public Film(String title, String path, String director, String duration, List<String> actors) {
        super(title, path);
        this.director = director;
        this.duration=duration;
        this.actors=actors;
    }

    public String getDirector() {
        return director;
    }

    public String getDuration() {
        return duration;
    }

    public List<String> getActors() {
        return actors;
    }

    @Override
    public Image getPreview() {
        try {
            if (previewGenerated()){
                File file = new File(generateNamePreview());
                String localUrl = file.toURI().toURL().toString();
                return new Image(localUrl);
            }else{
                String nameFile = generateNamePreview();

                FfmpegUse ffmpegUse = new FfmpegUse();
                ffmpegUse.thumbnail(getPath(), nameFile, foo);
                synchronized (foo){
                    try{
                        foo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                File outputFile = new File(nameFile);
                String localUrl = outputFile.toURI().toURL().toString();
                return new Image(localUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getType() {
        return "FILM";
    }
}
