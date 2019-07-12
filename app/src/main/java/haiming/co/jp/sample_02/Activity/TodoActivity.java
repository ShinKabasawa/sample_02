package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import haiming.co.jp.sample_02.Adapter.TodoListAdapater;
import haiming.co.jp.sample_02.Dao.DaoCreate;
import haiming.co.jp.sample_02.Dao.DaoTodo;
import haiming.co.jp.sample_02.Data.TodoData;
import haiming.co.jp.sample_02.R;

public class TodoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        DaoCreate daoCreate = new DaoCreate(this);
        daoCreate.onCreateTable();

    }

    @Override
    protected void onStart(){
        super.onStart();
        init();

        setdata();
        setdata();
    }

    // 初期化
    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.todo_recyclerview);
    }

    // RecyclerViewにデータをセット
    private void setdata(){
        List<TodoData> arrayList = new ArrayList<>();
        DaoTodo daoTodo = new DaoTodo(this,"",null,1);

        arrayList = daoTodo.sel_all_todo();

        RecyclerView.Adapter adapter = new TodoListAdapater(arrayList,this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        llm.setSmoothScrollbarEnabled(true);

        // 区切り線追加
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

    }

    public void onClick_reg(View v){
        Intent intent = new Intent(this,TodoRegistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_right,R.anim.out_left);
    }
}
