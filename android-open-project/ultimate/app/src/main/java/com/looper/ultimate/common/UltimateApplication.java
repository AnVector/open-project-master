package com.looper.ultimate.common;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.looper.ultimate.BuildConfig;
import com.looper.ultimate.util.LogUtils;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/20.
 */
public class UltimateApplication extends MultiDexApplication {

    private static UltimateApplication instance = null;

    //Application的构造方法不能私有化
    public UltimateApplication() {
    }

    public static Thread getMainThread() {
        return Thread.currentThread();
    }

    public static Long getMainThreadId() {
        return getMainThread().getId();
    }

    public static Handler getMainThreadHandler() {
        return new Handler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (UltimateApplication) getApplicationContext();//初始化Application
        initUmengAnalysis();//配置友盟统计
        LeakCanary.install(this);//配置LeakCanary
        ButterKnife.setDebug(BuildConfig.DEBUG);
        LogUtils.setDebugMode(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initUmengAnalysis() {
        MobclickAgent.setCatchUncaughtExceptions(true);
        // MobclickAgent.openActivityDurationTrack(true);
        //设置APP KEY
/*        MobclickAgent.UMAnalyticsConfig umAnalyticsConfig = new MobclickAgent.UMAnalyticsConfig(this,"appkey","channel",MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.startWithConfigure(umAnalyticsConfig);*/
        //设置Secret Key
        MobclickAgent.setSecret(this, "secretkey");

    }

    public static UltimateApplication getInstance() {
        return instance;
    }
}
