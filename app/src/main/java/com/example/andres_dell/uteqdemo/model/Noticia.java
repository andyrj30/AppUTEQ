package com.example.andres_dell.uteqdemo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Andres on 10/08/2017.
 */
public class Noticia {

    private String unidad;
    private String idNoticia;
    private String titulo;
    private String subtitulo;
    private String fecha;
    private String categoria;
    private String path;
    private String idCategoria;

    public Noticia(JSONObject a) throws JSONException {
        setIdNoticia(a.getString("id"));
        setTitulo(a.getString("titulo"));
        setUnidad(a.getString("unidad"));
        setSubtitulo(a.getString("intro"));
        setFecha(a.getString("publicacion"));
        setCategoria(a.getString("categoria"));
        setPath(a.getString("url"));
        setIdCategoria(a.getString("categoria_id"));
    }

    public static ArrayList<Noticia> JsonObjectsBuild(JSONArray datos)
            throws JSONException {
        ArrayList<Noticia> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new Noticia(datos.getJSONObject(i)));
        }
        return noticias;

    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}