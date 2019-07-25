package haiming.co.jp.sample_02.Manager;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Data.City_Data;
import haiming.co.jp.sample_02.Data.prefectures_data;

/**
 * ファイル操作クラス
 */
public class FileManager {

    /**
     * 都市リストファイル読み込み
     * @param activity
     * @return
     */
    public static List<City_Data> read_file(Activity activity)  {
        List list = new ArrayList();
        BufferedReader br = null;
        String str = new String();
        try {
            InputStream inputStream = activity.getAssets().open("test.csv");
            InputStreamReader ireader = new InputStreamReader(inputStream, "Shift-JIS");
            br = new BufferedReader(ireader);

            List<String> ss = new ArrayList<>();

            long i= 0;

            //ファイルの読み込み
            while (br.readLine() != null) {
                City_Data city_data = new City_Data();

                str = new String();
                str = String.valueOf(br.readLine());

                String[] city_data_list = str.split(",");

                city_data.city_id = Long.parseLong(city_data_list[0]);
                city_data.country_code = "JP";
                city_data.city_name_jp = city_data_list[2];

                list.add(city_data);

                i++;
            }

            Log.v("FileManager", "i = " + i);
            Log.v("FileManager", "ss = " + ss.size());
            br.close();
        } catch (Exception e){
            e.printStackTrace();
            Log.v("FileManager","str = " + str);
        }
        return list;
    }

    public static List<prefectures_data> prefectures_read_file(Activity activity)  {
        List list = new ArrayList();
        BufferedReader br = null;
        InputStream inputStream =null;
        InputStreamReader ireader = null;
        String str = new String();
        long i= 0;
        try {
            inputStream = activity.getAssets().open("longitude_latitude.csv");
            ireader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(ireader);
            str = "";
            //ファイルの読み込み
            while ((str = br.readLine()) != null) {
                prefectures_data prefectures_data = new prefectures_data();

                Log.v("str","str = " + str);

                if (str != null) {
                    String[] city_data_list = str.split(",");

                    prefectures_data.region = city_data_list[0];
                    prefectures_data.prefectures = city_data_list[1];
                    prefectures_data.city = city_data_list[2];
                    prefectures_data.lat = city_data_list[3];
                    prefectures_data.lon = city_data_list[4];


                    list.add(prefectures_data);
                }
                i++;
            }

            Log.v("FileManager", "i = " + i);
            inputStream.close();

        } catch (Exception e){
            e.printStackTrace();
            Log.v("FileManager","str = " + e + " " + i);
        }finally {
            try {
                inputStream.close();
                ireader.close();
                br.close();
            }catch (Exception w){
                Log.v("finally","error" + w);
            }
        }
        return list;
    }



}
