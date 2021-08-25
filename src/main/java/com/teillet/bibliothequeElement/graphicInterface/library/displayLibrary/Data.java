package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.graphicInterface.epubViewer.NewViewer;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;

public class Data {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private ImageView imageView;

    private IElements elem;

    public Data()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bibliothequeElement/view/listCellItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(IElements elem)
    {
        this.elem = elem;
        label1.setText(elem.getTitle());
        label2.setText(elem.getPath());
        imageView.setImage(elem.getPreview());
    }

    public GridPane getBox()
    {
        return gridPane;
    }

    @FXML
    public void onActionPlay(ActionEvent event){
        switch (elem.getType()){
            case Book -> {
                try{
                    InputStream inputStream = new FileInputStream(elem.getPath());
                        NewViewer viewer = new NewViewer(inputStream);
                }catch(Exception ignored){}


            }
            case Film -> {

                // Create the media source.
                File elemFile = new File(elem.getPath());

                Media media;
                try {
                    media = new Media(elemFile.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    break;
                }

                Stage stage = new Stage();
                HBox hbox = new HBox();

                // Create and set the Scene.
                Scene scene = new Scene(hbox, media.getWidth(), media.getHeight());
                stage.setScene(scene);

                // Name and display the Stage.
                stage.setTitle("Hello Media");

                // Create the player and set to play automatically.
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                // Create the view and add it to the Scene.
                MediaView mediaView = new MediaView(mediaPlayer);

                hbox.getChildren().add(mediaView);

                hbox.setPrefWidth(media.getWidth());
                hbox.setPrefHeight(media.getHeight());

                stage.showAndWait();
            }
            case Game -> {

            }
            case Image -> {
                HBox hBox = new HBox();
                Scene scene = new Scene(hBox);

                //Creating an image
                Image image = null;
                try {
                    image = new Image(new FileInputStream(elem.getPath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //Setting the image view
                ImageView imageView = new ImageView(image);

                hBox.getChildren().add(imageView);

                //Setting the preserve ratio of the image view
                imageView.setPreserveRatio(true);

                Stage stage = new Stage();
                stage.setTitle(elem.getTitle());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            }
        }
    }

    @FXML
    public void onActionDelete(ActionEvent event){

    }
}
