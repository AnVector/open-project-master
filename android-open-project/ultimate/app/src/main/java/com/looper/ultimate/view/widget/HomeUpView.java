package com.looper.ultimate.view.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.looper.ultimate.bridge.HomeEffectBridge;
import com.open.androidtvwidget.bridge.BaseEffectBridge;
import com.open.androidtvwidget.view.MainUpView;

/**
 * Created by Administrator on 2016/8/12.
 */
public class HomeUpView  extends MainUpView{

    public HomeUpView(Context context) {
        super(context);
    }

    public HomeUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initEffectBridge() {
//        super.initEffectBridge();
        BaseEffectBridge baseEffectBridge = new HomeEffectBridge();
        baseEffectBridge.onInitBridge(this);
        baseEffectBridge.setMainUpView(this);
        setEffectBridge(baseEffectBridge);
    }
}
