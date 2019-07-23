package haiming.co.jp.sample_02.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
}
