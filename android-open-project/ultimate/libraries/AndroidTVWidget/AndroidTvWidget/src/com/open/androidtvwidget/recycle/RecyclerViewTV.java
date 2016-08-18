package com.open.androidtvwidget.recycle;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.open.androidtvwidget.view.WidgetTvViewBring;

public class RecyclerViewTV extends RecyclerView {


	WidgetTvViewBring mWidgetTvViewBring;

	public RecyclerViewTV(Context context) {
		super(context);
		init();
	}

	public RecyclerViewTV(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RecyclerViewTV(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}



	private void init() {
		//RecyclerView不需要获取焦点
//		setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);//只有当其子类控件不需要获取焦点时才获取焦点
		setHasFixedSize(true);//用来使RecyclerView保持固定的大小，该信息被用于自身的优化
		setWillNotDraw(true);//如需重写onDraw,设置该值为false,这样程序会调用自定义的布局
		setFocusable(false);//不可获取焦点
		setOverScrollMode(View.OVER_SCROLL_NEVER);
		setChildrenDrawingOrderEnabled(true);//启用子视图排序功能
		mWidgetTvViewBring = new WidgetTvViewBring(this);
	}

	@Override
	public void bringChildToFront(View child) {
		mWidgetTvViewBring.bringChildToFront(this, child);
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		return mWidgetTvViewBring.getChildDrawingOrder(childCount, i);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		try {
			super.dispatchDraw(canvas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
