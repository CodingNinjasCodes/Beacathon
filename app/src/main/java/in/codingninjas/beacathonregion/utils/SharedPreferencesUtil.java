package in.codingninjas.beacathonregion.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import in.codingninjas.beacathonregion.MyApp;

/**
 * Created by Rohan on 08/02/16.
 *
 */
public class SharedPreferencesUtil {

    public static final String SHARED_PREFERENCES_NAME = "BEACATHON";

    public static final String USER_ACCESS_TOKEN = "user_access_token";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_NAME = "user_name";
    public static final String USER_SERVER_ID = "user_id";
    public static final String USER_PROFILE_PIC_URL = "profile_pic_url";


    private static SharedPreferences sharedPreferences;

    static {
        sharedPreferences = MyApp.getInstance().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void removeKey(String key) {
        synchronized (sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    public static void storeStringValue(String key, String value) {
        synchronized (sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static String retrieveStringValue(String key, String defValue) {
        synchronized (sharedPreferences) {
            return sharedPreferences.getString(key, defValue);
        }
    }

    public static void storeIntValue(String key, int value) {
        synchronized (sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public static int retrieveIntValue(String key, int defValue) {
        synchronized (sharedPreferences) {
            return sharedPreferences.getInt(key, defValue);
        }
    }

    public static void storeLongValue(String key, long value) {
        synchronized (sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }

    public static long retrieveLongValue(String key, long defValue) {
        synchronized (sharedPreferences) {
            return sharedPreferences.getLong(key, defValue);
        }
    }

    public static void storeBooleanValue(String key, boolean value) {
        synchronized (sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static boolean retrieveBooleanValue(String key, boolean defValue) {
        synchronized (sharedPreferences) {
            return sharedPreferences.getBoolean(key, defValue);
        }
    }
}
