package com.looper.ultimate.bridge;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.looper.ultimate.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EffectBridge{

    private int scaleViewId;
    private int visibleViewId;
    private int textViewId;
    private float scaleTimes = 1.0f;

    public EffectBridge(int scaleViewId,int visibleViewId,int textViewId,float scaleTimes){
        this.scaleViewId = scaleViewId;
        this.visibleViewId = visibleViewId;
        this.textViewId = textViewId;
        this.scaleTimes = scaleTimes;
    }

    public void setViewAnimation(View targetView,boolean hasFocus){

        if(targetView!=null) {
            if (targetView.findViewById(scaleViewId) == null ) {
                return;
            }
            if (hasFocus) {
                scaleAnimation(targetView.findViewById(scaleViewId),scaleTimes);
                targetView.findViewById(scaleViewId).setBackgroundResource(R.drawable.rectangle);
                if(targetView.findViewById(visibleViewId) != null){
                    targetView.findViewById(visibleViewId).setVisibility(View.VISIBLE);
                }
                if(targetView.findViewById(textViewId) != null){
                    set((TextView) targetView.findViewById(textViewId));
                }

            } else {
                scaleAnimation(targetView.findViewById(scaleViewId),1.0f);
                targetView.findViewById(scaleViewId).setBackgroundResource(0);
                if( targetView.findViewById(visibleViewId) != null){
                    targetView.findViewById(visibleViewId).setVisibility(View.GONE);
                }
                if(targetView.findViewById(textViewId) != null){
                    reset((TextView) targetView.findViewById(textViewId));
                }
            }
        }
    }
    private void reset(TextView titleTv) {
        setTextColor(titleTv, Color.GRAY);
        setTextMarquee(titleTv,false);
    }

    private void set(TextView titleTv) {
        setTextColor(titleTv, Color.WHITE);
        setTextMarquee(titleTv,true);
    }

    private void setTextColor(TextView textView, int color) {
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    private void setTextMarquee(TextView textView,boolean isSelected){
        if(textView!=null){
            textView.setSelected(isSelected);
        }
    }

    private void scaleAnimation(View view,float scale){
        if(view!=null){
            ViewHelper.setScaleX(view,scale);
            ViewHelper.setScaleY(view,scale);
        }
    }
}
