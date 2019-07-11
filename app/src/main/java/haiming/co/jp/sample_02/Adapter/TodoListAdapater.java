package haiming.co.jp.sample_02.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import haiming.co.jp.sample_02.ViewHolder.TodoViewHolder;

/**
 * Todo用のAdapter
 */
public class TodoListAdapater extends RecyclerView.Adapter<TodoViewHolder> {
    private List list;

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder todoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
