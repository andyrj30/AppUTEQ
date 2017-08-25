package com.example.andres_dell.uteqdemo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andres on 16/08/2017.
 */
public class Carrera {
    private int id;
    private int unidad_id;
    private String titulo;
    private String nombre;
    private String mision;
    private String vision;
    private String objetivos;
    private String jornada;
    private String modalidad;
    private int semestres;
    private String tiempo;
    private String descripcion;
    private String perfilprofesional;
    private String malla;
    private String email;
    private String telefono;
    private String direccion;
    private String estado;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getSemestres() {
        return semestres;
    }

    public void setSemestres(int semestres) {
        this.semestres = semestres;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPerfilprofesional() {
        return perfilprofesional;
    }

    public void setPerfilprofesional(String perfilprofesional) {
        this.perfilprofesional = perfilprofesional;
    }

    public String getMalla() {
        return malla;
    }

    public void setMalla(String malla) {
        this.malla = malla;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Carrera() {

    }

    public Carrera(int id, String Titulo) {
        setId(id);
        setTitulo(Titulo);
    }

    public Carrera(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        nombre = jsonObject.getString("nombre");
    }

    public static ArrayList<Carrera> JsonObjectsBuild(JSONArray datos)
            throws JSONException {

        ArrayList<Carrera> carreras = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            carreras.add(new Carrera(datos.getJSONObject(i)));
        }
        return carreras;

    }
}
