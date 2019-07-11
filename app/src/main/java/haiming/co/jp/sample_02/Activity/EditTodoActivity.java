package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import haiming.co.jp.sample_02.R;

public class EditTodoActivity extends AppCompatActivity {

    private EditText title_edit;
    private EditText content_edit;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        Intent intent = getIntent();
        data = intent.getStringArrayExtra("data");

        title_edit = (EditText)findViewById(R.id.title_edit);
        content_edit = (EditText)findViewById(R.id.content_edit);

        title_edit.setText(data[0]);
        content_edit.setText(data[1]);
    }


    public void onClick_edit_fin(View v){
        // DB更新
        Intent intent = new Intent(this,TodoDetailActivity.class);
        data[0] = title_edit.getText().toString().trim();
        data[1] = content_edit.getText().toString().trim();
        intent.putExtra("data",data);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.in_left,R.anim.out_right);
    }





}
