package com.example.demo4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Servise {
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Servise.user = user;
    }

    public static User user;
    public void openForm(String title,String openForm) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(openForm));
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void openAler(String title,String text,int type) {
        Alert alert;
    if (type==1){
        alert= new Alert(Alert.AlertType.INFORMATION);
    }else if(type==0){
        alert= new Alert(Alert.AlertType.CONFIRMATION);
    }else {
        alert= new Alert(Alert.AlertType.WARNING);
    }

    alert.setTitle(title);
    alert.setContentText(text);
    alert.show();
    }
}
