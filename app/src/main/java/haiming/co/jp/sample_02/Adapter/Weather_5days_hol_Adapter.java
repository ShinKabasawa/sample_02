package haiming.co.jp.sample_02.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import haiming.co.jp.sample_02.Data.Weather5days_Data;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.ViewHolder.WeatherViewHolder;

/**
 * 横方向のRecyclerViewのAdapter
 */
public class Weather_5days_hol_Adapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<Weather5days_Data> list;
    private WeatherViewHolder weatherViewHolder;
    private Context context;

    public Weather_5days_hol_Adapter(List<Weather5days_Data> dateset, Context context_) {
        list = dateset;
        context = context_;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.weather_list, viewGroup, false);
        weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder viewHolder, int i) {
        String[] date = list.get(i).date.split(" ");
        viewHolder.hour_view.setText(date[0] + "\n" + date[1]);
        viewHolder.temp_view.setText(list.get(i).temp + "℃");
        viewHolder.temp_min_view.setText(list.get(i).temp_min + "℃");
        viewHolder.temp_max_view.setText(list.get(i).temp_max + "℃");
        viewHolder.humidity_view.setText(list.get(i).humidity + "％");
        viewHolder.pressure_view.setText(list.get(i).pressure + "hpa");
        viewHolder.weather_view.setText(list.get(i).description);
        @SuppressLint("SdCardPath") String path = "/data/data/haiming.co.jp.sample_02/file/" + list.get(i).icon + ".png";

        Log.v("WeatherAdapter","icon = " + path);

        File file = new File(path);

//        if (file.exists()){
            try(InputStream inputStream = context.getAssets().open(list.get(i).icon + ".png");) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                viewHolder.icon_view.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }else{
//            // そのまま
//            // weatherViewHolder.icon_view.setImageDrawable(weather_activity.getResources().getDrawable(R.drawable.ic_launcher_foreground));
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
