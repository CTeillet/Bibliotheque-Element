package com.teillet.bibliothequeElement.graphicInterface.library.addElement;

import com.teillet.bibliothequeElement.database.BDElements;
import com.teillet.bibliothequeElement.database.BDTypes;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import com.teillet.bibliothequeElement.utils.Factory;
import com.teillet.bibliothequeElement.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class AddElementController {
    File choosenFile;
    @FXML
    ChoiceBox<String> typesChoiceBox;
    @FXML
    Button butChoiceFile;
    @FXML
    TextField textField;
    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        choosenFile = fc.showOpenDialog(((Node)actionEvent.getSource()).getScene().getWindow());
        if (choosenFile!=null){
            butChoiceFile.setText(choosenFile.getName());
        }
    }

    public void updateTypes(){
        List<String> types = BDTypes.getTypes();
        System.out.println( "Nb element " + types.size());
        typesChoiceBox.getItems().addAll(types);
    }

    public void addElement(ActionEvent actionEvent) {
        String type = typesChoiceBox.getSelectionModel().getSelectedItem();
        String title = textField.getText();
        String path = choosenFile.getAbsolutePath();
        IElements elem = Utils.Object2Elements(type, path, title);
        assert elem != null;
        boolean res = BDElements.addElements(elem);
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }
}
