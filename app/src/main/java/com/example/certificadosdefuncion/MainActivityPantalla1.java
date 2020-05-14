package com.example.certificadosdefuncion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.example.certificadosdefuncion.R;
import com.example.certificadosdefuncion.model.DatoContent;
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
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.certificadosdefuncion.Nombre.ID_CERTIFICADO;
import static com.example.certificadosdefuncion.Nombre.TIPO;
import static com.example.certificadosdefuncion.Nombre.USUARIO;
import static com.example.certificadosdefuncion.Nombre.customURL;


public class MainActivityPantalla1 extends Activity {

	private View mProgressView;
	private int idCertificado = 0;

	private static final String LOG_TAG = "Pantalla 1";
	private static final String TAG = "Pantalla 1";
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;

	final Context context = this;

	private ArrayList<CheckBox> mChecks;
	private ArrayList<CheckBox> mSelectedChecks;

	private ArrayList<CheckBox> mChecks2;
	private ArrayList<CheckBox> mSelectedChecks2;

	public MediaRecorder recorder = new MediaRecorder();
	private String audio;
	private Handler handler;
	public String honestidad;

	public StringBuilder builder0;

	private Button btnGuardar;
	private Button btnAbandono;
	private Button btnRechazo;
	private Button btnAbrir;
	private Button btnSalir;
	public Button uploadButton, emailButton;

	double latitude;
	double longitude;

	Random random = new java.util.Random();
	public int rand;
	public EditText editFin;
	public RadioGroup rdPreguntaMedio;
	public RadioGroup rdPreguntaSemana;
	public RadioGroup rdPreguntaFinSemana;

	public RadioGroup rdPreguntaOcupacion, rdPreguntaFocos, rdPreguntaE1a, rdPreguntaE1b, rdPreguntaCoche, rdPreguntaE3,
			rdPreguntaE4, rdPreguntaCuantosCoches, rdPreguntaTrabajo, rdPreguntaE7, rdPregunta4a, rdPregunta4b,
			rdPregunta4c, rdPregunta4d, rdPregunta4e, rdPreguntaCuartos, rdPreguntaCuartosDormir, rdPreguntaCuartos1a,
			rdPreguntaE92, rdPreguntaBanos, rdPreguntaTrabajaron, rdPreguntaInternet, rdPreguntaE101, rdPreguntaE102a, rdPreguntaE103, rdPreguntaRegadera,
			rdPreguntaEstufa, rdPreguntaEdad, rdPreguntaGenero, rdPreguntaTipoVivienda, rdPreguntaTipoPiso,
			rdPreguntaE17;


	public EditText editOtroNoticias, editOtroPeriodico, editOtroRedes;
	public RadioGroup rdPregunta5a, rdPregunta5b, rdPregunta5c, rdPregunta5d, rdPregunta5e, rdPregunta5f,
			rdPregunta5g, rdPregunta5h, rdPregunta5i, rdPregunta5j, rdPregunta5k, rdPregunta5l;
	public RadioGroup rdPregunta79a, rdPregunta79a1, rdPregunta79a2, rdPregunta79a3;
	public RadioGroup rdPregunta5m, rdPregunta5n, rdPregunta5o, rdPregunta5p, rdPregunta5q, rdPregunta5r, rdPregunta5s,
			rdPregunta5t;
	public RadioGroup rdPreguntaJefe, rdPreguntaAporta, rdPreguntaAbandono, rdPregunta6e;

	public RadioGroup rdPreguntaHijos, rdPregunta81, rdPregunta86a, rdPregunta86b, rdPregunta86c, rdPregunta86d,
			rdPregunta86e, rdPregunta86f, rdPregunta86g;

	public EditText editPregunta7c, editPregunta5e, editPregunta5h, editPregunta5k,
			editPregunta5l, editPregunta29a, editPregunta35;
	public RadioGroup rdPregunta13, rdPregunta14;
	public RadioGroup rdPregunta14a, rdPregunta16a, rdPregunta18a, rdPregunta20a;
	public RadioGroup rdPregunta14b, rdPregunta16b, rdPregunta18b, rdPregunta20b;
	public RadioGroup rdPregunta23, rdPregunta23a, rdPregunta24;
	public RadioGroup rdPregunta21, rdPregunta22;
	public RadioGroup rdPregunta1a, rdPregunta1a0, rdPregunta1a2, rdPregunta1b, rdPregunta1c, rdPregunta1d,
			rdPregunta1e, rdPregunta1f, rdPregunta1g, rdPregunta1h, rdPregunta1i;
	public RadioGroup rdPregunta2c, rdPregunta2d, rdPregunta2e, rdPregunta2f, rdPregunta2g, rdPregunta2h, rdPregunta2i;

