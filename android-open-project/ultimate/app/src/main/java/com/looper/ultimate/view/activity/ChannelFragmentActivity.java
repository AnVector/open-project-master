package com.looper.ultimate.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
import com.looper.ultimate.view.adapter.UFragmentPagerAdapter;
import com.looper.ultimate.view.fragment.ChannelFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChannelFragmentActivity extends BaseFragmentActivitiy implements ViewImpl {

    @BindView(R.id.btn_search)
    TextView mBtnSearch;
    @BindView(R.id.channel_category_rv)
    RecyclerView mChannelCategoryRv;
    @BindView(R.id.tv_icon)
    TextView mTvIcon;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private ActivityPresenter mPresenter;
    //adapter
    private SubChannelCategoryAdapter mSubChannelCategoryAdapter;
    //channel fragment list
    private List<Fragment> mFragmentList = new ArrayList();
    //channel fragment adapter
    private UFragmentPagerAdapter mPagerAdapter;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_channel_fragment;
    }

    @Override
    protected void getExtraParams() {

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

    private void fetchCatalogInfo(){
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "560314");
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
                if(hasFocus){
                    view.bringToFront();
                    view.setBackgroundResource(R.drawable.round_corner_rectangle);
                    ((TextView)view.findViewById(R.id.channel_category_tv)).setTextColor(getResources().getColor(R.color.white));
                }else{
                    view.setBackgroundResource(0);
                    ((TextView)view.findViewById(R.id.channel_category_tv)).setTextColor(getResources().getColor(R.color.item_title_color));
                }
            }
        });

        mBtnSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mBtnSearch.setBackgroundResource(R.drawable.btn_search_highlight);
                }else{
                    mBtnSearch.setBackgroundResource(R.drawable.btn_search);
                }
            }
        });

    }

    private void initViewPager(){
        mFragmentList.add(new ChannelFragment());
        mPagerAdapter = new UFragmentPagerAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {

        if (type == InterfaceType.getCatalogInfo) {
            if(page == -1){
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mSubChannelCategoryAdapter.add(0,bean.getCatalogInfo().getChildren().size(),bean.getCatalogInfo().getChildren());
                mChannelCategoryRv.setAdapter(mSubChannelCategoryAdapter);
            }
        }

    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @OnClick(R.id.btn_search)
    public void onClick() {
    }
}
