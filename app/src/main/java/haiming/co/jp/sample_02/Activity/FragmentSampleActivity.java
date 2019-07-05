package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import haiming.co.jp.sample_02.Fragment.Fragment1;
import haiming.co.jp.sample_02.R;

public class FragmentSampleActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample);
    }

    @Override
    protected void onStart(){
        super.onStart();
        frameLayout = (FrameLayout)findViewById(R.id.fream);
    }


    // 表示ボタンクリック時処理
    public void vis_btn_onClick(View v){
        frameLayout.setVisibility(View.VISIBLE);
    }

    // 非表示ボタンクリック時処理
    public void invis_btn_onClick(View v){
        frameLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFragmentInteraction(String uri) {
        Log.v("FragmentSampleActivity","onFragmentInteraction");
    }
}
