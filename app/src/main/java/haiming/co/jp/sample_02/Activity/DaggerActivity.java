package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import haiming.co.jp.sample_02.Interface.GreetingService;
import haiming.co.jp.sample_02.R;

public class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        Log.v("DaggerActivity","onCreate");
        GreetingService.GreetingCompornent compornent = haiming.co.jp.sample_02.Interface.DaggerGreetingService_GreetingCompornent.builder().build();
        compornent.greet().print("令和");
    }
}
