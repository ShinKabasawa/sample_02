package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import haiming.co.jp.sample_02.Adapter.PagerAdapter;
import haiming.co.jp.sample_02.R;

public class NewToDoActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        //初期データ
        ArrayList<Integer> items = new ArrayList<Integer>();
        items.add(0);
        items.add(1);
        items.add(2);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addAll(items);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            PagerAdapter adapter = (PagerAdapter) viewPager.getAdapter();

            ArrayList<Integer> indexes = adapter.getAll();

            int currentPage = viewPager.getCurrentItem();
            if( currentPage != 0 && currentPage != indexes.size() - 1){
                //最初でも最後のページでもない場合処理を抜ける
                return;
            }

            int nextPage = 0;
            if(currentPage == 0){
                /////////////////////////////////////////////////////////
                // 最初のページに到達                                  //
                /////////////////////////////////////////////////////////
                // nextPage = 1;                                       //
                // indexes.add(0, indexes.get(0) - 1);                 //
                /////////////////////////////////////////////////////////
                // 1ページ目は既に存在するため、Fragmentを全て破棄する //
                /////////////////////////////////////////////////////////
                // adapter.destroyAllItem(viewPager);                  //
                // adapter.notifyDataSetChanged();                     //
                /////////////////////////////////////////////////////////
            }else if(currentPage == indexes.size() - 1){
                //最後のページに到達
                nextPage = currentPage;
                indexes.add(indexes.get(indexes.size() - 1) + 1);
            }
            adapter.notifyDataSetChanged();
            adapter.addAll(indexes);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(nextPage);
        }
    }
}
