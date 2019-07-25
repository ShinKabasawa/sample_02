package haiming.co.jp.sample_02.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import haiming.co.jp.sample_02.Dao.DaoTodo;
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
        title = title_edit_txt.getText().toString().trim();
        content = content_edit_txt.getText().toString().trim();

        boolean flg = false;

        if (!title.equals("") && !content.equals("")){
            // Todoの登録
            DaoTodo daoTodo = new DaoTodo(this,"",null,1);
            Calendar cal = Calendar.getInstance();
            Date date_ = cal.getTime();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = simpleDateFormat.format(date_);
            Log.v("#$#$#$","DATE" + date);
            flg = daoTodo.add_todo(title,content,date,0);
        }

        if (flg) {
            // TodoのDB登録完了後、EditTextの値初期化
            init();
        }else{
            // Todo登録失敗した場合、EditTextの初期化を行わない
        }
    }

    // 初期化
    private void init(){
        title = "";
        content = "";
        title_edit_txt.setText(title);
        content_edit_txt.setText(content);
    }
}
