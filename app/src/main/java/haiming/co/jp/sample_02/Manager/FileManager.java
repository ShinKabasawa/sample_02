package haiming.co.jp.sample_02.Manager;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Data.City_Data;

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
}
