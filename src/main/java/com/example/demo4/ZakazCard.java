package com.example.demo4;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ZakazCard {

    @FXML
    private Text adresPunkta;

    @FXML
    private Text articule;

    @FXML
    private Text status;

    @FXML
    private Text dataZakaza;

    @FXML
    private Text datadostavki;

    public void setZakazData(Zakaz z) {
        articule.setText("Артикул: " + z.getArticulTovara());
        status.setText("Статус: " + z.getStatusZakaza());
        adresPunkta.setText("Пункт выдачи: " + z.getAdresPunktaVidachi());
        dataZakaza.setText("Дата заказа: " + z.getData_zakaza());
        datadostavki.setText("Дата доставки: " + z.getData_dostavki());
    }
}