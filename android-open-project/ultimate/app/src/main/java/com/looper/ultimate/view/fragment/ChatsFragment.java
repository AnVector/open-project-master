package com.looper.ultimate.view.fragment;

import com.looper.ultimate.R;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.view.ViewImpl;

/**
 * Created by Administrator on 2016/8/15.
 */
public class ChatsFragment extends BaseFragment implements ViewImpl {

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_chats;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {

    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }
}