	public RadioButton radio11_1, radio11_2, radio11_3, radio11_4, radio11_5, radio11_6, radio11_7, radio11_8,
			radio11_9, radio11_10, radio11_11, radio11_12, radio11_0, radio12_0_1, radio12_0_2;
	public RadioButton radio13_1, radio13_2, radio13_3, radio13_4, radio13_5, radio13_6, radio13_7, radio13_8,
			radio13_9, radio13_10, radio13_11, radio13_12, radio13_0;
	public RadioButton radio_abandono1, radio_abandono2, radio_abandono3;

	public EditText editPregunta13, editPregunta16,
			editPregunta21, editPregunta23, editPregunta24;
	public RadioGroup rdPregunta26a, rdPregunta280, rdPregunta28a, rdPregunta28b, rdPregunta29a,
			rdPregunta29b;

	public RadioGroup rdPregunta34, rdPregunta34a, rdPregunta34b, rdPregunta34c, rdPregunta35, rdPregunta36,
			rdPregunta36a, rdPregunta37, rdPregunta38;
	public RadioGroup rdPregunta51, rdPregunta55, rdPregunta56;

	public TextView pregunta3, pregunta6, pregunta6a, pregunta26, pregunta29b, pregunta51, pregunta34, pregunta35,
			pregunta36, pregunta36a;
	public TextView pregunta7, pregunta8, pregunta9, pregunta10, pregunta11, pregunta13, pregunta15, pregunta17,
			pregunta19, pregunta21, pregunta23, pregunta25;
	public TextView pregunta12, pregunta14, pregunta16, pregunta18, pregunta20;
	public TextView pregunta12a, pregunta14a, pregunta16a, pregunta18a, pregunta20a;
	public TextView pregunta12b, pregunta14b, pregunta16b, pregunta18b, pregunta20b;
	public TextView pregunta27, pregunta29;
	public TextView preguntaNom1, preguntaNom2, preguntaNom3, preguntaNom4, preguntaNom5;
	public TextView radio351, radio352, radio353, radio354, radio355;
	public TextView radio38, radio362, radio310, radio311, radio312;


	private static final int READ_BLOCK_SIZE = 100000;

	Nombre nom = new Nombre();
	String nombreEncuesta = nom.nombreEncuesta();

	UsuariosSQLiteHelper usdbh;
	UsuariosSQLiteHelper Udb;
	List<String> list;
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter2;
	private SQLiteDatabase db;

	UsuariosSQLiteHelper2 usdbh2;
	private SQLiteDatabase db2;

	private Spinner spinner_juzgado;

	String elDelegado;

	Timer timer;


	public String edad_1;
	public String edad_2;
	public String edad_3;
	public String edad_4;
	public String edad_5;

	public String op1 = "SIN DATOS";
	public String op2 = "SIN DATOS";
	public String op3 = "SIN DATOS";
	public String op4 = "SIN DATOS";
	public String op5 = "SIN DATOS";
	public String op6 = "SIN DATOS";
	public String op7 = "SIN DATOS";
	public String op8 = "SIN DATOS";

	public RadioGroup rdPregunta1;
	public RadioGroup rdPregunta2;
	public RadioGroup rdPregunta3;
	public RadioGroup rdPregunta4;
	public RadioGroup rdPregunta5;
	public RadioGroup rdPregunta6;
	public RadioGroup rdPregunta7;
	public RadioGroup rdPregunta8;

	public EditText editPregunta1;
	public EditText editPregunta2;
	public EditText editPregunta3;
	public EditText editPregunta4;
	public EditText editPregunta5;
	public EditText editPregunta6;
	public EditText editPregunta8;
	public EditText editPregunta9a;
	public EditText editPregunta9m;
	public EditText editPregunta9d;
	public EditText editFechaDeceso;


	public EditText editPreguntaOcupacion;


	public String captura1;
	public String captura2;
	public String captura3;
	public String captura4;
	public String captura5;
	public String captura6;
	public String captura7;
	public String captura8;
	public String captura9;
	public String captura9a;
	public String captura9m;
	public String captura9d;

	public Resources res;

	UsuariosSQLiteHelper3 usdbh3;
	private SQLiteDatabase db3;

	LinearLayout lay1;
	LinearLayout lay2;
	LinearLayout lay3;
	LinearLayout lay4;
	LinearLayout lay5;
	LinearLayout lay6;
	LinearLayout lay7;
	LinearLayout lay8;
	LinearLayout lay9;

	public ImageButton imageButtonVale;


	public String maximo = "";
	int elMaximo;
	String tipoEncuesta;

	public String pasoUsuario;

	public String Secc;

