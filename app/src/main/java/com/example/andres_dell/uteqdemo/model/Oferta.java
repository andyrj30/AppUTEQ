package com.example.andres_dell.uteqdemo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andres on 13/08/2017.
 */

public class Oferta {
    private int id;
    private int menu_id;
    private String titulo;
    private String routing;
    private String target;
    private String estado;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Oferta(){

    }

    public Oferta(JSONObject a) throws JSONException {
        id=a.getInt("id");
        menu_id =a.getInt("munu_id");
        titulo=a.getString("titulo");
        routing=a.getString("routing");
        target=a.getString("target");
        estado=a.getString("estado");

    }
    public static ArrayList<Oferta> JsonObjectsBuild(JSONArray datos)
            throws JSONException {

        ArrayList<Oferta> facultades = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            facultades.add(new Oferta(datos.getJSONObject(i)));
        }
        return facultades;

    }
}
