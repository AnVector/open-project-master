package com.looper.ultimate.view.adapter;

import android.content.Context;

import com.looper.ultimate.R;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ChannelCategoryAdapter extends RecyclerViewAdapter<Map<String,Integer>> {

    public ChannelCategoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Map<String, Integer> stringIntegerMap) {
        Iterator iterator = stringIntegerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry = (java.util.Map.Entry)iterator.next();
            holder.setText(R.id.channel_category_tv,entry.getKey());
            holder.setCompoundDrawables(R.id.channel_category_tv,entry.getValue(),10);
        }

    }


//    @Override
//    public void convert(ViewHolder holder, CatalogInfoBean.CatalogInfo catalogInfo) {
//        holder.setText(R.id.channel_category_tv,catalogInfo.getCatalogName());
//    }
}
