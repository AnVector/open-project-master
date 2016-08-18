package com.looper.ultimate.view.adapter;

import android.content.Context;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;

/**
 * Created by Administrator on 2016/8/7.
 */
public class SubChannelCategoryAdapter extends RecyclerViewAdapter<CatalogInfoBean.CatalogInfo> {

    public SubChannelCategoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, CatalogInfoBean.CatalogInfo catalogInfo) {
        holder.setText(R.id.channel_category_tv,catalogInfo.getCatalogName());
    }
}
