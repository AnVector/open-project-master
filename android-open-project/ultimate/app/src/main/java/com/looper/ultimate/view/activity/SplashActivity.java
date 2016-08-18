package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.looper.ultimate.R;
import com.looper.ultimate.presenter.Presenter;
import com.looper.ultimate.util.PreferencesUtils;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    //View
    @BindView(R.id.splash_img)
    ImageView mSplashImg;
    @BindView(R.id.timer_tv)
    TextView mTimerTv;
    //Presneter
    private Presenter mPresenter;

    //time left params
    private String mTimeHint;
    private byte mTimeLeft;

    //static params
    private static final int GO_HOME = 1001;
    private static final int GO_GUID = 1002;
    private static final int TIMER_TICK = 1003;
    private static final int TIMER_TICK_FINISHED = 1004;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    handleGoHome();
                    break;
                case GO_GUID:
                    handleGoGuid();
                    break;
                case TIMER_TICK:
                    handleTimerTick();
                    break;
                case TIMER_TICK_FINISHED:
                    handleTimerTickFinished();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getExtraParams() {

    }

    @Override
    protected void initData() {
//        DownloadManager.getInstance().downloadFile();
        mTimeHint = getResources().getString(R.string.timer_seconds);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = (byte) (millisUntilFinished / 1000);
                mHandler.sendEmptyMessage(TIMER_TICK);
            }

            @Override
            public void onFinish() {
                mTimeLeft = 0;
                mHandler.sendEmptyMessage(TIMER_TICK_FINISHED);
            }
        }.start();

    }

    private void handleGoHome() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    private void handleGoGuid() {
        Intent intent = new Intent(SplashActivity.this, GuidActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleTimerTick() {

        mTimerTv.setText(String.format(mTimeHint, mTimeLeft));
    }

    private void handleTimerTickFinished() {
        mTimerTv.setText(String.format(mTimeHint, mTimeLeft));
        boolean isFirstRun = PreferencesUtils.getBoolean(this, "isFirstRun", true);
        if (isFirstRun) {
            PreferencesUtils.putBoolean(this, "isFirstRun", false);
            mHandler.sendEmptyMessage(GO_GUID);
        } else {
            mHandler.sendEmptyMessage(GO_HOME);
        }
    }

    @Override
    protected void initEvent() {

        mTimerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimerTickFinished();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
