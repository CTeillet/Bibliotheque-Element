package com.teillet.bibliothequeElement.graphicInterface.connection;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Connection {

    public void noAccount() throws IOException {

        URL registerFXMLURL = ClassLoader.getSystemResource("bibliothequeElement/view/register.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(registerFXMLURL);
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
