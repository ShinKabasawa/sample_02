package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import haiming.co.jp.sample_02.R;

public class TodoDetailActivity extends AppCompatActivity {
    private TextView title_view;
    private TextView content_view;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        data = intent.getStringArrayExtra("data");
        title_view = (TextView)findViewById(R.id.todo_title_detail);
        content_view = (TextView)findViewById(R.id.todo_content_detail);

        title_view.setText(data[0]);
        content_view.setText(data[1]);
    }

    public void onClick_Edit(View v){
        Intent intent = new Intent(this,EditTodoActivity.class);
        intent.putExtra("data",data);
        startActivity(intent);
        overridePendingTransition(R.anim.in_right,R.anim.out_left);
        finish();
    }
}
