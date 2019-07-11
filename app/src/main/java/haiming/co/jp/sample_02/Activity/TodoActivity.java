package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import haiming.co.jp.sample_02.Dao.DaoCreate;
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
    }

    // 初期化
    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.todo_recyclerview);
    }

    // RecyclerViewにデータをセット
    private void setdata(){

    }

    public void onClick_reg(View v){
        Intent intent = new Intent(this,TodoRegistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_right,R.anim.out_left);
    }
}
