package com.looper.ultimate.bridge;

import android.view.View;
import android.view.animation.LinearInterpolator;

import com.looper.ultimate.R;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Administrator on 2016/8/16.
 */
public class SelectedAnimationBridge {

    private static final int ANIMATION_DURATION = 300;

    public static void startAnimation(View view, float scale) {

        if(view!=null){
            view.setBackgroundResource(R.drawable.rectangle);
            scaleAnimation(view,scale);
        }

    }

    public static void resetAnimation(View view) {
        if(view!=null){
            view.setBackgroundResource(0);
            resetScaleAnimation(view);
        }
    }

    private static void scaleAnimation(View view, float scale) {
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", scale);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", scale);
            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(ANIMATION_DURATION);
            animSet.setInterpolator(new LinearInterpolator());
            animSet.playTogether(anim1, anim2);
            animSet.start();
    }

    private static void resetScaleAnimation(View view){
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1);
            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(ANIMATION_DURATION);
            animSet.setInterpolator(new LinearInterpolator());
            animSet.playTogether(anim1, anim2);
            animSet.start();
    }
}
