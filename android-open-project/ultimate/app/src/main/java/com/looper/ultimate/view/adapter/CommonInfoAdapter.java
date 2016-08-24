package com.looper.ultimate.view.adapter;

import android.content.Context;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.ContentInfoBean;
import com.looper.ultimate.common.AppConstant;

/**
 * Created by Administrator on 2016/8/24.
 */
public class CommonInfoAdapter extends RecyclerViewAdapter<ContentInfoBean> {

    public CommonInfoAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ContentInfoBean contentInfoBean) {
        if("1".equals(contentInfoBean.getChargeFlag())){
            holder.setVisible(R.id.vip_flag,true);
        }
        holder.setText(R.id.item_study_count,""+contentInfoBean.getContentExtInfo().getStudyCount());
        holder.setText(R.id.item_title, contentInfoBean.getContentName());
        holder.displayImage(R.id.item_logo, AppConstant.PREFIX_URL + contentInfoBean.getMiddleLogo(), R.drawable.default5, 6);
    }
}
