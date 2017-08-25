package com.example.andres_dell.uteqdemo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ANDRES-DELL on 12/06/2017.
 */
public class ArchivoTransparencia {

    private String tituloArchivo;
    private String fechaArchivo;
    private String URLArchivo;
    private String Ano;
    private String Mes;

    public String getFechaArchivo() {
        return fechaArchivo;
    }

    public String getTituloArchivo() {
        return tituloArchivo;
    }

    public String getURLArchivo() {
        return URLArchivo;
    }

    public String getAno() {return Ano;}

    public String getMes() {return Mes;}

    public ArchivoTransparencia(JSONObject a, String tipoFragment) throws JSONException{
        if(!tipoFragment.equals("ar")){
            tituloArchivo=a.getString("detalle");
            Ano=a.getString("ano");
        }
        else
            tituloArchivo=a.getString("descripcion");

        fechaArchivo=a.getString("creacion");
        URLArchivo=a.getString("path");

        if(tipoFragment.equals("tr"))
            Mes=a.getString("mes");
    }

    public static ArrayList<ArchivoTransparencia> JsonObjectsBuild(JSONArray datos,String tipoFragment) throws JSONException {

        ArrayList<ArchivoTransparencia> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new ArchivoTransparencia(datos.getJSONObject(i),tipoFragment));
        }

        return noticias;

    }
}
