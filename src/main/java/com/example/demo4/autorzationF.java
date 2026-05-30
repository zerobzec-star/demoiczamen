package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class autorzationF {

    @FXML
    private Button gost;

    @FXML
    private TextField login;

    @FXML
    private TextField parol;

    @FXML
    private Button vhod;
    Servise servise = new Servise();
    DBHandler dbHandler = new DBHandler();
public void gost(ActionEvent actionEvent) throws IOException {
    servise.openForm("1","spisokTovara.fxml");
    gost.getScene().getWindow().hide();
    servise.setUser(null);
}
public void vhof(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
String Login = login.getText();
String Parol = parol.getText();
if (Login.isEmpty()||Parol.isEmpty()){
    servise.openAler("ошибка","заполни все поля",1);
}else {
    dbHandler.DBCoonnection();
    String query = String.format("SELECT p.fio,r.role " +
            "FROM polzovatel p " +
            "INNER JOIN role r ON p.role=r.id " +
            "WHERE login = '%s' AND parol = '%s' ", Login, Parol);
    ResultSet rs = dbHandler.qxecuteQuery(query);
    if (rs.next()){
        String fio=rs.getString("fio");
        String role=rs.getString("role");
        User user = new User(fio,role);
        servise.setUser(user);
        servise.openForm("1","spisokTovara.fxml");
        vhod.getScene().getWindow().hide();
    }else {
        servise.openAler("ошибка","логин пароль невернй",1);
    }
}
}
}
