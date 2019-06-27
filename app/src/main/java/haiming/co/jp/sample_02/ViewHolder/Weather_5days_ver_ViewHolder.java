package haiming.co.jp.sample_02.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import haiming.co.jp.sample_02.R;

public class Weather_5days_ver_ViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView recyclerView;

    public Weather_5days_ver_ViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.rec_ver);
    }
}
