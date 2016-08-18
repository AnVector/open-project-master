package com.looper.ultimate.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(),container,false);
        mContext = getActivity();
        init();
        return mRootView;
    }

    protected abstract void initData();
    protected abstract void initEvent();
    protected abstract int getContentViewId();

    protected void init(){
            ButterKnife.bind(this,mRootView);
        initData();
        initEvent();
    }

    protected String getPageName(){
        return this.getClass().getSimpleName();
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getPageName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getPageName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this,mRootView).unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
