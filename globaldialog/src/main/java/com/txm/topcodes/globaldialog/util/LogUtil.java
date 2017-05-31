package com.txm.topcodes.globaldialog.util;

import android.util.Log;

/**
 * LogUtil
 *
 * @author way
 *         Manage Log
 */
public class LogUtil {

	// Whether need bug?And you can init in application`s onCreate() function.
	public static boolean isDebug = true;
	private String TAG = "italk";

	public static LogUtil getBmob() {
		LogUtil l = new LogUtil();
		l.TAG = "bmob";
		return l;
	}

	public static LogUtil getEaseMob() {
		LogUtil l = new LogUtil();
		l.TAG = "easeMob";
		return l;
	}

	public static LogUtil getDefined(String tag) {
		LogUtil l = new LogUtil();
		l.TAG = tag;
		return l;
	}


	// These are 4 default funtion below.
	public void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}

	public void e(String msg) {
		if (isDebug)
			Log.e(TAG, msg);
	}

	public void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}
}