package com.example.certificadosdefuncion;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.certificadosdefuncion.R;
import com.example.certificadosdefuncion.model.DatoContent;
import com.example.certificadosdefuncion.model.Imagen;
import com.example.certificadosdefuncion.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.certificadosdefuncion.Nombre.ID_CERTIFICADO;
import static com.example.certificadosdefuncion.Nombre.TIPO;
import static com.example.certificadosdefuncion.Nombre.USUARIO;
import static com.example.certificadosdefuncion.Nombre.customURL;


public class FotoEvidencia extends Activity {

    public static String getHostName(String defValue) {
        try {
            Method getString = Build.class.getDeclaredMethod("getString", String.class);
            getString.setAccessible(true);
            return getString.invoke(null, "net.hostname").toString();
        } catch (Exception ex) {
            return defValue;
        }
    }


    private String TAG = "FotoEvidencia";
    static String ID = getHostName(null);
    static String prefix = ID;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df1 = new SimpleDateFormat("yyy-MM-dd");
    String formattedDate1 = df1.format(c.getTime());

    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
    String formattedDate2 = df2.format(c.getTime());

    SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
    String formattedDate3 = df3.format(c.getTime());

    SimpleDateFormat df6 = new SimpleDateFormat("MM");
    String formattedDate6 = df6.format(c.getTime());

    SimpleDateFormat df7 = new SimpleDateFormat("dd");
    String formattedDate7 = df7.format(c.getTime());

    SimpleDateFormat df4 = new SimpleDateFormat("HH:mm:ss a");
    String formattedDate4 = df4.format(c.getTime());

    SimpleDateFormat df5 = new SimpleDateFormat("HH:mm:ss");
    String formattedDate5 = df5.format(c.getTime());

    UsuariosSQLiteHelper3 usdbh3;
    private SQLiteDatabase db3;

    private ImageButton camara;
    private ImageView imagen;
    private ImageView imagen2;
    private ImageView imagen3;
    private TextView Texto;
    //	private EditText nombreImagen;
    private String nombreImagen;
    private String nombreImagen2;
    private String nombreImagen3;
    private Button Guarda;
    private Uri output;
    private String foto;
    private String foto2;
    private String foto3;
    private File file;
    private File file2;
    private File file3;

    private String video;
    private String nombreVideo;

    private String la_foto;
    private String nombreF;
    private String nombreD;

    private File file4;
    private File file5;
    private File file6;
    List<String> list;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    private Spinner spinnerPUBLICO;
    String alcaldia, laCasilla, distF, distL;

    UsuariosSQLiteHelper usdbh;
    private Spinner spinner;
    private Spinner spinner2;
    UsuariosSQLiteHelper Udb;
    private SQLiteDatabase db;
    double latitude;
    double longitude;
    String laLatitud;
    String laLongitud;
    public String tiempo;
    String date = formattedDate1.toString();
    public String maximo = "";
    int elMaximo;
    String laSeccion;

    public EditText comentario;
    public EditText editNombreFoto;

    // NORMAL
    Nombre nom = new Nombre();
    String nombreE = nom.nombreEncuesta();

    String upLoadServerUriFotos = "https://opinion.cdmx.gob.mx/cgi-bin/php/recibeFotos" + nombreE + ".php?encuesta=" + nombreE + "";
    int serverResponseCode = 0;

    private static final int READ_BLOCK_SIZE = 100000;

    public String tablet;

//    public String sacaChip(){
//		String deviceId = Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);
//		tablet=deviceId;	
//		return tablet;
//	}
//    public String sacaChip(){
//		prefix = ID.substring(8);
//		return prefix;
//	}
//    public String sacaChip(){
//    	String deviceId = Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);
//        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        String Imei = tManager.getDeviceId();
//    	return Imei;
//    	}

    public String sacaChip() {
        String deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        tablet = deviceId;
        return tablet;
    }

    public String cachaNombre() {
        Bundle datos = this.getIntent().getExtras();
        String Nombre = datos.getString("Nombre");
        return Nombre;
    }

    public String cachaCuantos() {
        Bundle datos = this.getIntent().getExtras();
        String cuantos = datos.getString("cuantos");
        return cuantos;
    }

    public String cachaLatitud() {
        Bundle datos = this.getIntent().getExtras();
        String latitud = datos.getString("latitud");
        return latitud;
    }

    public String cachaFolio() {
        Bundle datos = this.getIntent().getExtras();
        String folio = datos.getString("folio");
        return folio;
    }

    public String cachaLongitud() {
        Bundle datos = this.getIntent().getExtras();
        String longitud = datos.getString("longitud");
        return longitud;
    }

    public String cachaNumeracion() {
        Bundle datos = this.getIntent().getExtras();
        String numeracion = datos.getString("numeracion");
        return numeracion;
    }

    public String cachaSeccionGPS() {
        Bundle datos = this.getIntent().getExtras();
        String seccion_gps = datos.getString("seccion_gps");
        return seccion_gps;
    }

