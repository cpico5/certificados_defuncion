package com.example.certificadosdefuncion;

public class Nombre  {

	public static final String customURL = "https://opinion.cdmx.gob.mx/certificados/";
	public static final String USUARIO = "usuario";
	public static final String ID_CERTIFICADO = "IDCERTIFICADO";
	public static final String TIPO = "tipo";
	public static final String PADRON = "padron";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String IMEI = "imei";
	public static final String encuesta = "ceda_callcenter";

	public String nombreEncuesta(){
		
		//final String nombreEncuesta = "censo_pueblos";
		final String nombreEncuesta = "certificados_difuntos";
		return nombreEncuesta;
	}
	
public String nombreDatos(){
		
		final String nombreEncuesta = "certificados_difuntos";
		return nombreEncuesta;
	}
	
}	 