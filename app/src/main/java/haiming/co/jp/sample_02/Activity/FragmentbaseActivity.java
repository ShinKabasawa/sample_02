package haiming.co.jp.sample_02.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import haiming.co.jp.sample_02.DialogFragment.sampleDialogFragment;
import haiming.co.jp.sample_02.Fragment.Tab1Fragment;
import haiming.co.jp.sample_02.Fragment.Tab2Fragment;
import haiming.co.jp.sample_02.Fragment.Tab3Fragment;
import haiming.co.jp.sample_02.R;

public class FragmentbaseActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private FrameLayout frameLayout;
    private TabHost tabHost;
    private String mLastTabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentbase);
        Log.v("FragmentbaseAct","onCreate");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v("FragmentbaseAct","onStart");

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        /* Tab1 設定 */
        TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("TAB1");
        tab1.setContent(new DummyTabFactory(this));
        tabHost.addTab(tab1);

        // Tab2 設定
        TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("TAB2");
        tab2.setContent(new DummyTabFactory(this));
        tabHost.addTab(tab2);

        // Tab3 設定
        TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("TAB3");
        tab3.setContent(new DummyTabFactory(this));
        tabHost.addTab(tab3);

        // タブ変更時イベントハンドラ
        tabHost.setOnTabChangedListener(this);
        mLastTabId = "tab1";
        // 初期タブ選択

        tabHost.setCurrentTabByTag("tab1");
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


    /*
     * android:id/tabcontent のダミーコンテンツ
     */
    private static class DummyTabFactory implements TabHost.TabContentFactory {

        /* Context */
        private final Context mContext;

        DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            return v;
        }
    }
}
