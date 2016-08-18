package com.looper.ultimate.bridge;

import android.view.View;

import com.open.androidtvwidget.bridge.OpenEffectBridge;

/**
 * Created by Administrator on 2016/8/12.
 */
public class HomeEffectBridge extends OpenEffectBridge {

    @Override
    public void onFocusView(View focusView, float scaleX, float scaleY) {
        super.onFocusView(focusView, scaleX, scaleY);
        if (focusView != null) {
//            focusView.bringToFront();
//            focusView.setBackgroundResource(R.drawable.rectangle);
        }
    }

    @Override
    public void onOldFocusView(View oldFocusView, float scaleX, float scaleY) {
        super.onOldFocusView(oldFocusView, scaleX, scaleY);
        if (oldFocusView != null) {
//            oldFocusView.setBackgroundResource(0);

        }
    }
}
