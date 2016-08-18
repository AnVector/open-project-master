package com.open.androidtvwidget.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.open.androidtvwidget.R;

public class SmoothHorizontalScrollView extends HorizontalScrollView {
	final String TAG = "SmoothHorizontalScrollView";

	public SmoothHorizontalScrollView(Context context) {
		this(context, null, 0);
	}

	public SmoothHorizontalScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SmoothHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		if (getChildCount() == 0)
			return 0;
		int width = getWidth();//子View显示的宽度
		int rectWidth = rect.width();//获取焦点的View的宽度
		Log.d("width","viewWith="+width);
		Log.d("width","rectWith="+rectWidth);
		int screenLeft = getScrollX();//子View起始横坐标
		Log.d("width","screenLeft="+screenLeft);
		int screenRight = screenLeft + width;//子View最终横坐标
		Log.d("width","screenRight="+screenRight);

		int fadingEdge = this.getResources().getDimensionPixelSize(
				R.dimen.fading_edge);

		// leave room for left fading edge as long as rect isn't at very left
		Log.d("width","rect.left="+rect.left);
		if (rect.left > 0) {//获取焦点的View的起始横坐标
			screenLeft += fadingEdge;
		}
		Log.d("width","rect.right="+rect.right);
		// leave room for right fading edge as long as rect isn't at very right
		if (rect.right < getChildAt(0).getWidth()) {//获取焦点的View的最终横坐标
			screenRight -= fadingEdge;
		}

		int scrollXDelta = 0;

		if (rect.right > screenRight && rect.left > screenLeft) {//获取焦点的View超出屏幕右侧范围
			// need to move right to get it in view: move right just enough so
			// that the entire rectangle is in view (or at least the first
			// screen size chunk).

			if (rect.width() > width) {
				// just enough to get screen size chunk on
				scrollXDelta += (rect.left - screenLeft);
			} else {
				// get entire rect at right of screen
				scrollXDelta += (rect.right - screenRight);
			}

			// make sure we aren't scrolling beyond the end of our content
			int right = getChildAt(0).getRight();
			int distanceToRight = right - screenRight;
			scrollXDelta = Math.min(scrollXDelta, distanceToRight);

		} else if (rect.left < screenLeft && rect.right < screenRight) {//获取焦点的View超出屏幕左侧范围
			// need to move right to get it in view: move right just enough so
			// that
			// entire rectangle is in view (or at least the first screen
			// size chunk of it).

			if (rect.width() > width) {
				// screen size chunk
				scrollXDelta -= (screenRight - rect.right);
			} else {
				// entire rect at left
				scrollXDelta -= (screenLeft - rect.left);
			}

			// make sure we aren't scrolling any further than the left our
			// content
			scrollXDelta = Math.max(scrollXDelta, -getScrollX());
		}
		return scrollXDelta;
	}
	
}