    public String cachaSeccion() {
        Bundle datos = this.getIntent().getExtras();
        String seccion = datos.getString("seccion");
        return seccion;
    }


    public Integer cachaConsecutivoDiario() {
        Bundle datos = this.getIntent().getExtras();
        Integer consecutivo_diario = datos.getInt("consecutivo_diario");
        return consecutivo_diario;
    }


    public String cachaDiario() {

        elMaximo = Integer.parseInt(sacaMaximo().toString());

        String diario = String.valueOf(elMaximo);

        if (diario.length() == 1) {
            diario = "00" + diario;
        } else if (diario.length() == 2) {
            diario = "0" + diario;
        } else {
        }
        return diario;
    }

    public String cachaUsuario() {
        Bundle datos = this.getIntent().getExtras();
        String usuario = datos.getString("usuario");
        return usuario;
    }

    public String cachaDireccion() {
        Bundle datos = this.getIntent().getExtras();
        String direccion = datos.getString("Direccion");
        return direccion;
    }

    public String cachaNombreE() {
        Bundle datos = this.getIntent().getExtras();
        String nombre_encuesta = datos.getString("nombre_encuesta");
        return nombre_encuesta;
    }

    public String cachaTablet() {
        Bundle datos = this.getIntent().getExtras();
        String tablet = datos.getString("tablet");
        return tablet;
    }


    public String cachaMax() {
        Bundle datos = this.getIntent().getExtras();
        String max = datos.getString("max");
        return max;
    }

    public String cachaId() {
        Bundle datos = this.getIntent().getExtras();
        String id = datos.getString("id");
        return id;
    }

    public String cachaIdNuevo() {
        Bundle datos = this.getIntent().getExtras();
        String id_nuevo = datos.getString("id_nuevo");
        return id_nuevo;
    }

    public String cachaDelegacion() {
        Bundle datos = this.getIntent().getExtras();
        String delegacion = datos.getString("delegacion");
        return delegacion;
    }

    public String cachaCargo() {
        //   Bundle datos=this.getIntent().getExtras();
        String cargo = "Presidente";
        return cargo;
    }

    public String cachaDescripcion() {
        //Bundle datos=this.getIntent().getExtras();
        String descripcion = "Descripcion";
        return descripcion;
    }

    public String cachaQuien() {
        //Bundle datos=this.getIntent().getExtras();
        String quien = "Quien";
        return quien;
    }

    public String nombreArchivo() {
        String date = formattedDate1.toString();
        String var2 = ".txt";
        String var3 = date + var2;
        final String nombre = date + "_" + cachaTablet() + "_PRESIDENTE";
        return nombre;
    }

    public long t1() {
        Bundle datos = this.getIntent().getExtras();
        long t1 = datos.getLong("t1");
        return t1;
    }

