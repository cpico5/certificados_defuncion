package com.example.certificadosdefuncion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Imagen implements Serializable {

    private int id;
    @SerializedName("idCertificado")
    private int idCertificado;
    @SerializedName("path_imagen")
    private String pathImagen;
    @SerializedName("type")
    private String tipoImagen;
    @SerializedName("certificado_id")
    private int certificadoId;
    @SerializedName("numero_foto")
    private int numero_foto;
    private int enviado;

    public Imagen() {
    }

    public Imagen(int id, int idCertificado, String pathImagen, String tipoImagen,int certificadoId, int numero_foto, int enviado) {
        this.id = id;
        this.idCertificado = idCertificado;
        this.pathImagen = pathImagen;
        this.tipoImagen = tipoImagen;
        this.certificadoId = certificadoId;
        this.numero_foto = numero_foto;
        this.enviado = enviado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }

    public int getCertificadoId() {
        return certificadoId;
    }

    public void setCertificadoId(int certificadoId) {
        this.certificadoId = certificadoId;
    }

    public int getNumero_foto() {
        return numero_foto;
    }

    public void setNumero_foto(int numero_foto) {
        this.numero_foto = numero_foto;
    }
}
