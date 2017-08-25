package com.example.andres_dell.uteqdemo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andres on 10/08/2017.
 */

public class Slider {

    private int id;
    private int unidad_id;
    private int user_id;
    private String nombre;
    private String titulo;
    private String detalle;
    private String img;
    private String estado;
    private String prioridad;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(int unidad_id) {
        this.unidad_id = unidad_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Slider() {

    }

    public Slider(JSONObject a) throws JSONException {
        id = a.getInt("id");
        unidad_id = a.getInt("unidad_id");
        user_id = a.getInt("user_id");
        nombre = a.getString("nombre");
        titulo = a.getString("titulo");
        detalle = a.getString("detalle");
        img = a.getString("img");
        estado = a.getString("estado");
        prioridad = a.getString("prioridad");

    }

    public static ArrayList<Slider> JsonObjectsBuild(JSONArray datos)
            throws JSONException {

        ArrayList<Slider> slider = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            slider.add(new Slider(datos.getJSONObject(i)));
        }
        return slider;
    }
}
