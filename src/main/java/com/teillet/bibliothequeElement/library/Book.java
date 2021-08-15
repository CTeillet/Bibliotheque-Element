package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.utils.Utils;
import javafx.scene.image.Image;
import net.coobird.thumbnailator.Thumbnailator;
import nl.siegmann.epublib.epub.EpubReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.embed.swing.SwingFXUtils;


public class Book extends Elements{
    public Book(String title, String path) {
        super(title, path);
    }

    @Override
    public void play() {

    }

    @Override
    public Image getPreview() {
        try {
            if (previewGenerated()){
                File file = new File(generateNamePreview());
                String localUrl = file.toURI().toURL().toString();
                return new javafx.scene.image.Image(localUrl);
            }else{
                String nameFile = generateNamePreview();
                File inputFile = new File(getPath());
                InputStream inputStream = new FileInputStream(inputFile);

                nl.siegmann.epublib.domain.Book epubBook = new EpubReader().readEpub(inputStream);
                byte[] cover = epubBook.getCoverImage().getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(cover);
                BufferedImage bImage2 = ImageIO.read(bis);

                BufferedImage res = Thumbnailator.createThumbnail(bImage2, 100, 100);
                boolean resWrite = Utils.writeImage(res, nameFile);
                if(!resWrite) throw new Exception("Probleme lors de l'enregistrmement de l'image");
                return SwingFXUtils.toFXImage(res, null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getType() {
        return "Book";
    }
}
