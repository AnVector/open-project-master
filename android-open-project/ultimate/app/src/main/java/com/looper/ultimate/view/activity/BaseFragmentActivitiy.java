package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.looper.ultimate.R;
import com.looper.ultimate.util.UActivityManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class BaseFragmentActivitiy extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        UActivityManager.getInstance().addActivity(this);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        getExtraParams();
        initData();
        initEvent();
    }

    protected abstract int getContentViewId();
    protected abstract void getExtraParams();
    protected abstract void initData();
    protected abstract void initEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UActivityManager.getInstance().removeActivity(this);
        ButterKnife.bind(this).unbind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //此切换动画应用在本Activity的退出和目的Acitity的进入
//      overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fade_out);
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
//        overridePendingTransition(R.anim.activity_slide_in_left_anim, R.anim.activity_slide_out_right_anim);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        //此切换动画应用在本Activity的退出和目的Acitity的进入
//      overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fade_out);
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
//        overridePendingTransition(R.anim.activity_slide_in_left_anim, R.anim.activity_slide_out_right_anim);
    }

    @Override
    public void finish() {
        super.finish();
        //此切换动画应用在本Activity的退出和目的Acitity的进入
//      overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fade_out);
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
//        overridePendingTransition(R.anim.activity_slide_in_left_anim, R.anim.activity_slide_out_right_anim);
    }
}
