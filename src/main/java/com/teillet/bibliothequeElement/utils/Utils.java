package com.teillet.bibliothequeElement.utils;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class Utils {
    public static IElements Object2Elements(String type, String path, String title) {
        try {
            String t = StringUtils.capitalize(type.toLowerCase());
            String className = "com.teillet.bibliothequeElement.library." + t;
            @SuppressWarnings("unchecked") Class<IElements> cls = (Class<IElements>) Class.forName(className);
            @SuppressWarnings("rawtypes") Class[] partypes = new Class[2];
            partypes[0] = String.class;
            partypes[1] = String.class;
            Constructor<IElements> ct
                    = cls.getConstructor(partypes);
            Object[] arglist = new Object[2];
            arglist[0] = title;
            arglist[1] = path;
            return ct.newInstance(arglist);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean writeImage(BufferedImage bufferedImage, String filename){
        try {
            switch (bufferedImage.getType()) {
                case BufferedImage.TYPE_INT_ARGB:
                    BufferedImage newBufferedImage = new BufferedImage(
                            bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage
                            .createGraphics()
                            .drawImage(bufferedImage, 0, 0, Color.WHITE, null);
                    return ImageIO.write(newBufferedImage, "jpg", new File(filename));
                case BufferedImage.TYPE_INT_RGB:
                    return ImageIO.write(bufferedImage, "BMP", new File(filename));
                default:
                    return false;
            }
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }
}
