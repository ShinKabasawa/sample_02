package haiming.co.jp.sample_02.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Data.Weather5days_Data;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.ViewHolder.Weather_5days_ver_ViewHolder;

/**
 * 縦方向のRecyclerViewのAdapter
 */
public class Weather_5days_ver_Adapter extends RecyclerView.Adapter<Weather_5days_ver_ViewHolder> {
    private ArrayList<List<Weather5days_Data>> list;
    private Weather_5days_ver_ViewHolder weather_5days_ver_viewHolder;
    private Context context_1;

    public Weather_5days_ver_Adapter(ArrayList<List<Weather5days_Data>> arrayList,Context context){
        list = arrayList;
        Log.v("Weather_5days_ver", "list = " + list.size());
        context_1 = context;
    }


    @NonNull
    @Override
    public Weather_5days_ver_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.weather_5days_list_vertical, viewGroup, false);
        weather_5days_ver_viewHolder = new Weather_5days_ver_ViewHolder(view);
        return weather_5days_ver_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Weather_5days_ver_ViewHolder viewHolder, int i) {

        // 横方向のRecyclerViewのデータをセット

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context_1,LinearLayoutManager.HORIZONTAL,false));
        Weather_5days_hol_Adapter weather_5days_hol_adapter = new Weather_5days_hol_Adapter(list.get(i));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context_1, DividerItemDecoration.HORIZONTAL);
        viewHolder.recyclerView.addItemDecoration(itemDecoration);
        viewHolder.recyclerView.swapAdapter(weather_5days_hol_adapter,false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
