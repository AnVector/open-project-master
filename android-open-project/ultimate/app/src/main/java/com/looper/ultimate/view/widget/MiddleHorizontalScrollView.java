package com.looper.ultimate.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.open.androidtvwidget.view.SmoothHorizontalScrollView;

/**
 * Created by Administrator on 2016/8/4.
 */
public class MiddleHorizontalScrollView extends SmoothHorizontalScrollView {

    public MiddleHorizontalScrollView(Context context) {
        super(context);
    }

    public MiddleHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MiddleHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (getChildCount() == 0)
            return 0;
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        int middleXPoint = metric.widthPixels / 2; // 屏幕宽度（像素）
        int width = getWidth();//子View显示的宽度
        int rectWidth = rect.width();//获取焦点的View的宽度
        int screenLeft = getScrollX();//子View起始横坐标
        int screenRight = screenLeft + width;//子View最终横坐标
        //relative middle position
        int rlMiddleXPoint = screenLeft + middleXPoint;

        int fadingEdge = 0;
        // leave room for left fading edge as long as rect isn't at very left
        if (rect.left > 0) {//获取焦点的View的起始横坐标
            screenLeft += fadingEdge;
        }
        // leave room for right fading edge as long as rect isn't at very right
        if (rect.right < getChildAt(0).getWidth()) {//获取焦点的View的最终横坐标
            screenRight -= fadingEdge;
        }
        int scrollXDelta = 0;
        int viewMiddleXPoint = rect.left + rectWidth / 2;

        //若View的中线超过屏幕的中线，移动该View使该View的中线与屏幕的中线重合
        if (viewMiddleXPoint >= rlMiddleXPoint) {//获取焦点的View中线位于屏幕中线右侧，即向右滑动
            // need to move right to get it in view: move right just enough so
            // that the entire rectangle is in view (or at least the first
            // screen size chunk).
            if (rect.width() > width) {//暂未处理
                // just enough to get screen size chunk on
                scrollXDelta += (rect.left - screenLeft);
            } else {//获取X方向移动的Delta值
                // get entire rect at right of screen
                scrollXDelta += (viewMiddleXPoint - rlMiddleXPoint);
            }
            // make sure we aren't scrolling beyond the end of our content
            int right = getChildAt(0).getRight();
            int distanceToRight = right - screenRight;
            scrollXDelta = Math.min(scrollXDelta, distanceToRight);

        } else {//获取焦点的View中线位于屏幕中线左侧，即向左滑动
            // need to move right to get it in view: move right just enough so
            // that
            // entire rectangle is in view (or at least the first screen
            // size chunk of it).
            if (rect.width() > width) {//暂未处理
                // screen size chunk
                scrollXDelta -= (screenRight - rect.right);
            } else {
                // entire rect at left
                scrollXDelta -= (rlMiddleXPoint - viewMiddleXPoint);
            }
            // make sure we aren't scrolling any further than the left our
            // content
            scrollXDelta = Math.max(scrollXDelta, -getScrollX());
        }
//        if (scrollXDelta >= 0) {
//            Log.d("width", "向右平移" + scrollXDelta + "个像素");
//        } else {
//            Log.d("width", "向左平移" + scrollXDelta + "个像素");
//        }
        return scrollXDelta;
    }
}