	public EditText editUsuario;

	public String str;
	public String variablePrueba;
	public String encuestador;
	public String tablet;
	public String hora;

	public String quien;

	String upLoadServerUri = null;
	ProgressDialog dialog = null;
	final String path = "/mnt/sdcard/Mis_archivos/";

	int serverResponseCode = 0;

	public String tiempo;

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

	public static String getHostName(String defValue) {
		try {
			Method getString = Build.class.getDeclaredMethod("getString", String.class);
			getString.setAccessible(true);
			return getString.invoke(null, "net.hostname").toString();
		} catch (Exception ex) {
			return defValue;
		}
	}

	static String ID = getHostName(null);
	static String prefix = ID;

	public String cachaNombre() {
		Bundle datos = this.getIntent().getExtras();
		String Nombre = datos.getString("Nombre");
		return Nombre;
	}

	public String cachaSeccion() {
		Bundle datos = this.getIntent().getExtras();
		String Seccion = datos.getString("Seccion");
		return Seccion;
	}

	public String cachaDelegacion() {
		Bundle datos = this.getIntent().getExtras();
		String delegacion = datos.getString("delegacion");
		return delegacion;
	}

	public String cachaEquipo() {
		Bundle datos = this.getIntent().getExtras();
		String equipo = datos.getString("equipo");
		return equipo;
	}

	public long t1() {
		Bundle datos = this.getIntent().getExtras();
		long t1 = datos.getLong("t1");
		return t1;
	}

	public void sacaChip() {
		String deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
		tablet = deviceId;
	}

	public String sacaImei() {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
			System.out.println("Mi Numero: " + szImei);

		}
		String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
		System.out.println("Mi Numero: " + szImei);

