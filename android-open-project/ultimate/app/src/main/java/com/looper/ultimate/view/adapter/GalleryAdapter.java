package com.looper.ultimate.view.adapter;

import android.content.Context;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.Gallery;
import com.looper.ultimate.common.AppConstant;

/**
 * Created by Administrator on 2016/7/5.
 */
public class GalleryAdapter extends RecyclerViewAdapter<Gallery> {

    public GalleryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Gallery gallery) {
        holder.displayImage(R.id.content_pic, AppConstant.TNGOUAPI+gallery.getImg(),R.drawable.ic_launcher,0);
        holder.setText(R.id.content_txt,gallery.getTitle());
        holder.setText(R.id.like_count_tv,""+gallery.getCount());
        holder.setText(R.id.comment_count_tv,""+gallery.getFcount());
        holder.setText(R.id.nick_name_tv,""+gallery.getTitle());
        holder.setText(R.id.release_time_tv,"6月27日 09:51");
    }
}
