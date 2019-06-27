package haiming.co.jp.sample_02.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import haiming.co.jp.sample_02.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    public TextView weather_view;
    public TextView temp_view;
    public TextView temp_min_view;
    public TextView temp_max_view;
    public TextView humidity_view;
    public TextView hour_view;
    public TextView pressure_view;
    public ImageView icon_view;
    public LinearLayout linearLayout;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);

        weather_view = (TextView)itemView.findViewById(R.id.weather_des);
        temp_view = (TextView)itemView.findViewById(R.id.temp);
        temp_min_view = (TextView)itemView.findViewById(R.id.temp_min);
        temp_max_view = (TextView) itemView.findViewById(R.id.temp_max);
        humidity_view = (TextView)itemView.findViewById(R.id.humidity_txt);
        hour_view = (TextView)itemView.findViewById(R.id.hour_txt);
        pressure_view = (TextView)itemView.findViewById(R.id.pressure_txt);
        icon_view = (ImageView)itemView.findViewById(R.id.weather_icon);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.weather_linear);
    }
}
