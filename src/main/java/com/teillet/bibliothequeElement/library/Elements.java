package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.IElements;

import java.io.File;
import java.util.Objects;

public abstract class Elements implements IElements {
    private final String title;
    private final String path;

    public Elements(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    protected String generateNamePreview(){
        int test = path.hashCode();
        return "preview/" + test + ".jpeg";
    }

    protected boolean previewGenerated(){
        String res = generateNamePreview();
        File f = new File(res);
        return f.exists();
    }

    public abstract void play();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elements elements = (Elements) o;
        return title.equals(elements.title) && path.equals(elements.path) && getType().equals(elements.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, path, getType());
    }
}
