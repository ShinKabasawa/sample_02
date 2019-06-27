package haiming.co.jp.sample_02.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import haiming.co.jp.sample_02.Data.Weather5days_Data;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.ViewHolder.WeatherViewHolder;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<Weather5days_Data> list;
    private WeatherViewHolder viewholder;
    private Activity weather_activity;



    public WeatherAdapter(List<Weather5days_Data> dateset , Activity activity) {
        list = dateset;
        weather_activity = activity;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.weather_list, viewGroup, false);
        viewholder = new WeatherViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        String[] date = list.get(i).date.split(" ");
        weatherViewHolder.hour_view.setText(date[0] + "\n" + date[1]);
        weatherViewHolder.temp_view.setText(list.get(i).temp + "℃");
        weatherViewHolder.temp_min_view.setText(list.get(i).temp_min + "℃");
        weatherViewHolder.temp_max_view.setText(list.get(i).temp_max + "℃");
        weatherViewHolder.humidity_view.setText(list.get(i).humidity + "％");
        weatherViewHolder.pressure_view.setText(list.get(i).pressure + "hpa");
        weatherViewHolder.weather_view.setText(list.get(i).description);
        String path = "/data/data/haiming.co.jp.sample_02/file/" + list.get(i).icon + ".png";

        Log.v("WeatherAdapter","icon = " + path);

        File file = new File(path);

        if (file.exists()){
            try(InputStream inputStream0 = new FileInputStream(file) ) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream0);
                weatherViewHolder.icon_view.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // そのまま
            // weatherViewHolder.icon_view.setImageDrawable(weather_activity.getResources().getDrawable(R.drawable.ic_launcher_foreground));
        }

        //ArrayList<ArrayList<Weather5days_Data>> arrayLists = new ArrayList<>();
        //List<Weather5days_Data> list = arrayLists.get(0);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
