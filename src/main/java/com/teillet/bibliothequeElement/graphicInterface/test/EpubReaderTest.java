package com.teillet.bibliothequeElement.graphicInterface.test;

import nl.siegmann.epublib.viewer.Viewer;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.*;


public class EpubReaderTest {
    public static void main(String[] args) throws IOException {
        epubViewer();
    }

    static void epubViewer() throws IOException {
        URL url = ClassLoader.getSystemResource("bibliothequeElement/element/Around the World in 28 Languages.epub");
        InputStream inputStream = url.openStream();
        Viewer viewer = new Viewer(inputStream);
    }

    void generateHash() throws IOException, URISyntaxException {
        URL url = ClassLoader.getSystemResource("bibliothequeElement/element/Around the World in 28 Languages.epub");
        File f = Paths.get(url.toURI()).toFile();
        long t = System.currentTimeMillis();
        System.out.println("MD5 : " + Arrays.toString(new DigestUtils(MD5).digest(f)));
        long t1 = System.currentTimeMillis();
        System.out.println("SHA-1 : " + Arrays.toString(new DigestUtils(SHA_1).digest(f)));
        long t2 = System.currentTimeMillis();
        System.out.println("SHA-2 : " + Arrays.toString(new DigestUtils(SHA_256).digest(f)));
        long t3 = System.currentTimeMillis();

        System.out.println("Premier : " + (t1-t) + " deuxieme : " + (t2-t1) + " dernier : "+ (t3-t2));

    }
}
