package haiming.co.jp.sample_02.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import haiming.co.jp.sample_02.Data.SampleData;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.ViewHolder.sampleViewHolder;

/**
 * 表示、イベントを行う
 */
public class samplelistAdapter extends RecyclerView.Adapter<sampleViewHolder> {
    private Activity listactivity;
    private List<SampleData> list;
    private sampleViewHolder sampleviewholder;

    public samplelistAdapter(List<SampleData> dateset , Activity activity) {
        list = dateset;
        listactivity = activity;
    }

    @NonNull
    @Override
    public sampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.sample_list, viewGroup, false);
        sampleviewholder = new sampleViewHolder(view);
        return sampleviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull sampleViewHolder sampleViewHolder, final int i) {
        sampleViewHolder.name_view.setText(list.get(i).Name);

        // リストのクリックイベント
        sampleViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 0:
                        Toast.makeText(listactivity,"確認" + i, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(listactivity,"確認" + i, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