		return szImei;
	}

	public String hora() {

		if (formattedDate5.matches("")) {
			formattedDate5 = df5.format(c.getTime());
		}
		return formattedDate5;
	}

	public void dialogo() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Desea continuar Encuestando..?").setTitle("IMPORTANTE").setCancelable(false)
				.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						detenerGrabacion();

						Intent i = new Intent(MainActivityPantalla1.this, Entrada.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						System.exit(0); // metodo que se debe implementar
					}
				}).setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						detenerGrabacion();

						Intent i = new Intent(MainActivityPantalla1.this, MainActivity.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						i.putExtra("Nombre", cachaNombre());
						i.putExtra("cuantos", 1);
						startActivity(i);
						System.exit(0); // metodo que se debe implementar
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
	

	
	public void dialogoFoto() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Encuestador, tomar foto del Documento(s)").setTitle("Foto de Fachada").setCancelable(false)
				.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						detenerGrabacion();

						//elMaximo = Integer.parseInt(sacaMaximo().toString());
						elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
						
						Log.i(LOG_TAG, ">>>>>>>>>>  Consecutivo"+ elMaximo);
						elMaximo = Integer.parseInt(sacaMaximo().toString());
						Intent i = new Intent(MainActivityPantalla1.this, FotoEvidencia.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						i.putExtra("Nombre", cachaNombre());
						i.putExtra("consecutivo_diario", elMaximo);
						i.putExtra("cuantos", "1");
						i.putExtra("folio", editPregunta3.getText().toString());
						i.putExtra(USUARIO,usuario);
						i.putExtra(ID_CERTIFICADO,idCertificado);
						i.putExtra(TIPO,"certificado");

						startActivity(i);
						//System.exit(0); // metodo que se debe implementar
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void dialogoParoAudio() {
		timer.cancel();
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		MainActivityPantalla1.this.runOnUiThread(new Runnable() {
			public void run() {
				builder.setMessage("Se detendra la grabacion y \n se reiniciara la encuesta..?")
						.setTitle("AVISO...!!!").setCancelable(false)
						.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								detenerGrabacion();

								Intent i = new Intent(MainActivityPantalla1.this, Entrada.class);
								i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(i);
								System.exit(0); // metodo que se debe
												// implementar
							}
						});
				AlertDialog alert = builder.create();
				alert.show();

			}
		});

	}

	public void dialogoCierraEncuesta() {
		timer.cancel();

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		MainActivityPantalla1.this.runOnUiThread(new Runnable() {
			public void run() {
				builder.setMessage("Excediste el tiempo maximo para realizar la encuesta \n"
						+ "--- Se detendra la grabacion y se reiniciara la Aplicacion..!!!").setTitle("AVISO...!!!")
						.setCancelable(false).setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								detenerGrabacion();

								Intent i = new Intent(MainActivityPantalla1.this, Entrada.class);
								i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(i);
								System.exit(0); // metodo que se debe
												// implementar
							}
						});

				AlertDialog alert = builder.create();

				alert.show();
			}
		});

	}

	public void dialogoAbandono() {
		timer.cancel();

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		MainActivityPantalla1.this.runOnUiThread(new Runnable() {
			public void run() {
				builder.setMessage("Deseas abandonar la encuesta?").setTitle("AVISO...!!!").setCancelable(false)
						.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								detenerGrabacion();
							}
						}).setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});

				AlertDialog alert = builder.create();

				alert.show();
			}
		});

	}

	// EVENTO AL PULSAR EL BOTON ATRAS

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// Esto es lo que hace mi bot�n al pulsar ir a atr�s
			Toast.makeText(getApplicationContext(), "No puedo ir hacia atras!!\nEstoy grabando...", Toast.LENGTH_SHORT)
					.show();

			// dialogoAbandono();

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public String nombreArchivo() {
		String date = formattedDate3.toString();
		String var2 = ".txt";
		String var3 = date + var2;

		final String nombre = date + "-" + tablet + "-" + nombreEncuesta + var2;
		return nombre;
	}

	public String nombreAudio() {

		elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
		String date = formattedDate3.toString();
		String var2 = ".mp3";

		int consecutivo = Integer.parseInt(sacaConsecutivo().toString()) + 1;
		String elConsecutivo = String.valueOf(consecutivo);
		int Cons = elConsecutivo.length();

		if (Cons == 1) {
			elConsecutivo = "00" + elConsecutivo;
		} else if (Cons == 2) {
			elConsecutivo = "0" + elConsecutivo;
		} else {
			elConsecutivo = elConsecutivo;
		}

		String usuario;

		int tamanoUsuario = cachaNombre().length();

		if (tamanoUsuario == 1) {
			usuario = "00" + cachaNombre();
		} else if (tamanoUsuario == 2) {
			usuario = "0" + cachaNombre();
		} else {
			usuario = cachaNombre();
		}

		// nombreEncuesta_fecha_chip_usuario_consecutivo
		final String nombreAudio = nombreEncuesta + "_" + date + "_" + sacaImei() + "_" + cachaNombre() + "_"
				+ elConsecutivo + ".mp3";
		// final String nombreAudio =
		// nombreEncuesta+"_"+date+"_"+prefix+"_"+elConsecutivo+".mp3";
		return nombreAudio;
	}

	public String elTiempo() {
		// Para la diferenci entre tiempos
		Calendar t2 = Calendar.getInstance();
		long milis2 = t2.getTimeInMillis();
		long diff = milis2 - t1();

		long diffSegundos = diff / 1000;

		long diffMinutos = diffSegundos / 60;

		long residuo = diffSegundos % 60;

		System.out.println(diffSegundos);
		System.out.println(diffMinutos);
		System.out.println(residuo);

		tiempo = diffMinutos + ":" + residuo;

		return tiempo;

	}

	private Integer[] mLinearLayoutIds = { 
//			R.layout.activity_pantalla1, 
//			R.layout.activity_pantalla2,
//			 R.layout.activity_pantalla3, 
//			 R.layout.activity_pantalla4,
//			 R.layout.activity_pantalla5,
//			 R.layout.activity_pantalla6, 
//			 R.layout.activity_pantalla7,
//			 R.layout.activity_pantalla8,
//			 R.layout.activity_pantalla9, 
//			 R.layout.activity_pantalla10,
			//// R.layout.activity_pantalla11,
			//// R.layout.activity_pantalla12,
			//// R.layout.activity_pantalla13,
			//// R.layout.activity_pantalla14,
			//// R.layout.activity_pantalla15,
			//// R.layout.activity_pantalla16,
			//// R.layout.activity_pantalla17,
			//// R.layout.activity_pantalla18,
			//// R.layout.activity_pantalla19,
	};

	private Usuario usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla1); // COMENTAR ESTA CUANDO

		Intent startingIntent = getIntent();
		if (startingIntent == null) {
			finish();
			return;
		}

		if (savedInstanceState != null) {
			usuario = (Usuario) savedInstanceState.getSerializable(USUARIO);
		} else {
			usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
		}

		// Crea Log cuando falla la app
		Thread.setDefaultUncaughtExceptionHandler(new Crash(this));

		cachaNombre(); // llamado al método para obtener el numero del
						// encuestador

		try {

			handler = new Handler();

			new Thread(new Runnable() {
				@Override
				public void run() {
					handler.post(new Runnable() {
						@Override
						public void run() {
							grabar();
						}

					});

				}
			}).start();

		} catch (Exception e) {

		}

		mProgressView = findViewById(R.id.login_progressMain);

		///////////// EL TIMER PARA PARAR LA ENCUESTA /////////////////

