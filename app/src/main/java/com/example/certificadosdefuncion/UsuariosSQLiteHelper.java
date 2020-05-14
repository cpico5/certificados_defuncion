package com.example.certificadosdefuncion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.UUID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    private static final String ENCODING = "ISO-8859-1";


    public static String getHostName(String defValue) {
        try {
            Method getString = Build.class.getDeclaredMethod("getString", String.class);
            getString.setAccessible(true);
            return getString.invoke(null, "net.hostname").toString();
        } catch (Exception ex) {
            return defValue;
        }
    }


    UUID uuid = UUID.randomUUID();

    public String tablet;
    InputStream datos, usuarios, nofue, acambio, prd, pri, pan, morena, independiente = null;

    static Nombre nom = new Nombre();
    static String nombreE = nom.nombreEncuesta();


    static String ID = getHostName(null);
    static String prefix = ID;

    // private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() +"/Mis_archivos/" +nombreE+"_"+prefix+"";
    private static final int DATABASE_VERSION = 6;

    public UsuariosSQLiteHelper(Context context, String name, CursorFactory factory, int version, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        try {
            datos = context.getAssets().open("datos.sql");
            usuarios = context.getAssets().open("usuarios.sql");

        } catch (Exception ex) {
            Log.i(null, "HORROR-1: " + ex.fillInStackTrace());
        }

    }

//////////////////////   TABLA DATOS  //////////////////////////////////////////////////////////////


    public static class TablaDatos {
        public static String TABLA_DATOS = "datos";
        public static String COLUMNA_SECCION = "seccion";
        public static String COLUMNA_DISTRITO = "distrito";
        public static String COLUMNA_MUNICIPIO = "municipio";
        public static String COLUMNA_DELEGACION = "delegacion";
        public static String COLUMNA_EQUIPOS = "equipo";
        public static String COLUMNA_COORDINADOR = "coordinador";

    }

    private static final String DATABASE_DATOS = "create table "
            + TablaDatos.TABLA_DATOS + "("
            + TablaDatos.COLUMNA_SECCION + " INTEGER not null, "
            + TablaDatos.COLUMNA_DISTRITO + " text not null, "
            + TablaDatos.COLUMNA_MUNICIPIO + " text not null, "
            + TablaDatos.COLUMNA_DELEGACION + " integer not null, "
            + TablaDatos.COLUMNA_EQUIPOS + " text not null, "
            + TablaDatos.COLUMNA_COORDINADOR + " text not null); ";


