package com.example.sisekolah.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sisekolah.model.User;
import com.example.sisekolah.start.LoginPage;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "SIsekolah";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_ACCESS = "keyaccess";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return  mInstance;
    }

    //method untuk login user dan menyimpan data user di dalam shared preference
    public void userLogin(String username, String akses){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ACCESS, akses);
        editor.apply();
    }

    //mengecek apakah user sedang login atau tidak
    public boolean isLogin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_ACCESS, null)
        );
    }

    //jika user logout
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginPage.class));
    }
}
