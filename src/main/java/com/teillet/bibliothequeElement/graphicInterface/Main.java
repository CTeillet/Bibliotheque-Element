package com.teillet.bibliothequeElement.graphicInterface;

import com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary.LibraryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL mainFXMLURL = ClassLoader.getSystemResource("bibliothequeElement/view/library.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(mainFXMLURL);
        Parent root = fxmlLoader.load();
        LibraryController lb = fxmlLoader.getController();
        lb.setList();
        lb.populateListView();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
