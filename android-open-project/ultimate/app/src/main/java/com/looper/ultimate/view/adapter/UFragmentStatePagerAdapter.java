package com.looper.ultimate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class UFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public UFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        if (mFragments == null || mFragments.isEmpty())
            return 0;
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        if (mFragments == null || mFragments.isEmpty())
            return null;
        return mFragments.get(position);
    }
}
