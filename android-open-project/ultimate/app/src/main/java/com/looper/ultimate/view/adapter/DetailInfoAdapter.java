package com.looper.ultimate.view.adapter;

import android.content.Context;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.bean.ContentInfoBean;
import com.looper.ultimate.common.AppConstant;

/**
 * Created by Administrator on 2016/7/25.
 */
public class DetailInfoAdapter extends HeaderAndFooterRecyclerViewAdapter<ContentInfoBean, CatalogInfoBean.CatalogInfo, String> {

    public DetailInfoAdapter(Context context, int layoutId, int headerLayoutId) {
        super(context, layoutId, headerLayoutId);
    }

    public DetailInfoAdapter(Context context, int layoutId, int headerLayoutId, int footerLayoutId) {
        super(context, layoutId, headerLayoutId, footerLayoutId);
    }

    @Override
    public void convertHeader(ViewHolder holder, CatalogInfoBean.CatalogInfo catalogInfo) {
        holder.displayImage(R.id.scale_zone,catalogInfo.getCoverImages().get(0).getImageUrl(),R.drawable.default7,6);
    }

    @Override
    public void convertFooter(ViewHolder holder, String s) {

        holder.setText(R.id.footer_tv, s);
    }

    @Override
    public void convert(ViewHolder holder, ContentInfoBean contentInfoBean) {
        holder.setText(R.id.item_study_count,""+contentInfoBean.getContentExtInfo().getStudyCount());
        holder.setText(R.id.item_title, contentInfoBean.getContentName());
        holder.displayImage(R.id.item_logo, AppConstant.PREFIX_URL + contentInfoBean.getMiddleLogo(), R.drawable.default5, 6);
    }
}
