package app.com.zolospace.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import app.com.zolospace.data.remote.dto.LoginData;

/**
 * Created by Lenovo on 6/6/2017.
 */

public class ZoloPreference {


    public static String USERID = "user_id";
    public static String USERFORMID = "user_form_id";
    public static String USERNAME = "user_name";
    public static String USEREMAIL = "user_email";
    public static String USERENUMMBER = "user_number";
    public static String USEREPASSWORD = "old_password";
    public static String USEREReferral = "referral";
    public static String LOGINDATA = "login_data";
    private static String FILE_NAME = "ZoloPreference";
    private static int MODE_PRIVATE = 0;
    private static ZoloPreference preference;
    private SharedPreferences sharedPreferences;
    private String[] removeKey = {USERID, LOGINDATA, USEREMAIL, USERNAME, USERENUMMBER, USEREPASSWORD, USEREReferral};

    private ZoloPreference() {
    }

    public static ZoloPreference init(Context context) {
        if (preference == null) {
            preference = new ZoloPreference();
            preference.sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        }

        return preference;
    }

    public static ZoloPreference getInstance() {
        if (preference == null) {
            L.e(ZoloPreference.class.getName(), "Preference not initialize yet. Call init() to initialize it.");
        }
        return preference;
    }

    public void saveLoginData(LoginData registerStep1) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(registerStep1);
        editor.putString(LOGINDATA, json);
        editor.commit();

    }

    public LoginData getLoginData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LOGINDATA, null);
        Type type = new TypeToken<LoginData>() {
        }.getType();
        LoginData arrayList = gson.fromJson(json, type);
        return arrayList;
    }

    public void saveStringValue(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void saveIntValue(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntValue(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean hasValueForKey(String key) {

        String value = getStringValue(key);
        return !TextUtils.isEmpty(value);
    }


    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void clearPreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : removeKey) {
            editor.remove(key);
        }
        editor.commit();
    }
}

