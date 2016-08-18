package com.looper.ultimate.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/8.
 */
public class ImageViewWithText extends ImageView{

    private Paint mPaint;
    private RectF mRectF;

    public ImageViewWithText(Context context) {
        super(context);
        init(context,null);
    }

    public ImageViewWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public ImageViewWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    private void init(Context context,AttributeSet attrs){

        if(null!=attrs){

        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectF.top = 0;
        mRectF.left = 0;
        Drawable mDrawable = getDrawable();
        if(null!=mDrawable){
            mRectF.right = mDrawable.getIntrinsicWidth();
            mRectF.bottom = mDrawable.getIntrinsicHeight();
        }
        getImageMatrix().mapRect(mRectF);

//        canvas.drawText("",);
    }
}
