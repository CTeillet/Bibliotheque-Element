package com.teillet.bibliothequeElement.graphicInterface.test;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.viewer.Viewer;
import org.apache.commons.codec.digest.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.*;


public class EpubReaderTest {
    public static void main(String[] args) throws Exception {
        findCoverEpub();
    }

    static void epubViewer() throws IOException {
        File f = new File("C:\\Users\\teill\\test_element\\Around the World in 28 Languages.epub");
        InputStream inputStream = new FileInputStream(f);
        Viewer viewer = new Viewer(inputStream);
    }

    void generateHash() throws IOException {
        File f = new File("C:\\Users\\teill\\test_element\\Around the World in 28 Languages.epub");
        long t = System.currentTimeMillis();
        System.out.println("MD5 : " + Arrays.toString(new DigestUtils(MD5).digest(f)));
        long t1 = System.currentTimeMillis();
        System.out.println("SHA-1 : " + Arrays.toString(new DigestUtils(SHA_1).digest(f)));
        long t2 = System.currentTimeMillis();
        System.out.println("SHA-2 : " + Arrays.toString(new DigestUtils(SHA_256).digest(f)));
        long t3 = System.currentTimeMillis();

        System.out.println("Premier : " + (t1-t) + " deuxieme : " + (t2-t1) + " dernier : "+ (t3-t2));
    }

    static void findCoverEpub() throws IOException {
        File f = new File("C:\\Users\\teill\\test_element\\Around the World in 28 Languages.epub");
        InputStream inputStream = new FileInputStream(f);
        Book epubBook = new EpubReader().readEpub(inputStream);
        byte[] cover = epubBook.getCoverImage().getData();

        ByteArrayInputStream bis = new ByteArrayInputStream(cover);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("output.jpg") );

    }

    static void displaySystem(){
        System.out.println(System.getProperty("os.name").toLowerCase());
    }
}