    public String sacaImei() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
            System.out.println("Mi N�mero: " + szImei);
        }
        String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
        System.out.println("Mi N�mero: " + szImei);

        return szImei;
    }

    public String NombreD;

    public String elTiempo() {
        //Para la diferenci entre tiempos
        Calendar t2 = Calendar.getInstance();
        long milis2 = t2.getTimeInMillis();
        long diff = milis2 - t1();


        //System.out.println("CALCULOS 2");
        // calcular la diferencia en minutos
        long diffSegundos = diff / 1000;

        long diffMinutos = diffSegundos / 60;

        long residuo = diffSegundos % 60;

        System.out.println(diffSegundos);
        System.out.println(diffMinutos);
        System.out.println(residuo);

//	    			DecimalFormat df = new DecimalFormat("#.##");
//	    			DecimalFormat df1 = new DecimalFormat("#");

        tiempo = diffMinutos + ":" + residuo;

        return tiempo;

    }


    public void insertaFoto(Imagen imagen) {

        FileOutputStream fout = null;
        try {
            // INSERTA EN LA BASE DE DATOS //

            final String F = "File dbfile";

            Nombre nom = new Nombre();
            String nombreE = nom.nombreEncuesta();

            String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreE + "_"
                    + sacaImei() + "";

            // Abrimos la base de datos 'DBUsuarios' en modo escritura
            usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

            db = usdbh.getWritableDatabase();

            // NORMAL

            GPSTracker gps = new GPSTracker(this);
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            String strLatitud = String.valueOf(latitude);
            String strLongitud = String.valueOf(longitude);

            if (latitude == 0.0) {
                if (sacaLatitud() == null) {
                    latitude = 0.0;
                } else {
                    latitude = Double.valueOf(sacaLatitud());
                }

            }

            if (longitude == 0.0) {
                if (sacaLongitud() == null) {
                    longitude = 0.0;
                } else {
                    longitude = Double.valueOf(sacaLongitud());
                }

            }

            long consecutivoObtenido = 0;
            ContentValues values = new ContentValues();

            if (db != null) {

                values.put("consecutivo_diario", cachaConsecutivoDiario());
                values.put("usuario", cachaNombre().toUpperCase());
                values.put("nombre_programa", nombreE.toUpperCase());
                values.put("fecha", formattedDate1);
                values.put("hora", formattedDate5);
                values.put("imei", sacaImei() == null ? "0" : sacaImei());
                values.put("latitud", strLatitud);
                values.put("longitud", strLongitud);

                values.put("folio", cachaFolio());
                values.put("numero_foto", imagen.getNumero_foto());
                values.put("tipo", tipo);
                values.put("nombre_foto", imagen.getPathImagen());

                consecutivoObtenido = db.insert("fotos", null, values);
            }

            values.put("consecutivo", consecutivoObtenido);
            guardaEncuestaWS(values, imagen);


            //db.close();

            System.out.println("Latitud  " + strLatitud);
            System.out.println("Longitud  " + strLongitud);

            // FIN INSERTA BASE DE DATOS //

        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i(TAG, "================>> Inserta registros" + stackTrace);

        }

    }

    private void guardaEncuestaWS(ContentValues values, final Imagen imagenRecibida) {

        showProgress(true);

        String consecutivo = "";
        String usuarios = "";
        String imei = "";
        String fecha = "";
        String hora = "";
        String latitud = "";
        String longitud = "";
        String folio = "";
        String numero_foto = "";
        String nombre_foto = "";


        //RECORRE CONTENTVALUES
        DatoContent datoContent = new DatoContent();
        List<DatoContent> listaContenido = new ArrayList();
        Set<Map.Entry<String, Object>> s = values.valueSet();
        Iterator itr = s.iterator();
        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            String key = me.getKey().toString();
            Object value = me.getValue();

            datoContent = new DatoContent();
            datoContent.setKey(key);
            datoContent.setValue(String.valueOf(value));
            listaContenido.add(datoContent);

            if (key.equals("consecutivo_diario"))
                consecutivo = String.valueOf(value);
            if (key.equals("usuario"))
                usuarios = String.valueOf(value);
            if (key.equals("imei"))
                imei = String.valueOf(value);
            if (key.equals("fecha"))
                fecha = String.valueOf(value);
            if (key.equals("hora"))
                hora = String.valueOf(value);
            if (key.equals("latitud"))
                latitud = String.valueOf(value);
            if (key.equals("longitud"))
                longitud = String.valueOf(value);
            if (key.equals("folio"))
                folio = String.valueOf(value);
            if (key.equals("numero_foto"))
                numero_foto = String.valueOf(value);
            if (key.equals("nombre_foto"))
                nombre_foto = String.valueOf(value);

        }

        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<DatoContent>>() {
        }.getType();
        String json = gson.toJson(listaContenido, collectionType);

        RequestParams params = new RequestParams();
        params.put("api", "guarda_fotos");
        params.put("id_usuario", usuario.getId());
        params.put("certificado_id", idCertificado);

        params.put("consecutivo", consecutivo);
        params.put("usuario", usuarios);
        params.put("imei", imei);
        params.put("fecha", fecha);
        params.put("hora", hora);
        params.put("latitud", latitud);
        params.put("longitud", longitud);
        params.put("folio", folio);
        params.put("numero_foto", numero_foto);
        params.put("nombre_foto", nombre_foto);
        params.put("tipo", tipo);

        params.put("folio_acta", txtFolioActa != null ? txtFolioActa.getText().toString().toUpperCase() : "");
        params.put("paterno_acta", editPregunta4 != null ? editPregunta4.getText().toString().toUpperCase() : "");
        params.put("materno_acta", editPregunta5 != null ? editPregunta5.getText().toString().toUpperCase() : "");
        params.put("nombres_acta", editPregunta6 != null ? editPregunta6.getText().toString().toUpperCase() : "");
        params.put("genero_acta", op7.toUpperCase());
        params.put("curp_acta", editPregunta8 != null ? editPregunta8.getText().toString().toUpperCase() : "");
        params.put("fecha_acta", editFechaDeceso != null ? editFechaDeceso.getText() : "");

        try {
            File file = new File(nombre_foto);
            params.put("photo", file);
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        //client.addHeader("Authorization", "Bearer " + usuario.getToken());
        client.setTimeout(60000);

        RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //showProgress(false);
                try {

                    Log.d(TAG, "pimc -----------> Respuesta OK ");
                    Log.d(TAG, "pimc -----------> " + new String(responseBody));
                    String json = new String(responseBody);

                    if (json != null && !json.isEmpty()) {

                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);

                        String login = jsonObject.getJSONObject("response").get("code").toString();
                        if (Integer.valueOf(login) == 1) {
                            int idCertificadoss = 0;

                            String idCertificados = jsonObject.getJSONObject("data").getString("certificado_id");
                            idCertificadoss = Integer.valueOf(idCertificados);

                            if (idCertificadoss != 0) {
                                //borra la imagen
                                imagenRecibida.setEnviado(1);

                                File fichero = new File(imagenRecibida.getPathImagen());
                                fichero.delete();

                                imagen.setImageResource(0);
                                listaImagenes.remove(imagenRecibida);
                                fillImagen();

                                count++;
                                if(count >= total){

                                    showProgress(false);

                                    if(tipo.equals("certificado")){

                                        Intent i = new Intent(FotoEvidencia.this, FotoEvidencia.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        i.putExtra("Nombre", cachaNombre());
                                        //i.putExtra("cuantos", String.valueOf(tantos));
                                        i.putExtra("consecutivo_diario", cachaConsecutivoDiario());
                                        i.putExtra("folio", cachaFolio());
                                        i.putExtra(USUARIO,usuario);
                                        i.putExtra(ID_CERTIFICADO,idCertificado);
                                        i.putExtra(TIPO,"acta");
                                        startActivity(i);
                                        finish();

                                    }else{

                                        Intent i = new Intent(FotoEvidencia.this, MainActivityPantalla1.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        i.putExtra("Nombre", cachaNombre());
                                        //i.putExtra("cuantos", String.valueOf(tantos));
                                        i.putExtra("consecutivo_diario", cachaConsecutivoDiario());
                                        i.putExtra("folio", cachaFolio());
                                        i.putExtra(USUARIO,usuario);
                                        i.putExtra(ID_CERTIFICADO,idCertificado);
                                        startActivity(i);
                                        finish();

                                    }
                                }

                            }

                            //Guarda.setEnabled(true);
                            //dialogo();


                        } else {
                            showProgress(false);
                            Guarda.setEnabled(true);
                            Toast.makeText(FotoEvidencia.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Guarda.setEnabled(true);
                    showProgress(false);
                    Toast.makeText(FotoEvidencia.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showProgress(false);
                Guarda.setEnabled(true);
                try {
                    Log.e("guarda datos", "PIMC-----------------> existe un error en la conexi?n -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d("guarda datos", "pimc -----------> " + new String(responseBody));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (statusCode != 200) {
                    Log.e("guarda datos", "Existe un error en la conexi?n -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d("guarda datos", "pimc -----------> " + new String(responseBody));

                }

                Toast.makeText(FotoEvidencia.this, "Error de conexion, intente de nuevo", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void dialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialog).setTitle("IMPORTANTE").setCancelable(false)
                .setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

//						detenerGrabacion();

                        if (!listaImagenes.isEmpty()) {
                            total = listaImagenes.size();
                            for (Imagen imagen : listaImagenes) {
                                if(imagen.getEnviado() == 0)
                                    insertaFoto(imagen);
                            }
                            //showProgress(false);
                            //Guarda.setEnabled(true);
                        }


                        /*Intent i = new Intent(FotoEvidencia.this, MainActivityPantalla1.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra(USUARIO,usuario);
                        startActivity(i);
                        finish();*/

                        //System.exit(0); // metodo que se debe implementar
                    }
                }).setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                showProgress(false);
                Guarda.setEnabled(true);

//						detenerGrabacion();
               /* Intent i = new Intent(FotoEvidencia.this, MainActivityPantalla1.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Nombre", cachaNombre());
                //i.putExtra("cuantos", String.valueOf(tantos));
                i.putExtra("consecutivo_diario", cachaConsecutivoDiario());
                i.putExtra("folio", cachaFolio());
                i.putExtra(USUARIO, usuario);
                startActivity(i);
                finish();*/

                /*Integer tantos = Integer.valueOf(cachaCuantos()) + 1;

                Intent i = new Intent(FotoEvidencia.this, FotoEvidencia.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Nombre", cachaNombre());
                i.putExtra("cuantos", String.valueOf(tantos));
                i.putExtra("consecutivo_diario", cachaConsecutivoDiario());
                i.putExtra("folio", cachaFolio());
                i.putExtra(USUARIO,usuario);
                i.putExtra(ID_CERTIFICADO,idCertificado);

                startActivity(i);
                //System.exit(0); // metodo que se debe implementar
                finish();*/
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    ///////    METODO PARA VERIFICAR LA CONEXI�N A INTERNET
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No s�lo wifi, tambi�n GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle deber�a no ser tan �apa
        for (int i = 0; i < 2; i++) {
            // �Tenemos conexi�n? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }


    //EVENTO AL PULSAR EL BOTON ATRAS

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi bot�n al pulsar ir a atr�s
            Toast.makeText(getApplicationContext(), "No puedes regresar \n\nToma la Foto",
                    Toast.LENGTH_SHORT).show();

//        	dialogoAbandono();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Usuario usuario;
    private int idCertificado = 0;
    private String tipo ="";
    private View mProgressView;
    private Imagen fotoActual;
    private List<Imagen> listaImagenes = new ArrayList<>();

    private ImagenAdapter imagenAdapter;
    private RecyclerView recyclerFoto;
    private RecyclerView.LayoutManager layoutManager;
    private int contador = 1;
    private int count = 0;
    private int total = 0;

    public ImageButton imageButtonVale;
    TextView textViewVivienda;
    public RadioGroup rdPregunta7;
    EditText txtFolioActa,editPregunta4,editPregunta5,editPregunta6,editPregunta8,editFechaDeceso;
    LinearLayout linearActa;
    private String dialog = "";
    private String op7 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.foto_evidencia);

        Intent startingIntent = getIntent();
        if (startingIntent == null) {
            finish();
            return;
        }

        if (savedInstanceState != null) {
            usuario = (Usuario) savedInstanceState.getSerializable(USUARIO);
            idCertificado = (Integer) savedInstanceState.getSerializable(ID_CERTIFICADO);
            tipo = (String) savedInstanceState.getSerializable(TIPO);
        } else {
            usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
            idCertificado = (Integer) startingIntent.getSerializableExtra(ID_CERTIFICADO);
            tipo = (String) startingIntent.getSerializableExtra(TIPO);
        }

        mProgressView = findViewById(R.id.login_progressMain);
        recyclerFoto = (RecyclerView) findViewById(R.id.fotos_evidencia_recycler);
        recyclerFoto.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerFoto.setLayoutManager(layoutManager);


        //cargo ls nuevos controlers
        textViewVivienda = findViewById(R.id.textViewVivienda);
        textViewVivienda.setText("Cargue Certificado");

        txtFolioActa = findViewById(R.id.txtFolioActa);
        linearActa = findViewById(R.id.linearActa);
        linearActa.setVisibility(View.GONE);

        editPregunta4= (EditText)findViewById(R.id.editPregunta4);
        editPregunta5= (EditText)findViewById(R.id.editPregunta5);
        editPregunta6= (EditText)findViewById(R.id.editPregunta6);
        editPregunta8= (EditText)findViewById(R.id.editPregunta8);
        editFechaDeceso= (EditText)findViewById(R.id.editFechaDeceso);
        editFechaDeceso.setEnabled(false);
        imageButtonVale = (ImageButton) findViewById(R.id.imageButtonVale);
        rdPregunta7 = (RadioGroup)findViewById(R.id.rdPregunta7);

        rdPregunta7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op7 = "Hombre";
                }
                else if (checkedId == R.id.radio2) {
                    op7 = "Mujer";
                }
                else if (checkedId == R.id.radio0) {
                    op7 = "Otro";
                }
            }
        });

        final Calendar myCalendarioF = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                //myCalendarioF.set(Calendar.YEAR, year);
                myCalendarioF.set(Calendar.YEAR, year);
                myCalendarioF.set(Calendar.MONTH, monthOfYear);
                myCalendarioF.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                editFechaDeceso.setText(sdf.format(myCalendarioF.getTime()));

            }

        };

        imageButtonVale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FotoEvidencia.this,dateFecha, myCalendarioF
                        .get(Calendar.YEAR), myCalendarioF.get(Calendar.MONTH),
                        myCalendarioF.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dialog = "Finaliza captura de Certificado";

        if(tipo.equals("acta")){
            textViewVivienda.setText("Cargue Acta");
            linearActa.setVisibility(View.VISIBLE);
            dialog = "Finaliza captura de Acta";
        }

        Thread.setDefaultUncaughtExceptionHandler(new Crash(this));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        try {

//        	 //para desactivar el wifi
//            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE); 
////            wifiManager.setWifiEnabled(true);
//            wifiManager.setWifiEnabled(false);


            Nombre nom = new Nombre();
            String nombreE = nom.nombreEncuesta();

            GPSTracker gps = new GPSTracker(this);
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            String strLatitud = String.valueOf(latitude);
            String strLongitud = String.valueOf(longitude);


            if (latitude == 0.0) {
                if (sacaLatitud() == null) {
                    latitude = 0.0;
                } else {
                    latitude = Double.valueOf(sacaLatitud());
                }

            }

            if (longitude == 0.0) {
                if (sacaLongitud() == null) {
                    longitude = 0.0;
                } else {
                    longitude = Double.valueOf(sacaLongitud());
                }

            }


            String seccion = cachaSeccion();

            elMaximo = Integer.parseInt(sacaMaximo().toString());

            String diario = String.valueOf(elMaximo);


            nombreD = formattedDate3 + "_" + sacaImei() + "_" + cachaNombre() + "_" + cachaConsecutivoDiario() + "_" + contador + "_" + idCertificado + ".jpeg";

            Log.i("Foto", "NombreD: " + nombreD);


            imagen = (ImageView) findViewById(R.id.imagen);
            /*Texto = (TextView) findViewById(R.id.textViewVivienda);

            Texto.setText("Foto Documento");*/

            la_foto = Environment.getExternalStorageDirectory() + "/Fotos/FotosCertificadoRegistro" + formattedDate3 + "N" + "/" + nombreD.trim();
            File fileF = new File(la_foto);

            if (fileF.exists()) {
                Bitmap thumb = BitmapFactory.decodeFile(la_foto);
                // thumb = Bitmap.createScaledBitmap(thumb, 600, 400, false);
                imagen.setImageBitmap(thumb);

            } else {
//			imagen2.setImageResource(R.drawable.morena_logo);
            }


            camara = (ImageButton) findViewById(R.id.camara);

//        camara.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                elMaximo = Integer.parseInt(sacaMaximo().toString());
//
//                String diario= String.valueOf(elMaximo);
//
//                if(diario.length()==1){
//                    diario="00"+diario;
//                }else if(diario.length()==2){
//                    diario="0"+diario;
//                }else{
//                }
//
//
//
//
////                String seccion = cachaSeccion();
////
////                nombreD = diario+"_"+seccion+"_"+cachaNombre()+"_"+sacaImei()+"_"+date;
//
//				nombreImagen = nombreD;
//
//
//
//                    if (!nombreImagen.trim().equalsIgnoreCase("")) {
//                        getCamara();
//                    } else
//                        Toast.makeText(FotoEvidencia.this, "Debe nombrar el archivo primero", Toast.LENGTH_LONG)
//                                .show();
//                	}
//
//
//        });


            Guarda = (Button) findViewById(R.id.guardar);

            //PARA VER SI ESTA O NO EST� CONECTADO


//            Toast.makeText(getBaseContext(),"Sin Conexion...!", Toast.LENGTH_LONG).show();
            Guarda.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (linearActa.getVisibility() == View.VISIBLE && txtFolioActa.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  Folio Acta",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (linearActa.getVisibility() == View.VISIBLE && editPregunta4.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  APELLIDO PATERNO",Toast.LENGTH_LONG).show();
                        return;
                    }
                    /*if (linearActa.getVisibility() == View.VISIBLE && editPregunta5.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  Folio Acta",Toast.LENGTH_LONG).show();
                        return;
                    }*/
                    if (linearActa.getVisibility() == View.VISIBLE && editPregunta6.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  NOMBRE",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (linearActa.getVisibility() == View.VISIBLE && op7.toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  GENERO",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (linearActa.getVisibility() == View.VISIBLE && txtFolioActa.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  Folio Acta",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (linearActa.getVisibility() == View.VISIBLE && editPregunta8.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA:  CURP",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (linearActa.getVisibility() == View.VISIBLE && editFechaDeceso.getText().toString().trim().length()==0 ){
                        Toast.makeText(FotoEvidencia.this,"CAPTURA: FECHA DEFUNCION",Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (!listaImagenes.isEmpty()) {
                        Log.i("datos f", "Solo hay foto");
                        Guarda.setEnabled(false);
                        //insertaFoto();
                        dialogo();


                    } else {
                        Toast.makeText(FotoEvidencia.this, "Debe Tomar Una Foto", Toast.LENGTH_LONG).show();
                        Log.i("datos f", "No hay Nada");
                    }
                }
            });

        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i("encuestas", "Error todo OnCreate" + stackTrace);
        }//////FIN CATCH ONCREATE/////////////////////////////
    }

    class cambia extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        protected void onPreExecute() {
            super.onPreExecute();
//			dialog = new ProgressDialog(FotoDocumento.this);
//			dialog.setMessage("Enviando");
//			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//			dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {

            //guardarArchivo();
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//			dialog.dismiss();

        }

    }


    public void camara(View view) {

        elMaximo = Integer.parseInt(sacaMaximo().toString());

        String diario = String.valueOf(elMaximo);

        if (diario.length() == 1) {
            diario = "00" + diario;
        } else if (diario.length() == 2) {
            diario = "0" + diario;
        } else {
        }


//                String seccion = cachaSeccion();
//
//                nombreD = diario+"_"+seccion+"_"+cachaNombre()+"_"+sacaImei()+"_"+date;

        nombreImagen = nombreD;


        if (!nombreImagen.trim().equalsIgnoreCase("")) {
            getCamara();
        } else
            Toast.makeText(FotoEvidencia.this, "Debe nombrar el archivo primero", Toast.LENGTH_LONG)
                    .show();

    }


    public void dialogoCierre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea continuar Encuestando..?")
                .setTitle("IMPORTANTE")
                .setCancelable(false)
                .setNegativeButton("SALIR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Intent i = new Intent(FotoEvidencia.this, Entrada.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                System.exit(0); // metodo que se debe implementar
                            }
                        })
                .setPositiveButton("CONTINUAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent i = new Intent(FotoEvidencia.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("Nombre", cachaNombre());
                                i.putExtra("max", "0");
                                startActivity(i);
                                System.exit(0); // metodo que se debe implementar
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private void getCamara() {

        elMaximo = Integer.parseInt(sacaMaximo().toString());
        int numero = 0;

        if (cachaNumeracion() == null) {
            numero = numero + 1;
        } else {
            numero = Integer.valueOf(cachaNumeracion());
        }


        String diario = String.valueOf(elMaximo);
        String diario_control = String.valueOf(elMaximo);

        if (diario.length() == 1) {
            diario = "00" + diario;
        } else if (diario.length() == 2) {
            diario = "0" + diario;
        } else {
            diario = diario;
        }

        foto = Environment.getExternalStorageDirectory() + "/Fotos/FotosCertificadoRegistro" + formattedDate3 + "N" + "/" + nombreD.trim();
        Uri photoURI = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", new File(foto));

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(intent, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            setPic(data);
        }
    }

    private void setPic(Intent data) {

        int targetW = imagen.getWidth();
        int targetH = imagen.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(foto, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(foto, bmOptions);
        imagen.setImageBitmap(bitmap);

        //llena el objeto y la lista
        fotoActual = new Imagen();
        fotoActual.setEnviado(0);
        fotoActual.setPathImagen(foto);
        fotoActual.setIdCertificado(idCertificado);
        fotoActual.setNumero_foto(contador);
        fotoActual.setTipoImagen(tipo);

        listaImagenes.add(fotoActual);

        contador++;

        nombreD = formattedDate3 + "_" + sacaImei() + "_" + cachaNombre() + "_" + cachaConsecutivoDiario() + "_" + contador + "_" + idCertificado + ".jpeg";

        fillImagen();

    }

    private void fillImagen() {

        imagenAdapter = new ImagenAdapter(listaImagenes);
        imagenAdapter.setOnItemClickListener(onItemClickListener);
        imagenAdapter.setOnItemClickListenerDelete(onItemClickListenerDelete);
        recyclerFoto.addItemDecoration(new DividerItemDecoration(recyclerFoto.getContext(), DividerItemDecoration.VERTICAL));
        recyclerFoto.setAdapter(imagenAdapter);

    }

    private View.OnClickListener onItemClickListenerDelete = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            fotoActual = listaImagenes.get(position);
            //debe mostrar la imagen en el image view
            imagen.setImageResource(0);
            listaImagenes.remove(fotoActual);
            File fichero = new File(fotoActual.getPathImagen());
            fichero.delete();

            fillImagen();

        }
    };

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            fotoActual = listaImagenes.get(position);

            int targetW = imagen.getWidth();
            int targetH = imagen.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(foto, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(fotoActual.getPathImagen(), bmOptions);
            imagen.setImageBitmap(bitmap);


            //ImageTool.setPic(imagen,fotoActual.getPathImagen());

        }
    };

    private String sacaMaximo() {

        Set<String> set = new HashSet<String>();

        final String F = "File dbfile";

        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreE + "_"
                + sacaImei() + "";
        usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

        db = usdbh.getReadableDatabase();

        String selectQuery = "SELECT count(*) FROM registros where fecha='" + formattedDate3 + "'";
        Log.i("Foto", "==========> Query sacaMaximo: " + selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                maximo = cursor.getString(0);

            } while (cursor.moveToNext());
        }

        cursor.close();
