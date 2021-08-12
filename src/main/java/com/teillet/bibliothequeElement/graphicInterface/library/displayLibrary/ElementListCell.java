package com.teillet.bibliothequeElement.graphicInterface.library.displayLibrary;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import javafx.scene.control.ListCell;

public class ElementListCell extends ListCell<IElements> {

    @Override
    public void updateItem(IElements elem, boolean empty) {
        super.updateItem(elem, empty);
        if (elem != null) {
            Data data = new Data();
            data.setInfo(elem);
            setGraphic(data.getBox());
        }
    }
}
