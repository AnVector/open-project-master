package com.looper.ultimate.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
  
public class VerticalSeekBar extends SeekBar {  
  
    public VerticalSeekBar(Context context) {  
        super(context);  
    }  
  
    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public VerticalSeekBar(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(h, w, oldh, oldw);  
    }  
  
    @Override  
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);  
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());  
    }  
  
    protected void onDraw(Canvas c) {  
        //将SeekBar转转90度  
        c.rotate(-90);
        //将旋转后的视图移动回来  
        c.translate(-getHeight(),0);  
        Log.i("getHeight()",getHeight()+"");  
        super.onDraw(c);  
    }  
    
    public void setPosition(int position) {
    	super.setProgress(position);  
    	onSizeChanged(getWidth(), getHeight(), 0, 0);  
    }
    
    public void setThumb(int position) {
    	setProgress(position);
    	onSizeChanged(getWidth(), getHeight(), 0, 0);  
    }
  
}  