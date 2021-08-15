package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

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
        elem.play();
    }
}