//		timer = new Timer();
//		timer.schedule(new CierraEncuesta(), 1800000); // 8 Minutos 480000

		////////////////////////

//		radio_abandono1 =(RadioButton)findViewById(R.id.radio_abandono1);
//		radio_abandono2 =(RadioButton)findViewById(R.id.radio_abandono2);
//		radio_abandono3 =(RadioButton)findViewById(R.id.radio_abandono3);
		

		rdPregunta1 = (RadioGroup)findViewById(R.id.rdPregunta1);
		rdPregunta2 = (RadioGroup)findViewById(R.id.rdPregunta2);
		rdPregunta3 = (RadioGroup)findViewById(R.id.rdPregunta3);
		rdPregunta4 = (RadioGroup)findViewById(R.id.rdPregunta4);
		rdPregunta5 = (RadioGroup)findViewById(R.id.rdPregunta5);
		rdPregunta6 = (RadioGroup)findViewById(R.id.rdPregunta6);
		rdPregunta7 = (RadioGroup)findViewById(R.id.rdPregunta7);
		rdPregunta8 = (RadioGroup)findViewById(R.id.rdPregunta8);

		editPregunta3= (EditText)findViewById(R.id.editPregunta3);
		editPregunta4= (EditText)findViewById(R.id.editPregunta4);
		editPregunta5= (EditText)findViewById(R.id.editPregunta5);
		editPregunta6= (EditText)findViewById(R.id.editPregunta6);
		editPregunta8= (EditText)findViewById(R.id.editPregunta8);
		editFechaDeceso= (EditText)findViewById(R.id.editFechaDeceso);
		editFechaDeceso.setEnabled(false);

		imageButtonVale = (ImageButton) findViewById(R.id.imageButtonVale);
		
//		editPregunta9a= (EditText)findViewById(R.id.editPregunta9a);
//		editPregunta9m= (EditText)findViewById(R.id.editPregunta9m);
//		editPregunta9d= (EditText)findViewById(R.id.editPregunta9d);

		spinner_juzgado= (Spinner) findViewById(R.id.spinner_juzgado);

		res = getResources();
		
		captura1 =res.getString(R.string.PREGUNTA1);
		captura2 =res.getString(R.string.PREGUNTA2);
		captura3 =res.getString(R.string.PREGUNTA3);
		captura4 =res.getString(R.string.PREGUNTA4);
		captura5 =res.getString(R.string.PREGUNTA5);
		captura6 =res.getString(R.string.PREGUNTA6);
		captura7 =res.getString(R.string.PREGUNTA7);
		captura8 =res.getString(R.string.PREGUNTA8);
		captura9a =res.getString(R.string.PREGUNTA9a);
		captura9m =res.getString(R.string.PREGUNTA9m);
		captura9d =res.getString(R.string.PREGUNTA9d);


		
		lay1 = (LinearLayout) findViewById(R.id.lay1);
		lay2 = (LinearLayout) findViewById(R.id.lay2);
		lay3 = (LinearLayout) findViewById(R.id.lay3);
		lay4 = (LinearLayout) findViewById(R.id.lay4);
		lay5 = (LinearLayout) findViewById(R.id.lay5);
		lay6 = (LinearLayout) findViewById(R.id.lay6);
		lay7 = (LinearLayout) findViewById(R.id.lay7);
		lay8 = (LinearLayout) findViewById(R.id.lay8);
		lay9 = (LinearLayout) findViewById(R.id.lay9);
//		preg_clas_2.xml



		btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnSalir = (Button) findViewById(R.id.btnSalir);
		btnSalir.setEnabled(false);
		btnSalir.setVisibility(View.GONE);

