package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

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

    protected String generateNamePreview(String extension){
        //String res = new String (new DigestUtils(MD5).digest(path), StandardCharsets.UTF_8);
        int test = path.hashCode();
        return "preview/" + test + "." + extension;
    }

    protected boolean previewGenerated(String extension){
        String res = generateNamePreview(extension);
        File f = new File(res);
        return f.exists();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elements elements = (Elements) o;
        return title.equals(elements.title) && path.equals(elements.path) && getType().equals(elements.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, path);
    }
}