//    		db.close();

        return maximo;
    }

    private String sacaConsecutivo() {

        Set<String> set = new HashSet<String>();

        final String F = "File dbfile";

        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreE + "_"
                + sacaImei() + "";
        usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

        db = usdbh.getReadableDatabase();

        String selectQuery = "SELECT count(*) FROM registros order by id desc limit 1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                maximo = cursor.getString(0);

            } while (cursor.moveToNext());
        }

        cursor.close();
//    		db.close();

        return maximo;
    }


    //////////   UPLOAD DE FOTOS ////////

    //Enviar Fotos


    public int uploadFotos(String sourceFileUri, String fech) {

        File sdCard;
        sdCard = Environment.getExternalStorageDirectory();
        //final String pathFotos = sdCard.getAbsolutePath() + "/"+ nombreEncuesta+"-Audio"+fech;
        final String pathFotos = sdCard.getAbsolutePath() + "/Fotos/FotosCertificadoRegistro" + formattedDate3 + "N";

        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

//		     dialog.dismiss(); 
            Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + pathFotos + "" + "/" + "20161124_002_359083065132816_1.jpg");
            runOnUiThread(new Runnable() {
                public void run() {

                }
            });

            return 0;

        } else {
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUriFotos);
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\""
                        + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("Foto", "HTTP Response fotos is : " + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    + " http://www.androidexample.com/media/uploads/"
                                    + "20161124_002_359083065132816_1.jpg";

//		                      Toast.makeText(Entrada.this, "File Upload Complete."+msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

//		        dialog.dismiss();  
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//		                messageText.setText("MalformedURLException Exception : check script url.");
//		                Toast.makeText(CalendarViewFotos.this, "MalformedURLException", 
//		                                                    Toast.LENGTH_SHORT).show();
                    }
                });

                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server " + "error: " + ex.getMessage());

