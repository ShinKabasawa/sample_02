package haiming.co.jp.sample_02.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import haiming.co.jp.sample_02.Adapter.WeatherAdapter;
import haiming.co.jp.sample_02.Data.Common;
import haiming.co.jp.sample_02.Data.Weather5days_Data;
import haiming.co.jp.sample_02.Interface.AsyncTaskCallback;
import haiming.co.jp.sample_02.Interface.sample;
import haiming.co.jp.sample_02.Manager.ApiManager;
import haiming.co.jp.sample_02.R;

/**
 * 5日間天気情報取得Activity
 */
public class WeatherActivity extends AppCompatActivity {

    private final String url_weather_5days = "http://api.openweathermap.org/data/2.5/forecast/?id=%s&units=metric&lang=ja&appid=%s";    // 5日間の天気取得URL
    private final String url_weather_5dayslongitude_latitude = "http://api.openweathermap.org/data/2.5/forecast/?lat=%s&lon=%s&units=metric&lang=ja&appid=%s";    // 5日間の天気取得URL(緯度経度検索)
    private final String url_weather_longitude_latitude = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&lang=ja&cnt=1&appid=%s";     // 現在の天気取得URL（緯度経度検索）
    private TextView textView;
    private ProgressDialog progressDialog;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        textView = (TextView)findViewById(R.id.weatherdata_txt);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        rv = (RecyclerView)findViewById(R.id.weather_list_5);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v("test","onStart");
        LinearLayoutManager llm_1 = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm_1);
        llm_1.setSmoothScrollbarEnabled(true);
        llm_1.setOrientation(LinearLayoutManager.HORIZONTAL);

        ////////////////////////////////////////////////////////////////
        // LinearLayoutManager llm_2 = new LinearLayoutManager(this); //
        // llm_2.setSmoothScrollbarEnabled(true);                     //
        // llm_2.setOrientation(LinearLayoutManager.HORIZONTAL);      //
        // rv_1.setHasFixedSize(true);                                //
        // rv_1.setLayoutManager(llm_2);                              //
        // rv_2.setHasFixedSize(true);                                //
        // rv_2.setLayoutManager(llm_2);                              //
        // rv_3.setHasFixedSize(true);                                //
        // rv_3.setLayoutManager(llm_2);                              //
        // rv_4.setHasFixedSize(true);                                //
        // rv_4.setLayoutManager(llm_2);                              //
        ////////////////////////////////////////////////////////////////

    }

    /**
     * 取得クリックイベント
     * @param v
     */
    public void getWeather_5days(View v){

        if (!Common.NetworkCheck(this)) {
            final Handler handler = new Handler();
            final Activity activity = this;
            AsyncTaskCallback asyncTaskCallback = new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(String result) {
                    if (result.equals("OK")) {
                        Log.v("WeatherActivity", "5日間天気データ取得完了");
                        Log.v("WeatherActivity", "Common.weather5days_datalist.size() = " + Common.weather5days_datalist.size());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<ArrayList<Weather5days_Data>> arrayLists = new ArrayList<>();
                                ArrayList<Weather5days_Data> list = new ArrayList<>();

                                for (int i = 0; i < Common.weather5days_datalist.size(); i++) {
                                    String[] s = Common.weather5days_datalist.get(i).date.split(" ");
                                    int date = Integer.parseInt(s[1].substring(0, 2));

                                    switch (date) {
                                        case 0:
                                            list.add(Common.weather5days_datalist.get(i));
                                            arrayLists.add(list);
                                            list = new ArrayList<>();
                                            break;
                                        case 3:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 6:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 9:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 12:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 15:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 18:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                        case 21:
                                            list.add(Common.weather5days_datalist.get(i));
                                            break;
                                    }
                                }
                                Log.v("WeatherActivity", "arralists = " + arrayLists.size());

                                Calendar calendar = Calendar.getInstance();
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

                                Log.v("WeatherActivity", "arralists = " + formatter.format(calendar.getTime()).toUpperCase());
                                Log.v("WeatherActivity", "arralists = " + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH));

                                RecyclerView.Adapter adapter = new WeatherAdapter(Common.weather5days_datalist, activity);
                                rv.swapAdapter(adapter, false);

                                Common.arrayList_weather = arrayLists;

                                Intent intent = new Intent(getApplicationContext(), Weather_2_Activity.class);
                                startActivity(intent);

                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                // LinearLayoutManager llm = new LinearLayoutManager(activity);                                                      //
                                // rv.setHasFixedSize(true);                                                                                         //
                                // rv.setLayoutManager(llm);                                                                                         //
                                // llm.setSmoothScrollbarEnabled(true);                                                                              //
                                // llm.setOrientation(LinearLayoutManager.HORIZONTAL);                                                               //
                                // 区切り線追加                                                                                                      //
                                // RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL); //
                                // rv.addItemDecoration(itemDecoration);                                                                             //
                                // rv_1.setAdapter(adapter);                                                                                         //
                                // rv_2.setAdapter(adapter);                                                                                         //
                                // rv_3.setAdapter(adapter);                                                                                         //
                                // rv_4.setAdapter(adapter);                                                                                         //
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                            }
                        });
                    } else {
                        Log.v("WeatherActivity", "5日間天気データ取得失敗");
                    }

                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                }
            };

            sample sample = new sample() {
                @Override
                public void Callback(ArrayList arrayList, ArrayList arrayList_) {
                    // 処理

                }
            };

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("情報取得中...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            String url = String.format(url_weather_5days, "1850147", getString(R.string.api_key));

            ApiManager.getweather_5days getweather_5days = new ApiManager.getweather_5days(url, asyncTaskCallback);
            getweather_5days.execute();


        }else{
            Toast.makeText(this,"オフラインです",Toast.LENGTH_SHORT).show();
        }
    }
}
