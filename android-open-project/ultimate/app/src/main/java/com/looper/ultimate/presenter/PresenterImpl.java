package com.looper.ultimate.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.looper.ultimate.view.View;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/6/20.
 */
public abstract class PresenterImpl implements Presenter {

    protected Reference<View> mView;//View层
    protected Reference<Context> mContext;//异步数据请求
    protected CompositeSubscription mCompositeSubscription;

    public PresenterImpl(View view) {
        attachView(view);
        if (view instanceof Context) {
            attachContext((Context) view);
        }

        if (view instanceof Fragment) {
            attachContext(((Fragment) view).getContext());
        }
        mCompositeSubscription = new CompositeSubscription();
    }

    public PresenterImpl(View view, Context context) {
        attachView(view);
        attachContext(context);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public boolean isViewAttached() {

        return mView != null && mView.get() != null;
    }

    @Override
    public boolean isContextAttached() {
        return mContext != null && mContext.get() != null;
    }

    @Override
    public void attachView(View view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void attachContext(Context context) {
        mContext = new SoftReference<>(context);
    }


    @Override
    public View getView() {
        return mView.get();
    }

    @Override
    public Context getContext() {
        return mContext.get();
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public void detachContext() {
        if (mContext != null) {
            mContext.clear();
            mContext = null;
        }
    }

    @Override
    public boolean isSubscriptionAttached() {
        return (mCompositeSubscription != null);
    }

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }

    }
}
