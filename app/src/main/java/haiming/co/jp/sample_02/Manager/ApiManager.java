package haiming.co.jp.sample_02.Manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import haiming.co.jp.sample_02.Data.Common;
import haiming.co.jp.sample_02.Data.Weather5days_Data;
import haiming.co.jp.sample_02.Data.Weather_Data;
import haiming.co.jp.sample_02.Interface.AsyncTaskCallback;
import haiming.co.jp.sample_02.R;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * APIを扱うクラス
 */
public class ApiManager {

    // 住所取得クラス
    public static class getzip_code extends AsyncTask<Object, Integer, Object>{

        private Activity activity;
        private String URL;
        private String result_s;
        private Response zipcode_response;

        /**
         * 住所取得API
         * @param url　        取得先URL
         * @param activity_　　使用する画面
         */
        public getzip_code(String url,  Activity activity_){
            this.URL = url;
            this.activity = activity_;
            this.result_s = new String();
            this.zipcode_response = null;
        }

        @Override
        protected Object doInBackground(Object... objects) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(this.URL).build();
            Call call = client.newCall(request);
            zipcode_response = null;

            try {
                zipcode_response = call.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (zipcode_response != null) {
                if (zipcode_response.code() == 200) {
                    Log.v("APIManager", "通信成功");
                    try {
                        String jsonData = zipcode_response.body().string();
                        JSONObject Jobject = new JSONObject(jsonData);
                        JSONArray Jarray = Jobject.getJSONArray("results");
                        JSONObject object = new JSONObject();

                        for (int i = 0; i < Jarray.length(); i++) {
                            object = Jarray.getJSONObject(i);
                        }

                        StringBuilder sb = new StringBuilder();
                        sb.append(object.getString("address1"));
                        sb.append(object.getString("address2"));
                        sb.append(object.getString("address3"));
                        result_s = sb.toString();

                        ///////////////////////////////////////////////
                        // result_s += object.getString("address1"); //
                        // result_s += object.getString("address2"); //
                        // result_s += object.getString("address3"); //
                        ///////////////////////////////////////////////

                        return zipcode_response.body();

                    } catch (IOException e) {
                        Log.v("APIManager","IOException e =" + e);
                    } catch (JSONException e) {
                        Log.v("APImanager", "JSONException e = " + e);
                    } catch (Exception e) {
                        Log.v("APImanager", "Exception e = " + e);
                    }
                } else {

                    String jsonData = new String();
                    JSONObject Jobject = new JSONObject();
                    JSONArray Jarray = new JSONArray();
                    JSONObject object = new JSONObject();

                    try {
                        jsonData = zipcode_response.body().string();
                        Jobject = new JSONObject(jsonData);
                        Jarray = Jobject.getJSONArray("messages");
                        for (int i = 0; i < Jarray.length(); i++) {
                            object = Jarray.getJSONObject(i);
                            result_s += object.get("messages");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Log.v("APIManager", "通信失敗" + zipcode_response.code() + "エラーメッセージ : " + result_s);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            //処理が終了した
            //resultは、doInBackgroundからの戻り値
            TextView txt_view = this.activity.findViewById(R.id.zip_txt);
            txt_view.setText(result_s);
        }
    }

    // 天気取得クラス
    public static class getweather extends AsyncTask<Response, Integer, Response>{

        private Activity weatheractivity;
        private Response getweather_response;
        private String W_URL;
        private CountDownLatch lach;
        private AsyncTaskCallback asyncTaskCallback;

        /**
         * 天気情報取得API
         * @param url                   天気情報取得URL
         * @param asyncTaskCallback_
         */
        public getweather(String url, AsyncTaskCallback asyncTaskCallback_){
            this.W_URL = url;
            //this.weatheractivity = activity;
            this.asyncTaskCallback = asyncTaskCallback_;
            Common.weatherlist = new ArrayList<>();
        }

        @Override
        protected Response doInBackground(Response... responses) {
            // 非同期処理開始
            OkHttpClient client = new OkHttpClient();
            Log.v("APIManager","URL = " + this.W_URL);
            Request request = new Request.Builder().url(this.W_URL).build();
            Call call = client.newCall(request);

            try {
                Response response = call.execute();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Response result){
            // 非同期処理終了
            if (result != null) {
                if (result.code() == 200) {
                    try {
                        String jsonData = result.body().string();
                        Weather_Data weather_data = new Weather_Data();
                        List<Weather_Data> list = new ArrayList<>();

                        Log.v("APIManager","WeatherAPI = " + jsonData);

                        JSONObject jsonObject = new JSONObject(jsonData);
                        JSONObject object = null;
                        JSONArray jsonArray = jsonObject.getJSONArray("weather");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            object = jsonArray.getJSONObject(i);
                        }

                        StringBuilder sb =new StringBuilder();
                        sb.append(object.getString("main"));
                        sb.append("\n");
                        sb.append(object.getString("description"));
                        sb.append("\n");
                        sb.append(object.getString("icon"));
                        sb.append("\n");

                        jsonObject = new JSONObject(jsonData);
                        JSONObject object_main = jsonObject.getJSONObject("main");

                        sb.append(object_main.getString("temp"));
                        sb.append("\n");
                        sb.append(object_main.getString("pressure"));
                        sb.append("\n");
                        sb.append(object_main.getString("humidity"));
                        sb.append("\n");
                        sb.append(object_main.getString("temp_min"));
                        sb.append("\n");
                        sb.append(object_main.getString("temp_max"));
                        sb.append("\n");

                        weather_data.main = object.getString("main");
                        weather_data.description = object.getString("description");
                        weather_data.icon = object.getString("icon");
                        weather_data.temp = object_main.getString("temp");
                        weather_data.pressure = object_main.getString("pressure");
                        weather_data.humidity = object_main.getString("humidity");
                        weather_data.temp_min = object_main.getString("temp_min");
                        weather_data.temp_max = object_main.getString("temp_max");

                        list.add(weather_data);

                        Common.weatherlist = list;
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v("APIManager","IOException = " + e);
                    } catch (Exception e){
                        e.printStackTrace();
                        Log.v("APIManager","Exception = " + e);
                    }
                }else{
                    if (result != null) {
                        Log.v("APIManager", "getweather response.status = " + result.code());
                    }
                    //Toast.makeText(weatheractivity,"天気情報取得失敗",Toast.LENGTH_SHORT).show();
                }
            }
            this.asyncTaskCallback.onTaskCompleted("NG");
        }
    }

    /**
     * 天気アイコン取得クラス
     */
    public static class get_weather_icon extends AsyncTask<Object, Integer, Object>{
        private String icon_url;
        private String file_name;
        private AsyncTaskCallback get_icon_callback;

        /**
         * 天気アイコン取得API
         * @param W_iconURL     天気アイコン取得URL
         * @param file          天気アイコンファイル名
         * @param callback      コールバック
         */
        public  get_weather_icon(String W_iconURL, String file, AsyncTaskCallback callback){
            this.icon_url = W_iconURL;
            this.file_name = file;
            this.get_icon_callback = callback;
        }

        @Override
        protected Object doInBackground(Object... integers) {
            Bitmap icon = null;
            try {
                String requestUrl = this.icon_url;
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
                icon = BitmapFactory.decodeStream(in);
                in.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String dir_path = "/data/data/haiming.co.jp.sample_02/file";
            String fileName = this.file_name;
            String AttachName = dir_path + "/" + fileName;
            File dir_file = new File(dir_path);

            if (!dir_file.exists()){
                dir_file.mkdir();
            }

            File icon_file = new File(AttachName);

            if (!icon_file.exists()) {
                try {
                    FileOutputStream out = new FileOutputStream(AttachName);
                    if (icon != null) {
                        icon.compress(Bitmap.CompressFormat.PNG, 100, out);
                    }
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
            // 非同期処理終了
            Log.v("APIManager","天気アイコン取得処理終了");
            this.get_icon_callback.onTaskCompleted("OK");
        }
    }

    /**
     * 5日間・3時間毎の天気取得クラス
     */
    public static class getweather_5days extends AsyncTask<Response,Void,Response>{
        private String five_days_url;
        public AsyncTaskCallback fivedays_callback;

        /**
         * 5日間・3時間毎の天気情報取得API
         * @param weather_5days_url     5日間の天気取得URL
         * @param callback              非同期処理終了コールバック
         */
        public  getweather_5days(String weather_5days_url, AsyncTaskCallback callback){
            this.five_days_url = weather_5days_url;
            fivedays_callback = callback;
            Common.weather5days_datalist = new ArrayList<>();
        }

        @Override
        protected Response doInBackground(Response... responses) {
            // 非同期処理開始
            OkHttpClient client = new OkHttpClient();
            Log.v("APIManager","URL = " + this.five_days_url);
            Request request = new Request.Builder().url(this.five_days_url).build();
            Call call = client.newCall(request);

            try {
                Response response = call.execute();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(final Response result){
            // 非同期処理終了
            if (result != null){
                if (result.code() == 200) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //JSONパース
                                String jsonData = result.body().string();
                                Weather_Data weather_data = new Weather_Data();
                                List<Weather_Data> list = new ArrayList<>();
                                Log.v("APIManager", "WeatherAPI = " + jsonData);



                                JSONObject jsonObject = new JSONObject(jsonData);
                                JSONObject object = null;
                                JSONArray jsonArray = jsonObject.getJSONArray("list");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    object = jsonArray.getJSONObject(i);
                                    JSONObject main_object = object.getJSONObject("main");

                                    JSONArray weather_object_array = object.getJSONArray("weather");
                                    JSONObject weather_object = new JSONObject();

                                    for (int j = 0; j < weather_object_array.length(); j++){
                                        weather_object = weather_object_array.getJSONObject(j);
                                    }

                                    // リストに天気データ追加
                                    // 「main」
                                    Weather5days_Data weather5days_data = new Weather5days_Data();
                                    weather5days_data.temp = main_object.getString("temp");
                                    weather5days_data.temp_min = main_object.getString("temp_min");
                                    weather5days_data.temp_max = main_object.getString("temp_max");
                                    weather5days_data.pressure = main_object.getString("pressure");
                                    weather5days_data.sea_level_pre = main_object.getString("sea_level");
                                    weather5days_data.grnd_level_pre = main_object.getString("grnd_level");
                                    weather5days_data.humidity = main_object.getString("humidity");
                                    weather5days_data.date = object.getString("dt_txt");

                                    // 「weather」
                                    weather5days_data.main = weather_object.getString("main");
                                    weather5days_data.description = weather_object.getString("description");
                                    weather5days_data.icon = weather_object.getString("icon");

                                    Common.weather5days_datalist.add(weather5days_data);

                                    Log.v("APIManager","weather_object_array = " + weather_object_array.length());
                                    Log.v("APIManager","date = "+ object.getString("dt_txt"));
                                }
                                Log.v("APIManager","jsonArray = " + jsonArray.length());
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            Log.v("APIManager","天気アイコン取得処理終了");
                            fivedays_callback.onTaskCompleted("OK");
                        }
                    });

                    thread.start();

                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        fivedays_callback.onTaskCompleted("NG");
                    }catch (Exception e){
                        e.printStackTrace();
                        fivedays_callback.onTaskCompleted("NG");
                    }




                }else{
                    if (result != null) {
                        Log.v("APIManager", "getweather response.status = " + result.code());
                    }
                    fivedays_callback.onTaskCompleted("NG");
                }
            }else{
                fivedays_callback.onTaskCompleted("NG");
            }
        }
    }


    /**
     * Googleニュースを取得するクラス
     */
    public static class get_news extends AsyncTask<Response, Response, Response>{
        private String news_url;
        private AsyncTaskCallback asyncTaskCallback;
        private Context context;

        /**
         * Googleニュースを取得するAPI
         * @param url           ニュース取得先URL
         * @param callback      非同期処理終了コールバック
         * @param con           コンテキスト
         */
        public void get_news(String url, AsyncTaskCallback callback, Context con) {
            this.news_url = url;
            this.asyncTaskCallback = callback;
            this.context = con;
        }

        @Override
        protected Response doInBackground(Response... objects) {
            // 非同期処理開始
            OkHttpClient client = new OkHttpClient();
            Log.v("APIManager","URL = " + this.news_url);
            Request request = new Request.Builder().url(this.news_url).build();
            Call call = client.newCall(request);

            try {
                Response response = call.execute();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Response result) {
            if (result != null){
                if (result.code() == 200) {
                    String xml = "";

                    try {
                        xml = result.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    final String finalXml = xml;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ////////////////////////////////////////////////////////////////////////////////////
                            // XMLファイルのパース                                                            //
                            ////////////////////////////////////////////////////////////////////////////////////
                            XmlPullParser xmlPullParser = Xml.newPullParser();
                            try {
                                if (!finalXml.equals("")) {
                                    xmlPullParser.setInput(new StringReader(finalXml));
                                }
                            } catch (XmlPullParserException e) {
                                Log.d("XmlPullParserSample", "Error = " + e);
                            }

                            try {
                                int eventType;
                                eventType = xmlPullParser.getEventType();

                                while (eventType != XmlPullParser.END_DOCUMENT) {

                                    if(eventType == XmlPullParser.START_DOCUMENT) {
                                        Log.d("XmlPullParserSample", "Start document");
                                    }
                                    else if(eventType == XmlPullParser.END_DOCUMENT) {
                                        Log.d("XmlPullParserSample", "End document");
                                    }
                                    else if(eventType == XmlPullParser.START_TAG) {
                                        Log.d("XmlPullParserSample", "Start tag " + xmlPullParser.getName());
                                    }
                                    else if(eventType == XmlPullParser.END_TAG) {
                                        Log.d("XmlPullParserSample", "End tag " + xmlPullParser.getName());
                                    }
                                    else if(eventType == XmlPullParser.TEXT) {
                                        Log.d("XmlPullParserSample", "Text " + xmlPullParser.getText());
                                    }
                                    eventType = xmlPullParser.next();
                                }
                            } catch (Exception e) {
                                Log.d("XmlPullParserSample", "Error = " + e);
                            }
                        }
                    });

                    thread.start();

                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("APIManager","InterruptException = " + e);
                    } catch (Exception e){
                        Log.d("APIManager","Exception = " + e);
                    }
                }
            }

            this.asyncTaskCallback.onTaskCompleted("OK");

        }
    }
}
