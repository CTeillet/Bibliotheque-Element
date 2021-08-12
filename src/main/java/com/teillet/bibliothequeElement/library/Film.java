package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.IFilm;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Film extends Elements implements IFilm {
    private final String director;
    private final String duration;
    private final List<String> actors;


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
        return null;
    }

    @Override
    public String getType() {
        return "FILM";
    }
}
