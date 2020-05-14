package com.example.certificadosdefuncion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bitacora implements Serializable {

    @SerializedName("id_padron")
    public int id_padron;
    @SerializedName("id_usuario")
    public int id_usuario;
    @SerializedName("fecha_asignado")
    public String fecha;
    @SerializedName("id_status")
    public int id_status;
    @SerializedName("observaciones")
    public String observaciones;
    @SerializedName("tipo_registro")
    public String tipo_registro;
    @SerializedName("status")
    public String status;
    @SerializedName("telefono")
    public String telefono;

    public Bitacora() {
    }

    public Bitacora(int id_padron, int id_usuario, String fecha, int id_status, String observaciones, String tipo_registro, String status, String telefono) {
        this.id_padron = id_padron;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.id_status = id_status;
        this.observaciones = observaciones;
        this.tipo_registro = tipo_registro;
        this.status = status;
        this.telefono = telefono;
    }

    public int getId_padron() {
        return id_padron;
    }

    public void setId_padron(int id_padron) {
        this.id_padron = id_padron;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
