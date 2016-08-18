package com.looper.ultimate.view.adapter;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 * @param <T> Item
 * @param <V> header
 * @param <K> footer
 */
public abstract class HeaderAndFooterRecyclerViewAdapter<T, V, K> extends RecyclerViewAdapter<T> {

    //item type
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    protected int mHeaderLayoutId;
    protected List<V> mHeaderDatas;
    protected int mFooterLayoutId;
    protected List<K> mFooterDatas;

    public HeaderAndFooterRecyclerViewAdapter(Context context, int layoutId, int headerLayoutId, int footerLayoutId) {
        super(context, layoutId);
        this.mHeaderLayoutId = headerLayoutId;
        this.mFooterLayoutId = footerLayoutId;
    }

    public HeaderAndFooterRecyclerViewAdapter(Context context, int layoutId, int headerLayoutId) {
        super(context, layoutId);
        this.mHeaderLayoutId = headerLayoutId;
    }

    /**
     * @param positionStart
     * @param itemCount
     * @param headerDatas 要插入的新数据
     */
    public void addHeader(int positionStart, int itemCount, List<V> headerDatas) {
        if (headerDatas == null || headerDatas.isEmpty()) {
            return;
        }
        if(mHeaderDatas == null){
            this.mHeaderDatas = headerDatas;
        }
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void addItem(int positionStart, int itemCount, List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        if(mDatas == null){
            this.mDatas = datas;
        }
        notifyItemRangeInserted(getHeaderItemCount() + positionStart, itemCount);
    }

    public void addFooter(int positionStart, int itemCount, List<K> footerDatas) {
        if (footerDatas == null || footerDatas.isEmpty()) {
            return;
        }
        if(mFooterDatas == null){
            this.mFooterDatas = footerDatas;
        }
        notifyItemRangeInserted(getHeaderItemCount() + getCommonItemCount() + positionStart, itemCount);
    }

    public void insert(int positionStart, int itemCount,List<?> datas,int type){
        if(datas == null||datas.isEmpty()){
            return;
        }
        switch (type){
            case TYPE_HEADER:
                if(mHeaderDatas == null){
                    mHeaderDatas = (List<V>)datas;
                }
                notifyItemRangeInserted(positionStart,itemCount);
                break;
            case TYPE_ITEM:
                if(mDatas == null){
                    mDatas = (List<T>)datas;
                }
                notifyItemRangeInserted(positionStart,itemCount);
                break;
            case TYPE_FOOTER:
                if(mFooterDatas == null){
                    mFooterDatas = (List<K>)datas;
                }
                notifyItemRangeInserted(positionStart,itemCount);
                break;
            default:
                break;
        }

    }

    /**
     * @param headerDatas 要更新的数据，默认替换原有数据
     */
    public void updateHeader(List<V> headerDatas) {
        if (headerDatas == null || headerDatas.isEmpty()) {
            return;
        }
        this.mHeaderDatas = headerDatas;
        notifyItemRangeChanged(0, headerDatas.size());
    }

    public void updateItem(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        this.mDatas = datas;
        notifyItemRangeChanged(getHeaderItemCount(), datas.size());
    }

    public void updateFooter(List<K> footerDatas) {
        if (footerDatas == null || footerDatas.isEmpty()) {
            return;
        }
        this.mFooterDatas = footerDatas;
        notifyItemRangeChanged(getHeaderItemCount() + getCommonItemCount(), footerDatas.size());
    }

    //数据删除
    public void removeHeaderByCount(int positionStart, int itemCount){

        notifyItemRangeRemoved(positionStart,itemCount);
    }

    public void removeHeaderByIndex(int positionStart, int toPosition){

        notifyItemMoved(positionStart,toPosition);
    }

    @Override
    protected boolean isClickEnabled(int viewType) {
        if (viewType == TYPE_FOOTER)
            return false;
        return true;
    }

    @Override
    protected boolean isFoucusEnabled(int viewType) {
        if (viewType == TYPE_FOOTER)
            return false;
        return true;
    }

    @Override
    protected void setOnListener(final ViewGroup parent, ViewHolder viewHolder, final int viewType) {

        final int position = getPosition(viewHolder);
        final int itemViewType = viewType;
        if (isClickEnabled(viewType)) {

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        if (itemViewType == TYPE_HEADER) {
                            mOnItemClickListener.onItemClick(parent, v, mHeaderDatas.get(position), position);
                        } else {
                            mOnItemClickListener.onItemClick(parent, v, mDatas.get(position - getHeaderItemCount()), position);
                        }

                    }
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemClickListener != null) {
                        if (viewType == TYPE_HEADER) {
                            mOnItemClickListener.onItemLongClick(parent, v, mHeaderDatas.get(position), position);
                        } else {
                            mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position - getHeaderItemCount()), position);
                        }

                    }
                    return false;
                }
            });
        }

        if (isFoucusEnabled(viewType)) {
            viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (mOnItemFocusChangeListener != null) {
                        if (viewType == TYPE_HEADER) {
                            mOnItemFocusChangeListener.onItemFocusChanged(parent, v, mHeaderDatas.get(position), position, hasFocus);
                        } else {
                            mOnItemFocusChangeListener.onItemFocusChanged(parent, v, mDatas.get(position - getHeaderItemCount()), position, hasFocus);
                        }
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = 0;
        switch (viewType) {
            case TYPE_HEADER:
                layoutId = mHeaderLayoutId;
                break;
            case TYPE_ITEM:
                layoutId = mLayoutId;
                break;
            case TYPE_FOOTER:
                layoutId = mFooterLayoutId;
                break;
            default:
                break;
        }
        ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, layoutId, -1);
        setOnListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updatePosition(position);

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                setHeaderOrFooter(holder);
                convertHeader(holder, mHeaderDatas.get(position));
                break;
            case TYPE_ITEM:
                convert(holder, mDatas.get(position - getHeaderItemCount()));
                break;
            case TYPE_FOOTER:
                setHeaderOrFooter(holder);
                convertFooter(holder, mFooterDatas.get(position - (getHeaderItemCount() + getCommonItemCount())));
                break;
            default:
                break;
        }
    }

    private void setHeaderOrFooter(ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }

    public abstract void convertHeader(ViewHolder holder, V v);

    public abstract void convertFooter(ViewHolder holder, K k);

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position))
            return TYPE_HEADER;
        if (isFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    public boolean isHeader(int position) {

        return (mHeaderLayoutId != 0 && position < getHeaderItemCount());
    }

    public boolean isFooter(int position) {

        return (mFooterLayoutId != 0 && position >= getHeaderItemCount() + getCommonItemCount() && position <= getItemCount() - 1);
    }

    @Override
    public int getItemCount() {
        return getHeaderItemCount() + getCommonItemCount() + getFooterItemCount();
    }


    private int getHeaderItemCount() {

        return (mHeaderDatas == null ? 0 : mHeaderDatas.size());
    }

    private int getFooterItemCount() {
        return (mFooterDatas == null ? 0 : mFooterDatas.size());
    }

    private int getCommonItemCount() {

        return (mDatas == null ? 0 : mDatas.size());
    }


//    public enum TYPE{
//
//    }
}
