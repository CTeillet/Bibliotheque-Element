package com.teillet.bibliothequeElement.graphicInterface.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageViewer extends Application {
    @Override
    public void start(Stage stage) {
        //Button to display pop-up displaying image
        Button but= new Button("Test");

        but.setOnAction(event -> {
            HBox hBox = new HBox();
            Scene scene = new Scene(hBox);

            //Creating an image
            Image image = null;
            try {
                image = new Image(new FileInputStream("C:\\Users\\teill\\test_element\\i3wPjZ5Th-I.jpg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //Setting the image view
            ImageView imageView = new ImageView(image);

            hBox.getChildren().add(imageView);

            //Setting the position of the image
            imageView.setX(50);
            imageView.setY(25);

            //setting the fit height and width of the image view
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);

            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);

            Stage stage2 = new Stage();
            stage2.setTitle("Test");
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setScene(scene);
            stage2.showAndWait();
        });

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(root);

        //Add button to scene
        root.getChildren().add(but);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
