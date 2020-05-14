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
    private int numeroFoto;
    private int enviado;

    public Imagen() {
    }

    public Imagen(int id, int idCertificado, String pathImagen, String tipoImagen,int certificadoId,int numeroFoto, int enviado) {
        this.id = id;
        this.idCertificado = idCertificado;
        this.pathImagen = pathImagen;
        this.tipoImagen = tipoImagen;
        this.certificadoId = certificadoId;
        this.numeroFoto = numeroFoto;
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

    public int getNumeroFoto() {
        return numeroFoto;
    }

    public void setNumeroFoto(int numeroFoto) {
        this.numeroFoto = numeroFoto;
    }
}
