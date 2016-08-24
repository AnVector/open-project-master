package com.looper.ultimate.view.widget.ViewPagerTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2016/8/22.
 */
public class VerticalPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            ViewHelper.setAlpha(page,0);
        } else if (position <= 1) {
            ViewHelper.setAlpha(page,1-position);
            page.setTranslationX(page.getWidth() * -position);
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);
        } else {
            ViewHelper.setAlpha(page,0);
        }
    }
}
