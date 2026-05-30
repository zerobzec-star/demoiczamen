package com.example.demo4;

public class Tovar {
    public Tovar(String articule, String naimenovanie_tovara_id, String edenica_izmerenia, String postavshic_id, String proizvoditel_id, String kategoria_tovara_id, String opicanie_tovara, String foto, int cena, int deistvuishaz_skidka, int kolvo_na_sklade) {
        this.articule = articule;
        this.naimenovanie_tovara_id = naimenovanie_tovara_id;
        this.edenica_izmerenia = edenica_izmerenia;
        this.postavshic_id = postavshic_id;
        this.proizvoditel_id = proizvoditel_id;
        this.kategoria_tovara_id = kategoria_tovara_id;
        this.opicanie_tovara = opicanie_tovara;
        this.foto = foto;
        this.cena = cena;
        this.deistvuishaz_skidka = deistvuishaz_skidka;
        this.kolvo_na_sklade = kolvo_na_sklade;
    }

    private  String articule;
    private String naimenovanie_tovara_id;
    private String edenica_izmerenia;
    private String postavshic_id;
    private String proizvoditel_id;
    private String kategoria_tovara_id;

    public String getOpicanie_tovara() {
        return opicanie_tovara;
    }

    public String getArticule() {
        return articule;
    }

    public String getNaimenovanie_tovara_id() {
        return naimenovanie_tovara_id;
    }

    public String getEdenica_izmerenia() {
        return edenica_izmerenia;
    }

    public String getPostavshic_id() {
        return postavshic_id;
    }

    public String getProizvoditel_id() {
        return proizvoditel_id;
    }

    public String getKategoria_tovara_id() {
        return kategoria_tovara_id;
    }

    public String getFoto() {
        return foto;
    }

    public int getCena() {
        return cena;
    }

    public int getDeistvuishaz_skidka() {
        return deistvuishaz_skidka;
    }

    public int getKolvo_na_sklade() {
        return kolvo_na_sklade;
    }

    private String opicanie_tovara;
    private String foto;
    private int cena,deistvuishaz_skidka,kolvo_na_sklade;
}
