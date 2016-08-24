package com.looper.ultimate.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.presenter.ActivityPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.OnItemFocusChangeListener;
import com.looper.ultimate.view.adapter.SubChannelCategoryAdapter;
import com.looper.ultimate.view.adapter.UFragmentStatePagerAdapter;
import com.looper.ultimate.view.fragment.ChannelFragment;
import com.looper.ultimate.view.widget.ViewPagerTransformer.VerticalPageTransformer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ChannelFragmentActivity extends BaseFragmentActivitiy implements ViewImpl, ChannelFragment.FragmentListener {

    @BindView(R.id.btn_search)
    TextView mBtnSearch;
    @BindView(R.id.channel_category_rv)
    RecyclerView mChannelCategoryRv;
    @BindView(R.id.tv_icon)
    TextView mTvIcon;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    public static final String CATALOG_ID = "catalog_id";
    public static final String RECOMMEND_ID = "recommend_id";
    @BindView(R.id.left_ll)
    LinearLayout mLeftLl;
    private ActivityPresenter mPresenter;
    //adapter
    private SubChannelCategoryAdapter mSubChannelCategoryAdapter;
    private UFragmentStatePagerAdapter mFragmentStatePagerAdapter;
    //channel fragment list
    private List<Fragment> mFragmentList = new ArrayList();
    //channel fragment map
    private Map<Integer, Fragment> mFragmentMap = new HashMap<>();
    //extra params
    private String catalogId;
    private String recommendId;

    //key event control
    private boolean left = false;
    private boolean right = false;
    private boolean top = false;
    private boolean bottom = false;
    private boolean focused = false;
    //fragment index
    private int index;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_channel_fragment;
    }

    @Override
    protected void getExtraParams() {

        catalogId = getIntent().getStringExtra(CATALOG_ID);
        recommendId = getIntent().getStringExtra(RECOMMEND_ID);
        if (catalogId == null) {
            catalogId = "560139";
        }
        if (recommendId == null) {
            recommendId = "560314";
        }
    }

    @Override
    protected void initData() {
        mSubChannelCategoryAdapter = new SubChannelCategoryAdapter(this, R.layout.sub_channel_category_item);
        mChannelCategoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChannelCategoryRv.setHasFixedSize(true);
        initViewPager();
        mPresenter = PresenterHolder.getInstance().createPresenter(this);
        fetchCatalogInfo();
    }

    private void fetchCatalogInfo() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", recommendId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.VolleyRequestWithAuth(json, "subChannelInfo", -1, InterfaceType.getCatalogInfo);
    }

    @Override
    protected void initEvent() {

        mSubChannelCategoryAdapter.setOnItemFocusChangeListener(new OnItemFocusChangeListener() {
            @Override
            public void onItemFocusChanged(ViewGroup parent, View view, Object o, int position, boolean hasFocus) {
                if (hasFocus) {
                    focused = true;
                    index = position;
                    view.bringToFront();
                    view.setBackgroundResource(R.drawable.round_corner_rectangle);
                    ((TextView) view.findViewById(R.id.channel_category_tv)).setTextColor(getResources().getColor(R.color.white));
                    if (o instanceof CatalogInfoBean.CatalogInfo) {
                        String catalogId = ((CatalogInfoBean.CatalogInfo) o).getCatalogId();
                        if (!mFragmentMap.containsKey(position)) {
                            ChannelFragment fragment = new ChannelFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(ChannelFragment.INDEX, position);
                            bundle.putString(ChannelFragment.CATALOG_ID, catalogId);
                            fragment.setArguments(bundle);
                            mFragmentList.add(fragment);
                            mFragmentMap.put(position, fragment);
                            mFragmentStatePagerAdapter.notifyDataSetChanged();
                        }
                        mViewPager.setCurrentItem(position, true);
                    }
                } else {
                    view.setBackgroundResource(0);
                    ((TextView) view.findViewById(R.id.channel_category_tv)).setTextColor(getResources().getColor(R.color.item_title_color));
                }
            }
        });
    }

    private void initViewPager() {
        ChannelFragment fragment = new ChannelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ChannelFragment.INDEX, 0);
        bundle.putString(ChannelFragment.CATALOG_ID, catalogId);
        fragment.setArguments(bundle);
        mFragmentList.add(fragment);
        mFragmentMap.put(0, fragment);
        mFragmentStatePagerAdapter = new UFragmentStatePagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setPageTransformer(true, new VerticalPageTransformer());
        mViewPager.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        mViewPager.setAdapter(mFragmentStatePagerAdapter);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {

        if (type == InterfaceType.getCatalogInfo) {
            if (page == -1) {
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mSubChannelCategoryAdapter.add(0, bean.getCatalogInfo().getChildren().size(), bean.getCatalogInfo().getChildren());
                mChannelCategoryRv.setAdapter(mSubChannelCategoryAdapter);
            }
        }

    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    public void onItemSelected(boolean left, boolean right, boolean top, boolean bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
    }

    private void reset() {
        left = false;
        right = false;
        top = false;
        bottom = false;
        focused = true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (focused && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            if (mFragmentMap.containsKey(index)) {
                ChannelFragment channelFragment = (ChannelFragment) mFragmentMap.get(index);
                channelFragment.handleOnKeyDown(keyCode, event);
                mLeftLl.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                focused = false;
                return true;
            }
        }
        if (top && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            return true;
        }
        if (bottom && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();
        if (left && keyCode == KeyEvent.KEYCODE_DPAD_LEFT && action == KeyEvent.ACTION_DOWN) {
            if (mChannelCategoryRv != null && mChannelCategoryRv.getChildAt(index) != null) {
                mChannelCategoryRv.getChildAt(index).requestFocus();
                reset();
                //取消选中动画
                if (mFragmentMap.containsKey(index)) {
                    ChannelFragment channelFragment = (ChannelFragment) mFragmentMap.get(index);
                    channelFragment.handleOnKeyDown(keyCode, event);
                    return true;
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
