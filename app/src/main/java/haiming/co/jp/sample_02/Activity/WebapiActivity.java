package haiming.co.jp.sample_02.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import haiming.co.jp.sample_02.Data.City_Data;
import haiming.co.jp.sample_02.Data.Common;
import haiming.co.jp.sample_02.Interface.AsyncTaskCallback;
import haiming.co.jp.sample_02.Manager.ApiManager;
import haiming.co.jp.sample_02.Manager.FileManager;
import haiming.co.jp.sample_02.R;

/**
 * WebAPIサンプル（GET）
 */
public class WebapiActivity extends AppCompatActivity {

    public EditText editText;
    private final String url ="http://zipcloud.ibsnet.co.jp/api/search?zipcode=%s";
    public TextView textView;

    private EditText editText_2;
    //private final String url_weather = "http://api.openweathermap.org/data/2.5/weather?id=1850147&units=metric&appid=%s";
    private final String url_weather = "http://api.openweathermap.org/data/2.5/weather?id=%s&units=metric&lang=ja&appid=%s";          // 現在の天気取得URL
    private final String url_weather_5days = "http://api.openweathermap.org/data/2.5/forecast/?id=%s&appid=%s";                       // 5日間の天気取得URL

    private TextView textView_2;
    private List<City_Data> city_array;
    private ImageView icon_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapi);
    }

    @Override
    protected void onStart(){
        super.onStart();
        editText = (EditText)findViewById(R.id.zip_edit);
        textView = (TextView)findViewById(R.id.zip_txt);
        editText_2 = (EditText)findViewById(R.id.city_name);
        textView_2 = (TextView)findViewById(R.id.weather_txt);
        icon_view = (ImageView)findViewById(R.id.iconimage);

        city_array = FileManager.read_file(this);

        //Log.v("WebapiActivity","city_list.size = " + city_array.size());
    }

    /**
     *  住所取得
     * @param v
     */
    public void get_onClick(View v){
        if (!editText.getText().toString().equals("")){
            String url_2 = String.format(url,editText.getText().toString());
            ApiManager.getzip_code get_zip_code = new ApiManager.getzip_code(url_2,this);
            get_zip_code.execute();
        }else{
            Toast.makeText(this,"郵便番号を入力してください。",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 天気取得
     * @param v
     */
    public void get_weather_onClick(View v) {
        if (!editText_2.getText().toString().equals("")){
            long city_id = 0;

            for (int i = 0; i < city_array.size(); i++){
                if (editText_2.getText().toString().equals(city_array.get(i).city_name_jp)){
                    city_id = city_array.get(i).city_id;
                    break;
                }
            }

            if (city_id != 0) {
                final String url = String.format(url_weather, String.valueOf(city_id), getResources().getString(R.string.api_key));

                final AsyncTaskCallback asyncTaskCallback_icon = new AsyncTaskCallback() {
                    @Override
                    public void onTaskCompleted(String result) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < Common.weatherlist.size();i++){
                            sb = new StringBuilder();
                            sb.append(Common.weatherlist.get(i).main);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).description);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).temp);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).pressure);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).humidity);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).temp_min);
                            sb.append("\n");
                            sb.append(Common.weatherlist.get(i).temp_max);
                            sb.append("\n");
                        }
                        textView_2.setText(sb.toString());

                        String fileName = Common.weatherlist.get(0).icon + ".png";
                        String dir_path = "/data/data/haiming.co.jp.sample_02/file";
                        String AttachName = dir_path + "/" + fileName;

                        File file = new File(AttachName);

                        try(InputStream inputStream0 = new FileInputStream(file) ) {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream0);
                            icon_view.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.v("APIManager","icon表示失敗");
                        }
                    }
                };

                final AsyncTaskCallback asyncTaskCallback = new AsyncTaskCallback() {
                    @Override
                    public void onTaskCompleted(String result) {
                        String icon_file = Common.weatherlist.get(0).icon;
                        Log.v("WebapiActivity","asyncTaskCallback");
                        String icon_url = "http://openweathermap.org/img/w/" + icon_file + ".png";
                        String file_name = "";
                        file_name = icon_file + ".png";
                        ApiManager.get_weather_icon get_icon = new ApiManager.get_weather_icon(icon_url, file_name, asyncTaskCallback_icon);
                        get_icon.execute();
                    }
                };

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ApiManager.getweather getweather = new ApiManager.getweather(url, asyncTaskCallback);
                        getweather.execute();
                    }
                });

                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(this,"都市名が正しくありません",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"都市名を入力してください。",Toast.LENGTH_SHORT).show();
        }
    }
}
