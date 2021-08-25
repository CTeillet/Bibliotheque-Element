package com.teillet.bibliothequeElement.graphicInterface.epubViewer;

import nl.siegmann.epublib.browsersupport.Navigator;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.viewer.*;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class NewViewer {
    static final Logger log = LoggerFactory.getLogger(Viewer.class);
    private final JFrame mainWindow;
    private JSplitPane mainSplitPane;
    private JSplitPane rightSplitPane;
    private final Navigator navigator = new Navigator();
    //private final BookProcessorPipeline epubCleaner = new BookProcessorPipeline(Collections.emptyList());

    public NewViewer(InputStream bookStream) {
        mainWindow = createMainWindow();
        Book book;
        try {
            book = (new EpubReader()).readEpub(bookStream);
            gotoBook(book);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private JFrame createMainWindow() {
        JFrame result = new JFrame();
        result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        result.setJMenuBar(createMenuBar());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        leftSplitPane.setTopComponent(new TableOfContentsPane(navigator));
        leftSplitPane.setBottomComponent(new GuidePane(navigator));
        leftSplitPane.setOneTouchExpandable(true);
        leftSplitPane.setContinuousLayout(true);
        leftSplitPane.setResizeWeight(0.8);

        rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        rightSplitPane.setOneTouchExpandable(true);
        rightSplitPane.setContinuousLayout(true);
        rightSplitPane.setResizeWeight(1.0);
        ContentPane htmlPane = new ContentPane(navigator);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(htmlPane, BorderLayout.CENTER);
        BrowseBar browseBar = new BrowseBar(navigator, htmlPane);
        contentPanel.add(browseBar, BorderLayout.SOUTH);
        rightSplitPane.setLeftComponent(contentPanel);
        rightSplitPane.setRightComponent(new MetadataPane(navigator));

        mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setLeftComponent(leftSplitPane);
        mainSplitPane.setRightComponent(rightSplitPane);
        mainSplitPane.setOneTouchExpandable(true);
        mainSplitPane.setContinuousLayout(true);
        mainSplitPane.setResizeWeight(0.0);

        mainPanel.add(mainSplitPane, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(1000, 750));
        mainPanel.add(new NavigationBar(navigator), BorderLayout.NORTH);

        result.add(mainPanel);
        result.pack();
        setLayout(NewViewer.Layout.TocContentMeta);
        result.setVisible(true);
        return result;	}


    private void gotoBook(Book book) {
        mainWindow.setTitle(book.getTitle());
        navigator.gotoBook(book, this);
    }

    private static String getText(String text) {
        return text;
    }

    private static JFileChooser createFileChooser(File startDir) {
        if (startDir == null) {
            startDir = new File(System.getProperty("user.home"));
            if (! startDir.exists()) {
                startDir = null;
            }
        }
        JFileChooser fileChooser = new JFileChooser(startDir);
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setFileFilter(new FileNameExtensionFilter("EPub files", "epub"));

        return fileChooser;
    }

    @Nullable
    private File getFile(File previousDir) {
        JFileChooser fileChooser = createFileChooser(previousDir);
        int returnVal = fileChooser.showOpenDialog(mainWindow);
        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile == null) {
            return null;
        }
        if (! selectedFile.isDirectory()) {
            //noinspection UnusedAssignment
            previousDir = selectedFile.getParentFile();
        }
        return selectedFile;
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(getText("File"));
        menuBar.add(fileMenu);
        JMenuItem openFileMenuItem = new JMenuItem(getText("Open"));
        openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK ));
        openFileMenuItem.addActionListener(new ActionListener() {

            private File previousDir;

            public void actionPerformed(ActionEvent e) {
                File selectedFile = getFile(previousDir);
                try {
                   if (selectedFile==null) throw new Exception("Selected File est null");
                    Book book = (new EpubReader()).readEpub(new FileInputStream(selectedFile));
                    gotoBook(book);
                } catch (Exception e1) {
                    log.error(e1.getMessage(), e1);
                }
            }
        });
        fileMenu.add(openFileMenuItem);

        JMenuItem saveFileMenuItem = new JMenuItem(getText("Save as ..."));
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK ));
        saveFileMenuItem.addActionListener(new ActionListener() {

            private File previousDir;

            public void actionPerformed(ActionEvent e) {
                if (navigator.getBook() == null) {
                    return;
                }
                File selectedFile = getFile(previousDir);
                if (selectedFile == null) return;
                try {
                    (new EpubWriter()).write(navigator.getBook(), new FileOutputStream(selectedFile));
                } catch (Exception e1) {
                    log.error(e1.getMessage(), e1);
                }
            }

        });
        fileMenu.add(saveFileMenuItem);

        JMenuItem reloadMenuItem = new JMenuItem(getText("Reload"));
        reloadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        reloadMenuItem.addActionListener(e -> gotoBook(navigator.getBook()));
        fileMenu.add(reloadMenuItem);

        JMenuItem exitMenuItem = new JMenuItem(getText("Exit"));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        JMenu viewMenu = new JMenu(getText("View"));
        menuBar.add(viewMenu);

        JMenuItem viewTocContentMenuItem = new JMenuItem(getText("TOCContent"), NewViewerUtil.createImageIcon("layout-toc-content"));
        viewTocContentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
        viewTocContentMenuItem.addActionListener(e -> setLayout(Layout.TocContent));
        viewMenu.add(viewTocContentMenuItem);

        JMenuItem viewContentMenuItem = new JMenuItem(getText("Content"), NewViewerUtil.createImageIcon("layout-content"));
        viewContentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
        viewContentMenuItem.addActionListener(e -> setLayout(Layout.Content));
        viewMenu.add(viewContentMenuItem);

        JMenuItem viewTocContentMetaMenuItem = new JMenuItem(getText("TocContentMeta"), NewViewerUtil.createImageIcon("layout-toc-content-meta"));
        viewTocContentMetaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
        viewTocContentMetaMenuItem.addActionListener(e -> setLayout(Layout.TocContentMeta));
        viewMenu.add(viewTocContentMetaMenuItem);

        JMenu helpMenu = new JMenu(getText("Help"));
        menuBar.add(helpMenu);
        JMenuItem aboutMenuItem = new JMenuItem(getText("About"));
        aboutMenuItem.addActionListener(e -> new AboutDialog(NewViewer.this.mainWindow));
        helpMenu.add(aboutMenuItem);

        return menuBar;
    }

    private enum Layout {
        TocContentMeta,
        TocContent,
        Content
    }

    private void setLayout(NewViewer.Layout layout) {
        switch (layout) {
            case Content -> {
                mainSplitPane.setDividerLocation(0.0d);
                rightSplitPane.setDividerLocation(1.0d);
            }
            case TocContent -> {
                mainSplitPane.setDividerLocation(0.2d);
                rightSplitPane.setDividerLocation(1.0d);
            }
            case TocContentMeta -> {
                mainSplitPane.setDividerLocation(0.2d);
                rightSplitPane.setDividerLocation(0.6d);
            }
        }
    }

}
