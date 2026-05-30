package com.example.demo4;

public class Zakaz {
    private String nomer_zakaza;
    private String data_zakaza;
    private String data_dostavki;
    private String adresPunktaVidachi;
    private String statusZakaza;
    private String articulTovara;

    public Zakaz(String nomer_zakaza, String data_zakaza, String data_dostavki,
                 String adresPunktaVidachi, String statusZakaza, String articulTovara) {
        this.nomer_zakaza = nomer_zakaza;
        this.data_zakaza = data_zakaza;
        this.data_dostavki = data_dostavki;
        this.adresPunktaVidachi = adresPunktaVidachi;
        this.statusZakaza = statusZakaza;
        this.articulTovara = articulTovara;
    }

    public String getNomer_zakaza() { return nomer_zakaza; }
    public String getData_zakaza() { return data_zakaza; }
    public String getData_dostavki() { return data_dostavki; }
    public String getAdresPunktaVidachi() { return adresPunktaVidachi; }
    public String getStatusZakaza() { return statusZakaza; }
    public String getArticulTovara() { return articulTovara; }
}