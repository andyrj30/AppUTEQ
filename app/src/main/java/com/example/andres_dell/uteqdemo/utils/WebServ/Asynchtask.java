package com.example.andres_dell.uteqdemo.utils.WebServ;

import org.json.JSONException;

import java.text.ParseException;

public interface Asynchtask {
    /**
     * ESta funcion retorna los datos devueltos por el ws
     * @param result
     */
    void processFinish(String result) throws JSONException, ParseException;

}
