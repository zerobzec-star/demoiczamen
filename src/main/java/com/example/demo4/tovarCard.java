package com.example.demo4;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class tovarCard {

    @FXML
    private Text cena;

    @FXML
    private Text edizmerenia;

    @FXML
    private Text itogoaicena;

    @FXML
    private Text kategoria;

    @FXML
    private Text kolichestov;
    @FXML
    private ImageView fotoo;
    @FXML
    private Text nametovara;

    @FXML
    private Text opicanie;

    @FXML
    private Text postashic;

    @FXML
    private Text proizvoditel;

    @FXML
    private Text skidka;

    public void setTovarData(Tovar t){
        nametovara.setText("наименование товара"+t.getNaimenovanie_tovara_id());
        kategoria.setText("категория товара"+t.getKategoria_tovara_id() +"   |");
        opicanie.setText("описание"+t.getOpicanie_tovara());
        postashic.setText("поставщик"+t.getPostavshic_id());
        proizvoditel.setText("производитель"+t.getProizvoditel_id());
        edizmerenia.setText("еденица изменрения"+t.getEdenica_izmerenia());
        kolichestov.setText("количество на складе"+t.getKolvo_na_sklade()+"шт");
        int price = t.getCena();
        int skidkaa = t.getDeistvuishaz_skidka();
        int finalCena = price-price *skidkaa / 100;
        skidka.setText( skidkaa+"%");
        cena.setText("цена"+price);
        cena.setFill(Color.RED);
        cena.setStyle("-fx-strikethrough:true");
        itogoaicena.setText("итоговая цена"+finalCena);
loadImages(t.getFoto());

    }
    private void loadImages(String photo){
        try {
            String path = (photo != null || !photo.isEmpty()
            ? "/Images/"+ photo : "/Images/.picture.png");
            var url =getClass().getResource(path);
            if (url==null)
                url = getClass().getResource("/Images/.picture.png");
            fotoo.setImage(new Image(url.toString()));
        } catch (Exception e) {
            fotoo.setImage(new Image(getClass().getResource("/Images/.picture.png").toString()));
        }
    }
}