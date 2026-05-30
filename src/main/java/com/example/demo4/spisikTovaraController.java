package com.example.demo4;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class spisikTovaraController {

    @FXML
    private Tab zakaz;
    @FXML
    private ComboBox<String> filtraciaCB;

    @FXML
    private Button delet;
    @FXML
    private ListView<Zakaz> listViewZakaz;
    @FXML
    private Label fio;

    @FXML
    private ListView<Tovar> listWievT;

    @FXML
    private Button nazad;

    @FXML
    private TextField poiskTF;


    @FXML
    private Label role;

    @FXML
    private ComboBox<String> sortirovkaCB;
    Servise servise = new Servise();
    DBHandler dbHandler = new DBHandler();
    private SortedList<Tovar> sortedData;
    public FilteredList<Tovar> filteredData;

private boolean isGuest;

    public void  initialize(){
        var user = servise.getUser();
        isGuest = user== null || "гссть".equalsIgnoreCase(user.getRole());
        boolean isClient = user == null || "Авторизированный клиент".equalsIgnoreCase(user.getFio());

        fio.setText(user==null ? "гссть": user.getFio());
        role.setText(user==null ? "гссть": user.getRole());

        loadTovar();
        loadZakaz();
        delet.setOnAction(e -> deleteTovar());
        if (isGuest||isClient){
            delet.setVisible(false);
            zakaz.setDisable(true);
            poiskTF.setVisible(false);
            sortirovkaCB.setVisible(false);
            filtraciaCB.setVisible(false);

        }else {
            sortirovkaCB.setItems(FXCollections.observableArrayList("все","возр","убыв"));
sortirovkaCB.setValue("все");
sortirovkaCB.valueProperty().addListener((obs,o,n)->sortData());
poiskTF.textProperty().addListener((obs,o,n)-> applyData());
filtraciaCB.valueProperty().addListener((obs,o,n)-> applyData());
        }

    }
    private void loadTovar(){
        try {
            dbHandler.DBCoonnection();
            ResultSet rs = dbHandler.qxecuteQuery("SELECT t.articule, nt.nazvanie naimenovanie_tovara, t.edenica_izmerenia, t.cena, " +
                    "pt.nazvanie postavshic, p.nazvanie proizvoditel,k.nazvanie kategoria_tovara, " +
                    "t.deistvuishaz_skidka, t.kolvo_na_sklade, t.opicanie_tovara, t.foto " +
                    "FROM tovar t " +
                    "LEFT JOIN naimenovanie_tovarov nt ON t.naimenovanie_tovara_id = nt.id " +
                    "LEFT JOIN postavshik_tovara pt ON t.postavshic_id = pt.id " +
                    "LEFT JOIN proizviditel p ON t.proizvoditel_id = p.id " +
                    "LEFT JOIN kategoria k ON t.kategoria_tovara_id = k.id ");
            ObservableList<Tovar> masterData = FXCollections.observableArrayList();
            while (rs.next()){
                masterData.addAll(new Tovar(
                        rs.getString("articule"),
                        rs.getString("naimenovanie_tovara"),
                        rs.getString("edenica_izmerenia"),
                        rs.getString("postavshic"),
                        rs.getString("proizvoditel"),
                        rs.getString("kategoria_tovara"),
                        rs.getString("opicanie_tovara"),
                        rs.getString("foto"),
                        rs.getInt("cena"),
                        rs.getInt("deistvuishaz_skidka"),
                        rs.getInt("kolvo_na_sklade")));

                filteredData = new FilteredList<>(masterData, p -> true);
                sortedData = new SortedList<>(filteredData);
listWievT.setItems(sortedData);
listWievT.setCellFactory(p->new tovarCell());
            if (!isGuest){
ObservableList<String> suplier =FXCollections.observableArrayList("все поставщики");
suplier.addAll(masterData.stream().map(Tovar::getPostavshic_id).filter(s->s != null && !s.isEmpty()).distinct().collect(Collectors.toList()));
filtraciaCB.setItems(suplier);
filtraciaCB.setValue("все поставщики");
            }
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private void applyData(){
        if (isGuest)return;
        String searhText = poiskTF.getText();

        String suplier = filtraciaCB.getValue();
        filteredData.setPredicate(Tovar ->{
if (suplier != null && !suplier.equals("все поставщики" )&& !suplier.equalsIgnoreCase(Tovar.getPostavshic_id()))return false;
            if (searhText == null|| searhText.trim().isEmpty()) return true;
String serch = searhText.trim().toLowerCase();
return containsIgnoreNull(Tovar.getNaimenovanie_tovara_id(),serch)||
        containsIgnoreNull(Tovar.getOpicanie_tovara(),serch)||
        containsIgnoreNull(Tovar.getKategoria_tovara_id(),serch)||
        containsIgnoreNull(Tovar.getPostavshic_id(),serch)||
        containsIgnoreNull(Tovar.getProizvoditel_id(),serch);
                });
    }
    private boolean containsIgnoreNull(String value,String searh){
        return value != null&& value.toLowerCase().contains(searh);
    }
    private void sortData(){
        if (isGuest)return;
        sortedData.setComparator(switch (sortirovkaCB.getValue()) {
            case "возр" -> Comparator.comparingInt(Tovar::getKolvo_na_sklade);
            case "убыв" -> Comparator.comparingInt(Tovar::getKolvo_na_sklade).reversed();
            default -> null;
        });
        }
    private void loadZakaz() {
        try {
            dbHandler.DBCoonnection();

            String query = "SELECT z.nomer_zakaza, z.data_zakaza, z.data_dostavki, " +
                    "p.punkt_vidachi, z.status_zakaza, zt.articule_zakaza " +
                    "FROM zakaz z " +
                    "LEFT JOIN punkt p ON z.adres_punkta_vidachi_id = p.id " +
                    "LEFT JOIN zakaz_tovar zt ON z.nomer_zakaza = zt.nomer_zakaza";

            ResultSet rs = dbHandler.qxecuteQuery(query);
            ObservableList<Zakaz> zakazList = FXCollections.observableArrayList();

            while (rs.next()) {
                zakazList.add(new Zakaz(
                        rs.getString("nomer_zakaza"),
                        rs.getString("data_zakaza"),
                        rs.getString("data_dostavki"),
                        rs.getString("punkt_vidachi"),
                        rs.getString("status_zakaza"),
                        rs.getString("articule_zakaza") != null ? rs.getString("articule_zakaza") : "Нет артикула"
                ));
            }

            listViewZakaz.setItems(zakazList);
            listViewZakaz.setCellFactory(lv -> new ZakazCell());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            servise.openAler("Ошибка", "Не удалось загрузить заказы", 2);
        }
    }
    private void deleteTovar() {
        Tovar selected = listWievT.getSelectionModel().getSelectedItem();
        if (selected == null) {
            servise.openAler("Ошибка", "Выберите товар", 1);
            return;
        }

        try {
            dbHandler.DBCoonnection();

            // Проверяем, есть ли товар в заказах
            String checkQuery = "SELECT COUNT(*) as count FROM zakaz_tovar WHERE articule_zakaza = '" + selected.getArticule() + "'";
            ResultSet rs = dbHandler.qxecuteQuery(checkQuery);
            rs.next();
            int count = rs.getInt("count");

            if (count > 0) {
                servise.openAler("Ошибка", "Товар присутствует в заказах, удаление невозможно", 2);
                return;
            }

            // Если товара нет в заказах - удаляем
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Удалить " + selected.getNaimenovanie_tovara_id() + "?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                dbHandler.qxecuteUpdate("DELETE FROM tovar WHERE articule = '" + selected.getArticule() + "'");
                loadTovar();
                servise.openAler("Успех", "Товар удален", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            servise.openAler("Ошибка", "Не удалось удалить товар", 2);
        }

    }
    }

