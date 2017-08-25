package com.example.andres_dell.uteqdemo.model;

/**
 * Created by Andres on 11/08/2017.
 */

public class Menu {
    private String titulo;
    private String url;
    private String img;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Menu() {

    }

    public Menu(String titulo, String url, String img) {
        this.titulo = titulo;
        this.url = url;
        this.img = img;
    }
}