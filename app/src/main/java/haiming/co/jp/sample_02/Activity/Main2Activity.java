package haiming.co.jp.sample_02.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import haiming.co.jp.sample_02.Interface.AsyncTaskCallback;
import haiming.co.jp.sample_02.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ///////////////////////////////////////////
        // Common.arrayList_1 = new ArrayList(); //
        // Common.arrayList_2 = new ArrayList(); //
        // Common.arrayList_3 = new ArrayList(); //
        // Common.arrayList_4 = new ArrayList(); //
        // Common.arrayList_5 = new ArrayList(); //
        ///////////////////////////////////////////

        final int[] cnt = {0};

        AsyncTaskCallback callback = new AsyncTaskCallback() {
            @Override
            public void onTaskCompleted(String result) {
                cnt[0]++;

                if  (cnt[0] == 5){
                    Intent intnet = new Intent(getApplicationContext(),WeatherActivity.class);
                    startActivity(intnet);
                    finish();
                    overridePendingTransition(R.anim.in_right,R.anim.out_left);
                }
            }
        };



    }
}
