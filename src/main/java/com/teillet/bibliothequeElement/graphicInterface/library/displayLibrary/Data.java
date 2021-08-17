package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.siegmann.epublib.viewer.Viewer;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

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
//                Interface nul, à refaire
//                try{InputStream inputStream = new FileInputStream(elem.getPath());
//                        Viewer viewer = new Viewer(inputStream);
//                }catch(Exception e){}
            }
            case Film -> {
                MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
                EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();


                BorderPane pane = new BorderPane();
                pane.setStyle("-fx-background-color: black;");

                ImageView videoImageView = new ImageView();
                videoImageView.setPreserveRatio(true);

                videoImageView.fitWidthProperty().bind(pane.widthProperty());
                videoImageView.fitHeightProperty().bind(pane.heightProperty());

                embeddedMediaPlayer.videoSurface().set(videoSurfaceForImageView(videoImageView));

                pane.setCenter(videoImageView);

                Scene scene = new Scene(pane, 1200, 675, Color.BLACK);

                Stage stage = new Stage();
                stage.setTitle("vlcj JavaFX");
                stage.setScene(scene);
                stage.show();

                //System.out.println("Debut video");
                embeddedMediaPlayer.media().play(elem.getPath());



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
