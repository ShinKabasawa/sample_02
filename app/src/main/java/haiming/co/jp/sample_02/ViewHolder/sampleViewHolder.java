package haiming.co.jp.sample_02.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import haiming.co.jp.sample_02.R;

/**
 * リストで使用するViewの割り当てを行う
 */
public class sampleViewHolder extends RecyclerView.ViewHolder{

    public TextView name_view;
    public LinearLayout linearLayout;

    public sampleViewHolder(@NonNull View itemView) {
        super(itemView);
        name_view = (TextView)itemView.findViewById(R.id.sample_txt);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.sample_linear);
    }
}
