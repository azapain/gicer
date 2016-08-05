package com.fap.gic.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Darith on 8/3/2016.
 */
public class GicApplication extends Application {
    private Socket mSocket;
    public static final String TAG = GicApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static GicApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        try {
            mSocket = IO.socket(CONSTANTS.SOCKET_SERVER_URL);
            Log.i("Socket Link", CONSTANTS.SOCKET_SERVER_URL);
        } catch (URISyntaxException e) {
            Log.i("Socket Link", "Error");
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

    public static synchronized GicApplication getInstance() {
        if (mInstance == null) {
            mInstance = new GicApplication();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void saveSharedLogin(int settingValue) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("LOGIN_ID", settingValue);
        editor.apply();
    }

    public String readSharedSetting(String defaultValue) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        return sharedPref.getString(Profile.USER_ID, defaultValue);
    }

    public int readShareLogin(int defaultValue) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        return sharedPref.getInt(Profile.LOGIN_ID, defaultValue);
    }

    public String readShareName(String defaultValue) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        return sharedPref.getString(Profile.NAME, defaultValue);
    }


    public String readSharedPhone(String defaultValue) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        return sharedPref.getString(Profile.PHONE, defaultValue);
    }

    public void removeSharedProfile() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().commit();
    }


    public void saveShareLoginProfile(Profile profile) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(Profile.USER_ID, profile.getUserId());
        editor.putString(Profile.NAME, profile.getName());
        editor.putString(Profile.PHONE, profile.getPhone());
        editor.putString(Profile.AVATAR, profile.getAvatar());
        editor.putInt(Profile.LOGIN_ID, profile.getLoginId());
        editor.apply();

    }
}
