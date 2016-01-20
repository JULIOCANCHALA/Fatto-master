package com.canchala.julio.practica7;

/**
 * Created by julio on 20/01/2016.
 */
public class alarm {
    private String actividad;
    private int hora;
    private int min;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;


    public alarm(String actividad, int hora, int min, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo) {
        this.actividad = actividad;
        this.hora = hora;
        this.min = min;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }


    public String getActividad() {
        return actividad;
    }

    public int getHora() {
        return hora;
    }

    public int getMin() {
        return min;
    }

    public String getLunes() {
        return lunes;
    }

    public String getMartes() {
        return martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public String getSabado() {
        return sabado;
    }

    public String getDomingo() {
        return domingo;
    }
}
