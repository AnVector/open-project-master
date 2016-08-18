package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.looper.ultimate.R;
import com.looper.ultimate.util.UActivityManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG_NAME = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract int getContentViewId();

    protected abstract void getExtraParams();

    protected abstract void initData();

    protected abstract void initEvent();

    protected void init() {
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        UActivityManager.getInstance().addActivity(this);
        getExtraParams();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UActivityManager.getInstance().removeActivity(this);
        ButterKnife.bind(this).unbind();
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
    public void finish() {
        super.finish();
//              overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fade_out);
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
//        overridePendingTransition(R.anim.activity_slide_in_left_anim, R.anim.activity_slide_out_right_anim);
    }
}
