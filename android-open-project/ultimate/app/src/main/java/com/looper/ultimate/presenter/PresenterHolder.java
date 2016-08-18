package com.looper.ultimate.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.looper.ultimate.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by administrator on 21/6/16.
 */
public class PresenterHolder {

    private static volatile PresenterHolder singleton = null;

    private Map<View, Presenter> presenterMap;

    //单例模式 最佳实践1
    //单例模式 最佳实践2 静态内部类
    //单例模式最佳实践3 枚举
    public static PresenterHolder getInstance() {
        if (singleton == null) {
            synchronized (PresenterHolder.class) {
                if (singleton == null) {
                    singleton = new PresenterHolder();
                }
            }
        }
        return singleton;
    }

    private PresenterHolder() {
        this.presenterMap = new HashMap<>();
    }

    private void putPresenter(View v, Presenter p) {
        presenterMap.put(v, p);
    }

    public <T extends Presenter> T createPresenter(View v) {
        Presenter mPresenter = null;
        mPresenter = presenterMap.get(v);
        if (mPresenter == null) {
            if(v instanceof Activity){
                mPresenter = new ActivityPresenter(v);
            }
            if(v instanceof Fragment){
                mPresenter = new FragmentPresenter(v);
            }
            putPresenter(v, mPresenter);
        }
        return (T) mPresenter;
    }

    public <T extends Presenter> T createPresenter(View v, Context context) {
        Presenter mPresenter = null;
        mPresenter = presenterMap.get(v);
        if (mPresenter == null) {
            mPresenter = new FragmentPresenter(v, context);
            putPresenter(v, mPresenter);
        }
        return (T) mPresenter;
    }

    public void remove(View v) {

        if (presenterMap.containsKey(v)) {
            Presenter mPresenter = presenterMap.get(v);
            presenterMap.remove(v);
            if (mPresenter.isViewAttached()) {
                mPresenter.detachView();
            }
            if (mPresenter.isContextAttached()) {
                mPresenter.detachContext();
            }
            if(mPresenter.isSubscriptionAttached()){
                mPresenter.unSubscribe();
            }
        }

    }
}