/////////////////////  TABLA REGISTROS  ///////////////////////////////////////////////

    public static class TablaRegistros {
        public static String TABLA_REGISTROS = "registros";
        public static String COLUMNA_CONSECUTIVO_DIARIO = "consecutivo_diario";
        public static String COLUMNA_USUARIO = "usuario";
        public static String COLUMNA_NOMBRE_PROGRAMA = "nombre_programa";
        public static String COLUMNA_FECHA = "fecha";
        public static String COLUMNA_HORA = "hora";
        public static String COLUMNA_imei = "imei";
        public static String COLUMNA_latitud = "latitud";
        public static String COLUMNA_longitud = "longitud";
        //INICIAN PREGUNTAS
        public static String COLUMNA_juzgado = "juzgado";
        public static String COLUMNA_certificado_acta = "certificado_acta";
        public static String COLUMNA_folio = "folio";
        public static String COLUMNA_paterno = "paterno";
        public static String COLUMNA_materno = "materno";
        public static String COLUMNA_nombres = "nombres";
        public static String COLUMNA_genero = "genero";
        public static String COLUMNA_curp = "curp";
        public static String COLUMNA_fecha_deceso = "fecha_deceso";

    }


    private static final String DATABASE_REGISTROS = "create table "
            + TablaRegistros.TABLA_REGISTROS + "("
            + "id integer primary key autoincrement,"
            + TablaRegistros.COLUMNA_CONSECUTIVO_DIARIO + " text not null, "
            + TablaRegistros.COLUMNA_USUARIO + " text not null, "
            + TablaRegistros.COLUMNA_NOMBRE_PROGRAMA + " text not null, "
            + TablaRegistros.COLUMNA_FECHA + " date not null, "
            + TablaRegistros.COLUMNA_HORA + " text not null, "
            + TablaRegistros.COLUMNA_imei + " text not null, "
            + TablaRegistros.COLUMNA_latitud + " text, "
            + TablaRegistros.COLUMNA_longitud + " text, "

            + TablaRegistros.COLUMNA_juzgado + " text, "
            + TablaRegistros.COLUMNA_certificado_acta + " text, "
            + TablaRegistros.COLUMNA_folio + " text, "
            + TablaRegistros.COLUMNA_paterno + " text, "
            + TablaRegistros.COLUMNA_materno + " text, "
            + TablaRegistros.COLUMNA_nombres + " text, "
            + TablaRegistros.COLUMNA_genero + " text, "
            + TablaRegistros.COLUMNA_curp + " text, "
            + TablaRegistros.COLUMNA_fecha_deceso + " text ); ";

    /////////////////////  TABLA FOTOS  ///////////////////////////////////////////////

    public static class TablaFotos {
        public static String TABLA_FOTOS = "fotos";
        public static String COLUMNA_CONSECUTIVO_DIARIO = "consecutivo_diario";
        public static String COLUMNA_USUARIO = "usuario";
        public static String COLUMNA_NOMBRE_PROGRAMA = "nombre_programa";
        public static String COLUMNA_FECHA = "fecha";
        public static String COLUMNA_HORA = "hora";
        public static String COLUMNA_imei = "imei";
        public static String COLUMNA_latitud = "latitud";
        public static String COLUMNA_longitud = "longitud";
        //INICIAN PREGUNTAS
        public static String COLUMNA_folio = "folio";
        public static String COLUMNA_numero_foto = "numero_foto";
        public static String COLUMNA_nombre_foto = "nombre_foto";
        public static String COLUMNA_tipo = "tipo";

    }


    private static final String DATABASE_FOTOS = "create table "
            + TablaFotos.TABLA_FOTOS + "("
            + "id integer primary key autoincrement,"
            + TablaFotos.COLUMNA_CONSECUTIVO_DIARIO + " text not null, "
            + TablaFotos.COLUMNA_USUARIO + " text not null, "
            + TablaFotos.COLUMNA_NOMBRE_PROGRAMA + " text not null, "
            + TablaFotos.COLUMNA_FECHA + " date not null, "
            + TablaFotos.COLUMNA_HORA + " text not null, "
            + TablaFotos.COLUMNA_imei + " text not null, "
            + TablaFotos.COLUMNA_latitud + " text, "
            + TablaFotos.COLUMNA_longitud + " text, "

            + TablaFotos.COLUMNA_folio + " text, "
            + TablaFotos.COLUMNA_numero_foto + " text, "
            + TablaFotos.COLUMNA_tipo + " text, "
            + TablaFotos.COLUMNA_nombre_foto + " text ); ";

////////////////////////////  TABLA USUARIOS	 /////////////////////////////////////////////////////////    

    public static class TablaUsuarios {
        public static String TABLA_USUARIOS = "usuarios";
        public static String COLUMNA_USUARIO = "usuario";
        public static String COLUMNA_PASSWORD = "password";

    }

    private static final String DATABASE_USUARIOS = "create table "
            + TablaUsuarios.TABLA_USUARIOS + "("
            + TablaUsuarios.COLUMNA_USUARIO + " text not null, "
            + TablaUsuarios.COLUMNA_PASSWORD + " text not null); ";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DATABASE_REGISTROS);
        db.execSQL(DATABASE_FOTOS);


    }


    public void cargaDatos(SQLiteDatabase db) {
        InputStream tabla = datos;
        try {

            if (tabla != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(tabla, ENCODING));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.i(null, "HORROR-2: " + ex.getMessage());
        } finally {
            db.endTransaction();
            if (tabla != null) {
                try {
                    tabla.close();
                } catch (IOException e) {
                    Log.i(null, "HORROR-3; " + e.getMessage());
                }
            }
        }

    }

    public void cargaUsuarios(SQLiteDatabase db) {
        InputStream tabla = usuarios;
        try {

            if (tabla != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(tabla, ENCODING));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.i(null, "HORROR-2: " + ex.getMessage());
        } finally {
            db.endTransaction();
            if (tabla != null) {
                try {
                    tabla.close();
                } catch (IOException e) {
                    Log.i(null, "HORROR-3; " + e.getMessage());
                }
            }
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("drop table if exists " + TablaRegistros.TABLA_REGISTROS);
        db.execSQL("drop table if exists " + TablaFotos.TABLA_FOTOS);
        onCreate(db);
    }
}
