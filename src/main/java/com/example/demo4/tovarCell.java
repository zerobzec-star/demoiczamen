package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class tovarCell extends ListCell<Tovar>
{
    private Node root;
    private tovarCard conntroller;
    public tovarCell(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tovarCard.fxml"));
            root = loader.load();
             conntroller= loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void updateItem(Tovar item,boolean empety){

            super.updateItem(item,empety);
            if (empety||item==null){
                setGraphic(null);
                setText(null);
                setStyle("");
                return;
            }
            setStyle(item.getKolvo_na_sklade()==0 ? "-fx-background-color:BLUE;" : item.getDeistvuishaz_skidka()>15 ? "-fx-background-color:GREEN;" :"");
conntroller.setTovarData(item);
            setGraphic(root);
    }
}
