package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.sampleProgress;

/**
 * 非同期処理（ProgressBar）
 */
public class ProgressActivity extends AppCompatActivity {

    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        progressbar = (ProgressBar)findViewById(R.id.progressBar_test);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    // 開始ボタンクリック処理
    public void start_click(View v){
        sampleProgress sampleProgress = new sampleProgress(1000,progressbar,this);
        sampleProgress.execute();
    }
}
