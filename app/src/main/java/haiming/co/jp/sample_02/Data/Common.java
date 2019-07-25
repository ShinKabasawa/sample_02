package haiming.co.jp.sample_02.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 共通データクラス
 */
public class Common {
    public static List<Weather_Data> weatherlist;                           // 現在の天気データ配列
    public static ArrayList<Weather5days_Data> weather5days_datalist;            // 5日間の天気データ配列
    public static ArrayList<ArrayList<Weather5days_Data>> arrayList_weather;     // 5日間の天気データ（3時間毎）データ配列

    public static ArrayList arrayList_1;
    public static ArrayList arrayList_2;
    public static ArrayList arrayList_3;
    public static ArrayList arrayList_4;
    public static ArrayList arrayList_5;


    /**
     * ネットワーク接続確認
     * @param context   コンテキスト
     * @return          ネットワーク接続情報
     */
    public static boolean NetworkCheck(Context context){
        boolean result = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()){
            result = true;
        }
        return  result;
    }

    /**
     * SharedPreferenceを保存する
     * @param con    Context
     * @param key    保存するSharedPreferenceのキー
     * @param obj    保存するSharedPreferenceの値
     * @param spType 保存するSharedPreferenceの値の型 1:String 2:int 3:boolean 4:Long 5:Float
     */
    public static void setPreference(Context con, String key, Object obj, int spType) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String str = String.valueOf(obj);

        switch (spType) {
            case 1: // String
                prefs.edit().putString(key, str).apply();
                break;
            case 2: // int
                prefs.edit().putInt(key, Integer.parseInt(str)).apply();
                break;
            case 3: // boolean
                boolean bool = true;
                if (str.equals("false")) {
                    bool = false;
                }
                prefs.edit().putBoolean(key, bool).apply();
                break;
            case 4: // long
                prefs.edit().putLong(key, Long.parseLong(str)).apply();
                break;
            case 5: // float
                prefs.edit().putFloat(key, Float.parseFloat(str)).apply();
                break;
        }
    }
    /**
     * SharedPreferenceをから値を取得する
     * @param con     Context
     * @param key     取得するSharedPreferenceのキー
     * @param spType  取得するSharedPreferenceの値の型
     * @return        取得したSharedPreferenceの値
     */
    public static Object loadPreference(Context con, String key, int spType) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(con);
        Object obj = new Object();

        switch (spType) {
            case 1: // String
                obj = sp.getString(key, "");
                break;
            case 2: // int
                obj = sp.getInt(key, 1);
                break;
            case 3: // boolean
                obj = sp.getBoolean(key, false);
                break;
            case 4: // long
                obj = sp.getLong(key, 1);
                break;
            case 5: // float
                obj = sp.getFloat(key, 1);
                break;
        }
        return obj;
    }


}
