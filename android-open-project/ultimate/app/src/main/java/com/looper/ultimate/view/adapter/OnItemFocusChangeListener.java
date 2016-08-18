package com.looper.ultimate.view.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/25.
 */
public interface OnItemFocusChangeListener<T> {

    void onItemFocusChanged(ViewGroup parent, View view, T t, int position,boolean hasFocus);

}
