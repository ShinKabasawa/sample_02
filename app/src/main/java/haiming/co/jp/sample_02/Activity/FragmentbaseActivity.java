package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import haiming.co.jp.sample_02.DialogFragment.sampleDialogFragment;
import haiming.co.jp.sample_02.Fragment.Tab1Fragment;
import haiming.co.jp.sample_02.Fragment.Tab2Fragment;
import haiming.co.jp.sample_02.Fragment.Tab3Fragment;
import haiming.co.jp.sample_02.R;

public class FragmentbaseActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private TabHost tabHost;
    private String mLastTabId = "tab1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentbase);
        Log.v("FragmentbaseAct","onCreate");
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        /* Tab1 設定 */
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("TAB1");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        // Tab2 設定
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("TAB2");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        // Tab3 設定
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("TAB3");
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab3);
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
        mLastTabId = "tab1";

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.realtabcontent, new Tab1Fragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v("FragmentbaseAct","onStart");
    }

    public void dialog_btn(View v){
        sampleDialogFragment sampleDialogFragment = new sampleDialogFragment();
        sampleDialogFragment.show(getSupportFragmentManager(),"Sample");

    }

    public void onbtn_onClick(View v){
        //frameLayout.setVisibility(View.VISIBLE);
    }

    public void hide_onClick(View v){
        //frameLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTabChanged(String tabId) {

        if (mLastTabId != null) {
            if (!mLastTabId.equals(tabId)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if ("tab1".equals(tabId)) {
                    fragmentTransaction.replace(R.id.realtabcontent, new Tab1Fragment());
                    Log.d("TAB_FRAGMENT_LOG","tabId:" + tabId);
                } else if ("tab2".equals(tabId)) {
                    fragmentTransaction.replace(R.id.realtabcontent, new Tab2Fragment());
                    Log.d("TAB_FRAGMENT_LOG","tabId:" + tabId);
                } else if ("tab3".equals(tabId)) {
                    fragmentTransaction.replace(R.id.realtabcontent, new Tab3Fragment());
                    Log.d("TAB_FRAGMENT_LOG","tabId:" + tabId);
                }
                mLastTabId = tabId;
                fragmentTransaction.commit();
            }
        }
    }

}
