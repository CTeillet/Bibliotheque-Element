package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
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

    public Data()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bibliothequeElement/view/listCellItem2.fxml"));
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
        label1.setText(elem.getPath());
        label2.setText(elem.getTitle());
        imageView.setImage(elem.getPreview());
    }

    public GridPane getBox()
    {
        return gridPane;
    }
}