//				ocultaEstadisticas();
		
		CargaSpinnerJuzgados();
		



		rdPregunta2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radio1) {
					op2 = "Acta de Defuncion";

				}
				else if (checkedId == R.id.radio2) {
					op2 = "Certificado de Defuncion";

				}
			}
		});

		rdPregunta7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
				new DatePickerDialog(MainActivityPantalla1.this,dateFecha, myCalendarioF
						.get(Calendar.YEAR), myCalendarioF.get(Calendar.MONTH),
						myCalendarioF.get(Calendar.DAY_OF_MONTH)).show();
			}
		});


		
	}////// FIN ONCREATE/////////////////////////////
	
	public void mensaje(String mensaje){
		
		Toast.makeText(getBaseContext(),"Aviso: " +  mensaje,Toast.LENGTH_LONG).show();
		
	}

	@Override
	protected void onPause() {
		super.onPause();

	}


	class CierraEncuesta extends TimerTask {

		@Override
		public void run() {

			dialogoCierraEncuesta();

		}

	}

	public void drawResults() {
		for (CheckBox c : mChecks) {
			c.setChecked(mSelectedChecks.contains(c));
		}
	}

	public void drawResults2() {
		for (CheckBox d : mChecks2) {
			d.setChecked(mSelectedChecks2.contains(d));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void valores() {

		
		String str = "";

		String seg = formattedDate5.substring(7);
		System.out.println("El segundo: " + seg);
		System.out.println("El IMEI" + sacaImei());

		String mes = formattedDate6.toString();
		System.out.println("El mes" + mes);

		String dia = formattedDate7.toString();
		System.out.println("El dia" + dia);

		sacaChip();

		cachaNombre();



		elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
		
		String strText12_1;
		String strText12a_1;

		String str1 = spinner_juzgado.getSelectedItem().toString().toUpperCase();
		String str2 = op2.toUpperCase();
		String str3 = editPregunta3.getText().toString().toUpperCase();
		String str4 = editPregunta4.getText().toString().toUpperCase();
		String str5 = editPregunta5.getText().toString().toUpperCase();
		String str6 = editPregunta6.getText().toString().toUpperCase();
		String str7 = op7.toUpperCase();
		String str8 = editPregunta8.getText().toString().toUpperCase();
		String str9 = editFechaDeceso.getText().toString();

		String strFinal = "\n";

		// Clase que permite grabar texto en un archivo
		FileOutputStream fout = null;
		try {
			// INSERTA EN LA BASE DE DATOS //

			final String F = "File dbfile";

			String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
					+ sacaImei() + "";

			// Abrimos la base de datos 'DBUsuarios' en modo escritura
			usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

			db = usdbh.getWritableDatabase();

			// NORMAL
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

			String strLatitud2 = String.valueOf(latitude);
			String strLongitud2 = String.valueOf(longitude);

			long consecutivoObtenido = 0;
			ContentValues values = new ContentValues();

			if (db != null) {
				values.put("consecutivo_diario", elMaximo);
				values.put("usuario", cachaNombre().toUpperCase());
				values.put("imei", sacaImei() == null ? "0" : sacaImei() );
				values.put("fecha", formattedDate1);
				values.put("hora", formattedDate5);
				values.put("latitud", strLatitud);
				values.put("longitud", strLongitud);

				values.put("nombre_programa", nombreE.toUpperCase());
				values.put("juzgado",str1);
				values.put("certificado_acta",str2);
				values.put("folio",str3);
				values.put("nombres",str6);
				values.put("paterno",str4);
				values.put("materno",str5);
				values.put("genero",str7);
				values.put("curp",str8);
				values.put("fecha_deceso",str9);
				db.insert("registros", null, values);
			}

			values.put("consecutivo", consecutivoObtenido);
			guardaEncuestaWS(values);

			//db.close();

			System.out.println("Latitud  " + strLatitud);
			System.out.println("Longitud  " + strLongitud);
			
			System.out.println("juzgado  " +   str1);
			System.out.println("certificado_acta  " +   str2);
			System.out.println("folio  " +   str3);
			System.out.println("paterno  " +   str4);
			System.out.println("materno  " +   str5);
			System.out.println("nombres  " +   str6);
			System.out.println("genero  " +   str7);
			System.out.println("curp  " +   str8);
			System.out.println("fecha_deceso  " +   str9);

			// FIN INSERTA BASE DE DATOS //

		} catch (Exception e) {
			String stackTrace = Log.getStackTraceString(e);
			Log.i(TAG,"================>> Inserta registros"+ stackTrace);

		}

	}

	private void guardaEncuestaWS(ContentValues values){

		showProgress(true);

		String consecutivo = "";
		String usuarios = "";
		String imei = "";
		String fecha = "";
		String hora = "";
		String latitud = "";
		String longitud = "";
		String nombre_programa = "";
		String juzgado = "";
		String certificado_acta = "";
		String folio = "";
		String nombre = "";
		String paterno = "";
		String materno = "";
		String genero = "";
		String curp = "";
		String fecha_deceso = "";


		//RECORRE CONTENTVALUES
		DatoContent datoContent = new DatoContent();
		List<DatoContent> listaContenido = new ArrayList();
		Set<Map.Entry<String, Object>> s=values.valueSet();
		Iterator itr = s.iterator();
		while(itr.hasNext()) {
			Map.Entry me = (Map.Entry)itr.next();
			String key = me.getKey().toString();
			Object value =  me.getValue();

			datoContent = new DatoContent();
			datoContent.setKey(key);
			datoContent.setValue(String.valueOf(value));
			listaContenido.add(datoContent);

			if(key.equals("consecutivo_diario"))
				consecutivo = String.valueOf(value);
			if(key.equals("usuario"))
				usuarios = String.valueOf(value);
			if(key.equals("imei"))
				imei = String.valueOf(value);
			if(key.equals("fecha"))
				fecha = String.valueOf(value);
			if(key.equals("hora"))
				hora = String.valueOf(value);
			if(key.equals("latitud"))
				latitud = String.valueOf(value);
			if(key.equals("longitud"))
				longitud = String.valueOf(value);
			if(key.equals("nombre_programa"))
				nombre_programa = String.valueOf(value);
			if(key.equals("juzgado"))
				juzgado = String.valueOf(value);
			if(key.equals("certificado_acta"))
				certificado_acta = String.valueOf(value);
			if(key.equals("folio"))
				folio = String.valueOf(value);
			if(key.equals("nombres"))
				nombre = String.valueOf(value);
			if(key.equals("paterno"))
				paterno = String.valueOf(value);
			if(key.equals("materno"))
				materno = String.valueOf(value);
			if(key.equals("genero"))
				genero = String.valueOf(value);
			if(key.equals("curp"))
				curp = String.valueOf(value);
			if(key.equals("fecha_deceso"))
				fecha_deceso = String.valueOf(value);

		}

		Gson gson  = new Gson();
		Type collectionType = new TypeToken<List<DatoContent>>() { }.getType();
		String json = gson.toJson(listaContenido,collectionType);

		RequestParams params = new RequestParams();
		params.put("api", "guarda_certificado");
		params.put("id_usuario", usuario.getId());

		params.put("consecutivo", consecutivo);
		params.put("usuario", usuarios);
		params.put("imei", imei);
		params.put("fecha", fecha);
		params.put("hora", hora);
		params.put("latitud", latitud);
		params.put("longitud", longitud);
		params.put("nombre_programa", nombre_programa);
		params.put("juzgado", juzgado);
		params.put("certificado_acta", certificado_acta);
		params.put("folio", folio);
		params.put("nombre", nombre);
		params.put("paterno", paterno);
		params.put("materno", materno);
		params.put("genero", genero);
		params.put("curp", curp);
		params.put("fecha_deceso", fecha_deceso);

		AsyncHttpClient client = new AsyncHttpClient();
		client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
		//client.addHeader("Authorization", "Bearer " + usuario.getToken());
		client.setTimeout(60000);

		RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				showProgress(false);
				try {

					Log.d(TAG, "pimc -----------> Respuesta OK ");
					Log.d(TAG, "pimc -----------> " + new String(responseBody));
					String json = new String(responseBody);

					if (json != null && !json.isEmpty()) {

						Gson gson = new Gson();
						JSONObject jsonObject = new JSONObject(json);

						String login = jsonObject.getJSONObject("response").get("code").toString();
						if (Integer.valueOf(login) == 1) {

							//JSONObject idCertificado = jsonObject.getJSONObject("data").getJSONObject("certificado_id");
							String idCertificados = jsonObject.getJSONObject("data").getString("certificado_id");
							idCertificado = Integer.valueOf(idCertificados);

							btnGuardar.setEnabled(false);
							dialogoFoto();

							/*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.putExtra(USUARIO,usuario);
							startActivity(intent);
							finish();*/


						} else {
							Toast.makeText(MainActivityPantalla1.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
						}
					}

				} catch (Exception e) {
					showProgress(false);
					Toast.makeText(MainActivityPantalla1.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				showProgress(false);
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

				Toast.makeText(MainActivityPantalla1.this, "Error de conexion, intente de nuevo", Toast.LENGTH_SHORT).show();

			}
		});


	}
	

	public void guardar(View v) {
		System.out.println(cachaDelegacion());

//		timer.cancel();
		op2 = "Certificado de Defuncion";

		String str = "";

		int tipo = 1;

		switch (tipo) {
		case 1:


			  if (lay1.getVisibility() == View.VISIBLE && spinner_juzgado.getSelectedItem().toString().equals("Selecciona")){Toast.makeText(this,"CAPTURA:  " +  captura1,Toast.LENGTH_LONG).show();}
			  else if (lay2.getVisibility() == View.VISIBLE && op2.matches("SIN DATOS")){Toast.makeText(this,"CAPTURA:  " +  captura2,Toast.LENGTH_LONG).show();}
			  else if (lay3.getVisibility() == View.VISIBLE && editPregunta3.getText().toString().trim().length()==0){Toast.makeText(this,"CAPTURA:  " +  captura3,Toast.LENGTH_LONG).show();}
			  else if (lay4.getVisibility() == View.VISIBLE && editPregunta4.getText().toString().trim().length()==0){Toast.makeText(this,"CAPTURA:  " +  captura4,Toast.LENGTH_LONG).show();}
			  else if (lay5.getVisibility() == View.VISIBLE && editPregunta5.getText().toString().trim().length()==0){Toast.makeText(this,"CAPTURA:  " +  captura5,Toast.LENGTH_LONG).show();}
			  else if (lay6.getVisibility() == View.VISIBLE && editPregunta6.getText().toString().trim().length()==0){Toast.makeText(this,"CAPTURA:  " +  captura6,Toast.LENGTH_LONG).show();}
			  else if (lay7.getVisibility() == View.VISIBLE && op7.matches("SIN DATOS")){Toast.makeText(this,"CAPTURA:  " +  captura7,Toast.LENGTH_LONG).show();}
			  else if (lay8.getVisibility() == View.VISIBLE && editPregunta8.getText().toString().trim().length()==0){Toast.makeText(this,"CAPTURA:  " +  captura8,Toast.LENGTH_LONG).show();}
			  else if (lay9.getVisibility() == View.VISIBLE
					  	&& editFechaDeceso.getText().toString().trim().length()==0
			  			){Toast.makeText(this,"CAPTURA:  " +  captura9,Toast.LENGTH_LONG).show();}

			else {

				valores();
				/*btnGuardar.setEnabled(false);
				dialogoFoto();*/
				
				

			}// Finaliza else de validaci�n

			break;

		}

	}


	public void Salir(View view) {
		finish();
	}

	private String sacaMaximo() {

		Set<String> set = new HashSet<String>();

		final String F = "File dbfile";

		// Abrimos la base de datos 'DBUsuarios' en modo escritura
		String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
				+ sacaImei() + "";
		usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

		db = usdbh.getReadableDatabase();

		String selectQuery = "SELECT count(*) FROM registros where fecha='" + formattedDate1 + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				maximo = cursor.getString(0);

			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return maximo;
	}

	private String sacaConsecutivo() {

		String consecutivo = null;

		Set<String> set = new HashSet<String>();

		final String F = "File dbfile";

		// Abrimos la base de datos 'DBUsuarios' en modo escritura

		String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
				+ sacaImei() + "";
		usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

		db = usdbh.getReadableDatabase();

		String selectQuery = "SELECT count(*) FROM registros order by id desc limit 1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				consecutivo = cursor.getString(0);

			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return consecutivo;
	}


	public void CargaSpinnerJuzgados() {
		String var = "Selecciona";
		if (var == null) {
			var = "";
		}
		final String[] datos = new String[] { "" + var + "",
				"JUZGADO GL",
				"JUZGADO T",
				"JUZGADO X",
				"JUZGADO 13",
				"JUZGADO 14",
				"JUZGADO 16",
				"JUZGADO 18",
				"JUZGADO 19",
				"JUZGADO 32",
				"JUZGADO 51"
		};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_juzgado.setAdapter(adaptador);
		spinner_juzgado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	///////////// FIN SPINNER /////////////

	private String sacaDelegacion(String seccion) {
		Set<String> set = new HashSet<String>();
		final String F = "File dbfile";
		String Dele = "";
		// Abrimos la base de datos 'DBUsuarios' en modo escritura
		usdbh2 = new UsuariosSQLiteHelper2(this, "F", null, 1);
		db2 = usdbh2.getReadableDatabase();
		String selectQuery = "SELECT delegacion FROM datos where seccion='" + seccion + "'";
		Cursor cursor = db2.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Dele = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db2.close();
		return Dele;
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

	public void grabar() {

		// sacaMaximo();
		String pathAudio = "/mnt/sdcard/Audio1" + formattedDate3 + "";

		Nombre nom = new Nombre();
		String nombreEncuesta = nom.nombreEncuesta();

		File sdCard = null, directory, file = null;
		if (Environment.getExternalStorageState().equals("mounted")) {
			// Obtenemos el directorio de la memoria externa
			sdCard = Environment.getExternalStorageDirectory();
		}
		directory = new File(sdCard.getAbsolutePath() + "/" + nombreEncuesta + "-Audio" + formattedDate3 + "");
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		recorder.setOutputFile("/mnt/sdcard/" + nombreEncuesta + "-Audio" + formattedDate3 + "/" + nombreAudio() + "");
		try {
			recorder.prepare();
		} catch (IOException e) {
			Log.i("", String.valueOf("Fallo en grabaci�n: " + e.getMessage()));
		}
		recorder.start();
	}

	public void detenerGrabacion() {
		Thread thread = new Thread() {
			public void run() {
				if (recorder != null)
					recorder.stop();
				recorder.reset(); // You can reuse the object by going back to
				// setAudioSource() step
				recorder.release();
			}
		};
		thread.start();
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
