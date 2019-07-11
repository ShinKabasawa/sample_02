package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import haiming.co.jp.sample_02.R;

public class TodoRegistActivity extends AppCompatActivity {
    EditText title_edit_txt;
    EditText content_edit_txt;

    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_regist);
        title_edit_txt = (EditText)findViewById(R.id.title_edit);
        content_edit_txt = (EditText)findViewById(R.id.content_edit);
    }

    @Override
    public void onStart(){
        super.onStart();

        init();
    }

    public void onClick_Regist(View v){
        title = title_edit_txt.getText().toString();
        content = content_edit_txt.getText().toString();

        if (!title.equals("") && content.equals("")){
            // Todoの登録
        }

        // TodoのDB登録完了後、EditTextの値初期化
        init();
    }

    private void init(){
        title = "";
        content = "";
        title_edit_txt.setText(title);
        content_edit_txt.setText(content);
    }
}
