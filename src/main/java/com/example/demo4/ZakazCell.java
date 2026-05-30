package com.example.demo4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import java.io.IOException;

public class ZakazCell extends ListCell<Zakaz> {
    private Node root;
    private ZakazCard controller;

    public ZakazCell() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("zakazForm.fxml"));
            root = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Zakaz item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
            return;
        }
        controller.setZakazData(item);
        setGraphic(root);
    }
}