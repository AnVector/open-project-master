package com.looper.ultimate.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.bean.ContentInfoBean;
import com.looper.ultimate.bean.ContentListBean;
import com.looper.ultimate.bridge.EffectBridge;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.presenter.ActivityPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.DetailInfoAdapter;
import com.looper.ultimate.view.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.looper.ultimate.view.adapter.HeaderSpanSizeLookup;
import com.looper.ultimate.view.adapter.SubChannelCategoryAdapter;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelActivity extends BaseActivity implements ViewImpl {

    //constant params
    private static final String TAG = ChannelActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerViewTV mRecyclerView;
    @BindView(R.id.mainUpView1)
    MainUpView mMainUpView1;
    @BindView(R.id.channel_category_rv)
    RecyclerView mChannelCategoryRv;

    private ActivityPresenter mPresenter;
    private EffectBridge mRecyclerViewBridge;
    private GridLayoutManagerTV gridlayoutManager;
    private DetailInfoAdapter mRecyclerViewAdapter;
    private View oldView;
    //data
    private boolean finished = false;
    private List<ContentInfoBean> mContentInfoBeans = new ArrayList<>();
    private List<CatalogInfoBean.CatalogInfo> mCatalogInfoBeans;
    //数据分页加载
    private int page = 0;
    private int start = 1;
    private static final int COUNT = 24;
    //footer data
    private List<String> mFooterList = new ArrayList<>();

    //adapter
    private SubChannelCategoryAdapter mSubChannelCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_channel;
    }

    @Override
    protected void getExtraParams() {

    }

    protected void initData() {
        mMainUpView1.setEffectBridge(new EffectBridge());
        mRecyclerViewBridge = (EffectBridge) mMainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.rectangle);
        //adapter 无数据源
        mRecyclerViewAdapter = new DetailInfoAdapter(this, R.layout.recyclerview_item, R.layout.recyclerview_header_item, R.layout.recyclerview_footer_item);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        gridlayoutManager = new GridLayoutManagerTV(this, 4);
        gridlayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridlayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), gridlayoutManager.getSpanCount()));
        mRecyclerView.setLayoutManager(gridlayoutManager);

        mSubChannelCategoryAdapter = new SubChannelCategoryAdapter(this, R.layout.sub_channel_category_item);
        mChannelCategoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChannelCategoryRv.setHasFixedSize(true);

        mPresenter = PresenterHolder.getInstance().createPresenter(this);
        getCatalogInfo();
        fetchCatalogInfo();
        getContentListByCatalog();
    }

    protected void initEvent() {
        gridlayoutManager.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(RecyclerView parent, View focusview, int position, int dy) {
                focusview.bringToFront();
                mRecyclerViewBridge.setFocusView(focusview, oldView, 1.05f, R.id.scale_zone, R.id.item_title, R.id.item_study_count);
                oldView = focusview;
                if (position > mContentInfoBeans.size() - 5 && !finished) {
                    getContentListByCatalog();
                }
            }
        });

    }

    private void getContentListByCatalog() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "560139");
            json.put("channelCode", "");
            json.put("start", start);
            json.put("count", COUNT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.fetchData(json, page, InterfaceType.getContentListByCatalog);
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

    private void getCatalogInfo() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "552179");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //原始框架获取接口请求数据
//       mPresenter.fetchData(json,1, InterfaceType.getCatalogInfo);
        //Volley异步请求框架获取接口数据
        mPresenter.VolleyRequestWithAuth(json, "catalogInfo", 0, InterfaceType.getCatalogInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {
        if (type == InterfaceType.getContentListByCatalog) {
            ContentListBean bean = GsonUtils.getBeanFromJson(result, ContentListBean.class);
            mContentInfoBeans.addAll(bean.getContentList());
            mRecyclerViewAdapter.addItem(start, bean.getContentList().size(), mContentInfoBeans);
            if (mContentInfoBeans.size() == Integer.parseInt(bean.getTotalCount())) {
                finished = true;
                mFooterList.add("以上为该分类下所有内容...");
                mRecyclerViewAdapter.addFooter(0, mFooterList.size(), mFooterList);
            } else {
                ++this.page;
                start += COUNT;
            }
        }

        if (type == InterfaceType.getCatalogInfo) {

            if(page == -1){
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mSubChannelCategoryAdapter.add(0,bean.getCatalogInfo().getChildren().size(),bean.getCatalogInfo().getChildren());
                mChannelCategoryRv.setAdapter(mSubChannelCategoryAdapter);
            }else{
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mCatalogInfoBeans = bean.getCatalogInfo().getChildren();
                mRecyclerViewAdapter.addHeader(0, mCatalogInfoBeans.size(), mCatalogInfoBeans);
            }
        }
    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    protected void onStop() {
        super.onStop();
//        VolleyManager.getInstance(this).cancelRequest("catalogInfo");
//        VolleyManager.getInstance(this).cancelRequest("subChannelInfo");
    }
}
