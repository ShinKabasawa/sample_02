package haiming.co.jp.sample_02.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import haiming.co.jp.sample_02.R;

/**
 * Todo用のViewHolder
 */
public class TodoViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView title_view;
    TextView content_view;
    LinearLayout linearLayout;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = (CheckBox)itemView.findViewById(R.id.todo_checkbox);
        title_view = (TextView)itemView.findViewById(R.id.todo_titlle);
        content_view = (TextView)itemView.findViewById(R.id.todo_content);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.todo_content_linear);
    }
}
