package com.teillet.bibliothequeElement.graphicInterface.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class MultiSelectionComboDemo extends Application {
    final ListView<String> selectedItems = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        final String sMenuTextStart = "Fruit : ";
        final String sMenuTextEmpty = "[empty]";
        final MenuButton choices = new MenuButton(sMenuTextStart+sMenuTextEmpty);
        final List<CheckMenuItem> items   = Arrays.asList(new CheckMenuItem("Apple"), new CheckMenuItem("Banana"), new CheckMenuItem("Pear"), new CheckMenuItem("Kiwi"));
        choices.getItems().addAll(items);

        for (final CheckMenuItem item : items) {
            item.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue) {
                    selectedItems.getItems().add(item.getText());
                } else {
                    selectedItems.getItems().remove(item.getText());
                }
                String sMenuText = sMenuTextStart + (selectedItems.getItems().size()>0?"":sMenuTextEmpty);
                choices.setText(sMenuText+String.join(", ", selectedItems.getItems()));
            });
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(choices);
        borderPane.setCenter(selectedItems);
        primaryStage.setScene(new Scene(borderPane, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
