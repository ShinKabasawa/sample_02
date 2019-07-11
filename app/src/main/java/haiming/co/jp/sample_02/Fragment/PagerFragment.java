package haiming.co.jp.sample_02.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Activity.TodoRegistActivity;
import haiming.co.jp.sample_02.Adapter.TodoListAdapater;
import haiming.co.jp.sample_02.Dao.DaoTodo;
import haiming.co.jp.sample_02.Data.TodoData;
import haiming.co.jp.sample_02.R;

public class PagerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pageritem, container, false);

        Bundle bundle = getArguments();
        int index = bundle.getInt("INDEX");

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.todo_rec);

        List<TodoData> arrayList = new ArrayList<>();
        DaoTodo daoTodo = new DaoTodo(getActivity().getApplicationContext(),"",null,1);

        arrayList = daoTodo.sel_all_todo();

        RecyclerView.Adapter adapter = new TodoListAdapater(arrayList,getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        llm.setSmoothScrollbarEnabled(true);

        // 区切り線追加
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);


        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(onClickListener);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText("Index:" + index);

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(), TodoRegistActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_right,R.anim.out_left);
        }
    };
}
