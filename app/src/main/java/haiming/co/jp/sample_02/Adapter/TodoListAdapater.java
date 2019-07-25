package haiming.co.jp.sample_02.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Activity.TodoDetailActivity;
import haiming.co.jp.sample_02.Data.TodoData;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.ViewHolder.TodoViewHolder;

/**
 * Todo用のAdapter
 */
public class TodoListAdapater extends RecyclerView.Adapter<TodoViewHolder> {
    private ArrayList<TodoData> list;
    private Activity activity;


    public TodoListAdapater(List<TodoData> arrayList, Activity todoActivity) {
        this.list = (ArrayList<TodoData>) arrayList;
        this.activity = todoActivity;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.todo_layout, viewGroup, false);
        TodoViewHolder todoViewHolder = new TodoViewHolder(view);
        return todoViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoViewHolder todoViewHolder, @SuppressLint("RecyclerView") final int i) {
        todoViewHolder.title_view.setText(list.get(i).todo_title);
        todoViewHolder.content_view.setText(list.get(i).todo_content);

        if (list.get(i).fin_flg == 1){
            todoViewHolder.checkBox.setChecked(true);
        }else{
            todoViewHolder.checkBox.setChecked(false);
        }

        todoViewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todoViewHolder.checkBox.isChecked()){
                    todoViewHolder.checkBox.setChecked(false);
                    // DB更新
                }else{
                    todoViewHolder.checkBox.setChecked(true);
                    // DB更新
                }
            }
        });

        todoViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TodoDetailActivity.class);
                String[] data_array = new String[4];
                data_array[0] = list.get(i).todo_title;
                data_array[1] = list.get(i).todo_content;
                data_array[2] = list.get(i).setting_date;
                data_array[3] = String.valueOf(list.get(i).fin_flg);
                intent.putExtra("data",data_array);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.in_right,R.anim.out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
