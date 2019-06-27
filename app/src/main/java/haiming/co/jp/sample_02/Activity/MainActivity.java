package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import haiming.co.jp.sample_02.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void move_Next(View v){
        Intent intent = new Intent(this,NextActivity.class);
        intent.putExtra("test","test");
        startActivity(intent);
        overridePendingTransition(R.anim.in_right,R.anim.out_left);
    }
}