//		        Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
            } catch (Exception e) {

//		        dialog.dismiss();  
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//		                messageText.setText("Error de Internet");
//		                Toast.makeText(CalendarViewFotos.this, "Error de Internet", 
//		                        Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server Exception " + "Exception : " + e.getMessage());

//		        Log.e("Upload file to server Exception", "Exception : "
//		                                         + e.getMessage(), e);  
            }
            return serverResponseCode;

        } // End else block
    }

    class UpdateFotos extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Foto", "CarlosQ:  Entra ====>");
        }


        @Override
        protected String doInBackground(String... params) {


            final String date2 = formattedDate3;
            File sdCard;
            sdCard = Environment.getExternalStorageDirectory();
            final String pathFotos = sdCard.getAbsolutePath() + "/Fotos/FotosCertificadoRegistro" + date2 + "N";
            final String pathFotosN = sdCard.getAbsolutePath() + "/Fotos/FotosCertificadoRegistro" + date2 + "N/";
            final String pathFotosF = sdCard.getAbsolutePath() + "/Fotos/FotosCertificadoRegistro" + date2 + "/";
            ;

            String sDirectorio = pathFotos;
            final File f = new File(sDirectorio);
            Log.i(null, "lista" + pathFotos);
            final String customURL = "https://opinion.cdmx.gob.mx/fotografias/censo_pueblos/";

            Log.i("Foto", "CarlosQ =======> lista de fotos: " + pathFotos);
            Log.i("Foto", "CarlosQ =======> pathFotosN: " + pathFotosN);
            Log.i("Foto", "CarlosQ =======> pathFotosF: " + pathFotosF);
            Log.i("Foto", "CarlosQ =======> URL: " + customURL);

            File F = new File(pathFotos);

            try {

                if (F.exists()) {

                    File[] ficheros = F.listFiles();

                    for (int i = 0; i < ficheros.length; i++) {
                        //Simulamos cierto retraso
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }

                        publishProgress(i / (float) (ficheros.length)); //Actualizamos los valores
                    }


                    String seccion = cachaSeccion();

                    elMaximo = Integer.parseInt(sacaMaximo().toString());

                    String diario = String.valueOf(elMaximo);


                    Nombre nom = new Nombre();
                    String nombreE = nom.nombreEncuesta();

                    GPSTracker gps = new GPSTracker(FotoEvidencia.this);
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    String strLatitud = String.valueOf(latitude);
                    String strLongitud = String.valueOf(longitude);


                    if (latitude == 0.0) {
                        latitude = Double.valueOf(sacaLatitud());

                    }

                    if (longitude == 0.0) {
                        longitude = Double.valueOf(sacaLongitud());

                    }

                    String strLatitud2 = String.valueOf(latitude);
                    String strLongitud2 = String.valueOf(longitude);


                    nombreD = diario + "_" + seccion + "_" + cachaNombre() + "_" + sacaImei() + "_" + date + "_" + strLatitud2 + "_" + strLongitud2;


                    nombreD = diario + "_" + seccion + "_" + cachaNombre() + "_" + sacaImei() + "_" + date;

                    String[] s = new String[ficheros.length];
                    String[] t = new String[ficheros.length];
                    for (int x = 0; x < ficheros.length; x++) {
                        Log.i("Foto", "CarlosQ ===========> lista: " + ficheros[x].getName());
                        s[x] = pathFotos + "/" + ficheros[x].getName();
//							t[x] = ficheros[x].getName();
                        // solo la foto
                        t[x] = nombreD.trim() + ".jpg";

                        //	uploadFotos(s[x],date2);
                        URL u = new URL(customURL + t[x]);
                        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                        huc.setRequestMethod("GET");  //OR  huc.setRequestMethod ("HEAD");
                        huc.connect();
                        huc.getResponseCode();
                        Log.i("Foto", "CarlosQ: =====================> Respuesta del servidor " + huc.getResponseCode());
                        if (huc.getResponseCode() == 200) {
                            moveFile(pathFotosN, t[x], pathFotosF);
                            Log.i("Foto", "CarlosQ: ================>  Foto en el servidor y Movida a otra carpeta ====>" + t[x]);
                        } else if (huc.getResponseCode() == 404) {
                            uploadFotos(s[x], date2);
                            Log.i("Foto", "CarlosQ: =================> Foto Enviada y a�n sin Moverse ====>" + t[x]);
                        }
                    }
                    // first parameter is d files second parameter is zip file name

                } else {
                    Log.i(null, "lista 2: " + "No existe el directorio");
                }
                // first parameter is d files second parameter is zip file name

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.i(null, "error zip: " + "_" + e.getMessage());
            }


            return date2;
        }


        protected void onProgressUpdate(Float... valores) {
            int p = Math.round(100 * valores[0]);

        }


        protected void onPostExecute(String date2) {
            super.onPostExecute(date2);

        }

    }

    private void moveFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();


        } catch (FileNotFoundException fnfe1) {
            Log.i(null, "Archivos  tag" + fnfe1.getMessage());
        } catch (Exception e) {
            Log.i(null, "Archivos  tag" + e.getMessage());
        }

    }


    private String sacaLatitud() {
        Set<String> set = new HashSet<String>();
        String acceso = null;
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select latitud from ubicacion order by id desc limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                acceso = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // db.close();

        return acceso;
    }

    private String sacaLongitud() {
        Set<String> set = new HashSet<String>();
        String acceso = null;
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select longitud from ubicacion order by id desc limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                acceso = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // db.close();

        return acceso;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}



