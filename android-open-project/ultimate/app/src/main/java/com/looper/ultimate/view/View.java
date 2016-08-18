package com.looper.ultimate.view;

import com.looper.ultimate.common.InterfaceType;

/**
 * Created by Administrator on 2016/6/20.
 */
public interface View {
    void onSuccess(String result, final int page, final InterfaceType type);

    void onFailure(String error,final int page,final InterfaceType type);
}
