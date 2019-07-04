package haiming.co.jp.sample_02.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import haiming.co.jp.sample_02.DialogFragment.sampleDialogFragment;
import haiming.co.jp.sample_02.Fragment.Fragment1;
import haiming.co.jp.sample_02.R;

public class FragmentbaseActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private View fragment;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentbase);
    }

    @Override
    protected void onStart(){
        super.onStart();
        frameLayout = (FrameLayout)findViewById(R.id.framelayout);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void dialog_btn(View v){
        sampleDialogFragment sampleDialogFragment = new sampleDialogFragment();
        sampleDialogFragment.show(getSupportFragmentManager(),"Sample");
    }

    public void onbtn_onClick(View v){
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void hide_onClick(View v){
        frameLayout.setVisibility(View.INVISIBLE);
    }
}
