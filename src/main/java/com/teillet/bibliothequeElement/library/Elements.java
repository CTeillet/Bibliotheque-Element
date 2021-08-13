package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.util.Arrays;

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

    protected String generateNamePreview(){
        String res = Arrays.toString(new DigestUtils(MD5).digest(path));
        return "preview/" + res;
    }

    protected boolean previewGenerated(){
        String res = "preview/" + Arrays.toString(new DigestUtils(MD5).digest(path));
        File f = new File(res);
        return f.exists();
    }
}
