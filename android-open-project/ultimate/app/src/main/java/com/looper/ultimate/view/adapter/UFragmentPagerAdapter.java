package com.looper.ultimate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class UFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    public UFragmentPagerAdapter(FragmentManager fm,List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragments == null||mFragments.isEmpty())
            return null;
        return
                mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments == null||mFragments.isEmpty())
            return 0;
        return mFragments.size();
    }
}
