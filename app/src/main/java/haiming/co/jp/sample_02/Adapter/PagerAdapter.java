package haiming.co.jp.sample_02.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import haiming.co.jp.sample_02.Fragment.PagerFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Integer> mIndexes = new ArrayList<Integer>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", mIndexes.get(position));

        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return mIndexes.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    //public void destroyAllItem(ViewPager pager) {
    //    for (int i = 0; i < getCount() - 1; i++) {
    //        try {
    //            Object obj = this.instantiateItem(pager, i);
    //            if (obj != null)
    //                destroyItem(pager, i, obj);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //}

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

        /////////////////////////////////////////////////////////////////////////////
        // if (position <= getCount()) {                                           //
        //    FragmentManager manager = ((Fragment) object).getFragmentManager();  //
        //    FragmentTransaction trans = manager.beginTransaction();              //
        //    trans.remove((Fragment) object);                                     //
        //    trans.commit();                                                      //
        // }                                                                       //
        /////////////////////////////////////////////////////////////////////////////
    }

    public void addAll(ArrayList<Integer> indexes) {
        mIndexes = indexes;
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getAll() {
        return mIndexes;
    }

}
