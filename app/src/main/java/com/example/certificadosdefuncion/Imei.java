package com.example.certificadosdefuncion;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Imei extends Activity  {

	public String getDeviceIMEI() {
	    String deviceUniqueIdentifier = null;
	    TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
	    if (null != tm) {
	        deviceUniqueIdentifier = tm.getDeviceId();
	    }
	    if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
	        deviceUniqueIdentifier = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
	    }
	    return deviceUniqueIdentifier;
	}
	
}	 