package com.example.certificadosdefuncion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import com.example.certificadosdefuncion.Anotaciones.PrimaryKey;

public class PadronSms implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("paterno")
    public String paterno;
    @SerializedName("materno")
    public String materno;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("nombre_completo")
    public String nombre_completo;
    @SerializedName("telefono")
    public String telefono;
    @SerializedName("folio")
    public String folio;
    @SerializedName("fecha_inicio")
    public String fecha_inicio;
    @SerializedName("edad")
    public String edad;
    @SerializedName("edad_cod")
    public String edad_cod;
    @SerializedName("sexo")
    public String sexo;

    @SerializedName("direccion")
    public String direccion;

    @SerializedName("calle")
    public String calle;
    @SerializedName("numero_exterior")
    public String numero_exterior;
    @SerializedName("numero_interior")
    public String numero_interior;
    @SerializedName("alcaldia")
    public String alcaldia;
    @SerializedName("colonia")
    public String colonia;
    @SerializedName("codigo_postal")
    public String codigo_postal;

    @SerializedName("creacion_registro")
    public String creacion_registro;
    @SerializedName("dia")
    public String dia;

    //datos de su salud
    @SerializedName("fiebre")
    public int fiebre;
    @SerializedName("dolor_cabeza")
    public int dolor_cabeza;
    @SerializedName("tos")
    public int tos;
    @SerializedName("dolor_garganta")
    public int dolor_garganta;
    @SerializedName("dificultad_respirar")
    public int dificultad_respirar;
    @SerializedName("dolor_cuerpo")
    public int dolor_cuerpo;
    @SerializedName("morbilidad")
    public int morbilidad;
    @SerializedName("fase")
    public int fase;
    @SerializedName("fuente")
    public String fuente;
    @SerializedName("resultado")
    public String resultado;
    @SerializedName("color_azul")
    public String color_azul;
    @SerializedName("escurrimiento")
    public String escurrimiento;
    @SerializedName("dolor_respirar")
    public String dolor_respirar;
    @SerializedName("conjuntivitis")
    public String conjuntivitis;
    @SerializedName("dolor_pecho")
    public String dolor_pecho;
    @SerializedName("falta_aire")
    public String falta_aire;
    @SerializedName("decil")
    public String decil;
    @SerializedName("status")
    public String status;

    @SerializedName("sospechoso")
    public String sospechoso;

    public PadronSms() {
    }

    public PadronSms(int id, String paterno, String materno, String nombre, String nombre_completo, String telefono, String folio, String fecha_inicio, String edad, String edad_cod, String sexo, String direccion, String calle, String numero_exterior, String numero_interior, String alcaldia, String colonia, String codigo_postal, String creacion_registro, String dia, int fiebre, int dolor_cabeza, int tos, int dolor_garganta, int dificultad_respirar, int dolor_cuerpo, int morbilidad, int fase, String fuente, String resultado, String color_azul, String escurrimiento, String dolor_respirar, String conjuntivitis, String dolor_pecho, String falta_aire, String decil, String status, String sospechoso) {
        this.id = id;
        this.paterno = paterno;
        this.materno = materno;
        this.nombre = nombre;
        this.nombre_completo = nombre_completo;
        this.telefono = telefono;
        this.folio = folio;
        this.fecha_inicio = fecha_inicio;
        this.edad = edad;
        this.edad_cod = edad_cod;
        this.sexo = sexo;
        this.direccion = direccion;
        this.calle = calle;
        this.numero_exterior = numero_exterior;
        this.numero_interior = numero_interior;
        this.alcaldia = alcaldia;
        this.colonia = colonia;
        this.codigo_postal = codigo_postal;
        this.creacion_registro = creacion_registro;
        this.dia = dia;
        this.fiebre = fiebre;
        this.dolor_cabeza = dolor_cabeza;
        this.tos = tos;
        this.dolor_garganta = dolor_garganta;
        this.dificultad_respirar = dificultad_respirar;
        this.dolor_cuerpo = dolor_cuerpo;
        this.morbilidad = morbilidad;
        this.fase = fase;
        this.fuente = fuente;
        this.resultado = resultado;
        this.color_azul = color_azul;
        this.escurrimiento = escurrimiento;
        this.dolor_respirar = dolor_respirar;
        this.conjuntivitis = conjuntivitis;
        this.dolor_pecho = dolor_pecho;
        this.falta_aire = falta_aire;
        this.decil = decil;
        this.status = status;
        this.sospechoso = sospechoso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEdad_cod() {
        return edad_cod;
    }

    public void setEdad_cod(String edad_cod) {
        this.edad_cod = edad_cod;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero_exterior() {
        return numero_exterior;
    }

    public void setNumero_exterior(String numero_exterior) {
        this.numero_exterior = numero_exterior;
    }

    public String getNumero_interior() {
        return numero_interior;
    }

    public void setNumero_interior(String numero_interior) {
        this.numero_interior = numero_interior;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCreacion_registro() {
        return creacion_registro;
    }

    public void setCreacion_registro(String creacion_registro) {
        this.creacion_registro = creacion_registro;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getFiebre() {
        return fiebre;
    }

    public void setFiebre(int fiebre) {
        this.fiebre = fiebre;
    }

    public int getDolor_cabeza() {
        return dolor_cabeza;
    }

    public void setDolor_cabeza(int dolor_cabeza) {
        this.dolor_cabeza = dolor_cabeza;
    }

    public int getTos() {
        return tos;
    }

    public void setTos(int tos) {
        this.tos = tos;
    }

    public int getDolor_garganta() {
        return dolor_garganta;
    }

    public void setDolor_garganta(int dolor_garganta) {
        this.dolor_garganta = dolor_garganta;
    }

    public int getDificultad_respirar() {
        return dificultad_respirar;
    }

    public void setDificultad_respirar(int dificultad_respirar) {
        this.dificultad_respirar = dificultad_respirar;
    }

    public int getDolor_cuerpo() {
        return dolor_cuerpo;
    }

    public void setDolor_cuerpo(int dolor_cuerpo) {
        this.dolor_cuerpo = dolor_cuerpo;
    }

    public int getMorbilidad() {
        return morbilidad;
    }

    public void setMorbilidad(int morbilidad) {
        this.morbilidad = morbilidad;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getColor_azul() {
        return color_azul;
    }

    public void setColor_azul(String color_azul) {
        this.color_azul = color_azul;
    }

    public String getEscurrimiento() {
        return escurrimiento;
    }

    public void setEscurrimiento(String escurrimiento) {
        this.escurrimiento = escurrimiento;
    }

    public String getDolor_respirar() {
        return dolor_respirar;
    }

    public void setDolor_respirar(String dolor_respirar) {
        this.dolor_respirar = dolor_respirar;
    }

    public String getConjuntivitis() {
        return conjuntivitis;
    }

    public void setConjuntivitis(String conjuntivitis) {
        this.conjuntivitis = conjuntivitis;
    }

    public String getDolor_pecho() {
        return dolor_pecho;
    }

    public void setDolor_pecho(String dolor_pecho) {
        this.dolor_pecho = dolor_pecho;
    }

    public String getFalta_aire() {
        return falta_aire;
    }

    public void setFalta_aire(String falta_aire) {
        this.falta_aire = falta_aire;
    }

    public String getDecil() {
        return decil;
    }

    public void setDecil(String decil) {
        this.decil = decil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSospechoso() {
        return sospechoso;
    }

    public void setSospechoso(String sospechoso) {
        this.sospechoso = sospechoso;
    }
}
