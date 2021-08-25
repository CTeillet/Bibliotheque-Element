package com.teillet.bibliothequeElement.graphicInterface.epubViewer;

import nl.siegmann.epublib.viewer.ViewerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NewViewerUtil {

    private static final Logger log = LoggerFactory.getLogger(ViewerUtil.class);

    // package
    static JButton createButton(String iconName, String backupLabel) {
        JButton result;
        ImageIcon icon = createImageIcon(iconName);
        if (icon == null) {
            result = new JButton(backupLabel);
        } else {
            result = new JButton(icon);
        }
        return result;
    }


    static ImageIcon createImageIcon(String iconName) {
        ImageIcon result = null;
        String fullIconPath = "/viewer/icons/" + iconName + ".png";
        try {
            Image image = ImageIO.read(Objects.requireNonNull(ViewerUtil.class.getResourceAsStream(fullIconPath)));
            result = new ImageIcon(image);
        } catch(Exception e) {
            log.error("Icon '" + fullIconPath + "' not found");
        }
        return result;
    }
}
