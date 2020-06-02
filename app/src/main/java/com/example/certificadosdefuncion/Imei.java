package com.example.certificadosdefuncion;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

public class Imei extends Activity {

	public String getDeviceIMEI() {
		String deviceUniqueIdentifier = null;
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		if (null != tm) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				deviceUniqueIdentifier = tm.getDeviceId();
			}
			deviceUniqueIdentifier = tm.getDeviceId();
	    }
	    if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
	        deviceUniqueIdentifier = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
	    }
	    return deviceUniqueIdentifier;
	}
	
}	 