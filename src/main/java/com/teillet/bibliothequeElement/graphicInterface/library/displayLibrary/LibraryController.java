package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.database.BDConnect;
import com.teillet.bibliothequeElement.graphicInterface.library.addElement.AddElementController;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import com.teillet.bibliothequeElement.utils.Factory;
import com.teillet.bibliothequeElement.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibraryController {
    @FXML
    ListView<IElements> listView;

    public void addPopUp(ActionEvent actionEvent) throws IOException {
        //Chargement du fxml
        URL addElementFXMLURL = ClassLoader.getSystemResource("bibliothequeElement/view/addElement.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(addElementFXMLURL);
        Parent parent = fxmlLoader.load();

        //Recuperation du controller
        AddElementController aec = fxmlLoader.getController();
        aec.updateTypes();

        //Preparation de la scene de la fenetre
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.setTitle("Test");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        //Uodate the listView
        populateListView();
    }

    public void populateListView() {
        try {
            String query = "select * from elements";
            PreparedStatement prestate = BDConnect.connect().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String type = result.getString("type");

                IElements elem = Utils.Object2Elements(type,result.getString("path"),result.getString("title"));
                ObservableList<IElements> list = FXCollections.observableArrayList(elem);
                listView.getItems().addAll(list);
            }
            listView.setCellFactory(lv -> new ElementListCell());

            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
