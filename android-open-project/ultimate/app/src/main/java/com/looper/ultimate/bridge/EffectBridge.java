package com.looper.ultimate.bridge;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.looper.ultimate.R;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EffectBridge extends RecyclerViewBridge {


    public void setFocusView(View newView, View oldView, float scale, int childId, int layoutId,int id1) {
        if (newView != null) {
            View newChildView = newView.findViewById(childId);
            View newLayoutView = newView.findViewById(layoutId);
            if(newLayoutView!=null){
                newLayoutView.setVisibility(View.VISIBLE);
            }
            TextView newTv1 = (TextView) newView.findViewById(id1);
            if (newChildView != null) {
                newChildView.setBackgroundResource(R.drawable.rectangle);
                newView = newChildView;
            }
            set(newTv1);
        }

        if (oldView != null) {
            View oldChildView = oldView.findViewById(childId);
            View oldLayoutView = oldView.findViewById(layoutId);
            if(oldLayoutView!=null){
                oldLayoutView.setVisibility(View.INVISIBLE);
            }
            TextView oldTv1 = (TextView) oldView.findViewById(id1);
            if (oldChildView != null) {
                oldChildView.setBackgroundResource(0);
                oldView = oldChildView;
            }
            reset(oldTv1);
        }
        setFocusView(newView, scale);
        setUnFocusView(oldView);
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
            if(!isSelected){
                textView.setEllipsize(TextUtils.TruncateAt.END);
            }
        }
    }
}
