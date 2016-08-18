package com.looper.ultimate.presenter;

import android.content.Context;

import com.looper.ultimate.view.View;

/**
 * Created by Administrator on 2016/6/20.
 */
public interface Presenter {

    void attachView(View view);
    void attachContext(Context context);
    View getView();
    Context getContext();
    boolean isViewAttached();
    void detachContext();
    boolean isContextAttached();
    void detachView();
    boolean isSubscriptionAttached();
    void unSubscribe();
}