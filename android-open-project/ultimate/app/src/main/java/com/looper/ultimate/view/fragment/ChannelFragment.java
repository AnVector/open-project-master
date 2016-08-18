package com.looper.ultimate.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.bean.ContentInfoBean;
import com.looper.ultimate.bean.ContentListBean;
import com.looper.ultimate.bridge.EffectBridge;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.presenter.FragmentPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.util.LogUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.DetailInfoAdapter;
import com.looper.ultimate.view.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.looper.ultimate.view.adapter.HeaderSpanSizeLookup;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends BaseFragment implements ViewImpl {


    @BindView(R.id.recyclerView)
    RecyclerViewTV mRecyclerView;
    @BindView(R.id.mainUpView1)
    MainUpView mMainUpView1;

    private FragmentPresenter mPresenter;
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

    public ChannelFragment() {
        // Required empty public constructor
    }
    @Override
    protected void initData() {
        mMainUpView1.setEffectBridge(new EffectBridge());
        mRecyclerViewBridge = (EffectBridge) mMainUpView1.getEffectBridge();
//        mRecyclerViewBridge.setUpRectResource(R.drawable.rectangle);
        //adapter 无数据源
        mRecyclerViewAdapter = new DetailInfoAdapter(getContext(), R.layout.recyclerview_item, R.layout.recyclerview_header_item, R.layout.recyclerview_footer_item);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        gridlayoutManager = new GridLayoutManagerTV(getContext(), 4);
        gridlayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridlayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), gridlayoutManager.getSpanCount()));
        mRecyclerView.setLayoutManager(gridlayoutManager);

        mPresenter = PresenterHolder.getInstance().createPresenter(this);
        getCatalogInfo();
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
    protected void initEvent() {
        gridlayoutManager.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(RecyclerView parent, View focusview, int position, int dy) {
                focusview.bringToFront();
                mRecyclerViewBridge.setFocusView(focusview, oldView, 1.05f, R.id.scale_zone,R.id.study_count_ll,R.id.item_title);
                oldView = focusview;
                if (position > mContentInfoBeans.size() - 5 && !finished) {
                    getContentListByCatalog();
                }
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_channel;
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {
        if (type == InterfaceType.getContentListByCatalog) {
            ContentListBean bean = GsonUtils.getBeanFromJson(result, ContentListBean.class);
            mContentInfoBeans.addAll(bean.getContentList());
            mRecyclerViewAdapter.addItem(start, bean.getContentList().size(), mContentInfoBeans);
            if (mContentInfoBeans.size() == Integer.parseInt(bean.getTotalCount())) {
                finished = true;
                mFooterList.add("-----end-----");
                mRecyclerViewAdapter.addFooter(0, mFooterList.size(), mFooterList);
            } else {
                ++this.page;
                start += COUNT;
            }
        }

        if(type == InterfaceType.getCatalogInfo ){
            LogUtils.d("fragmentTransaction","142213425");
            CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
            mCatalogInfoBeans = bean.getCatalogInfo().getChildren();
            mRecyclerViewAdapter.addHeader(0, mCatalogInfoBeans.size(), mCatalogInfoBeans);
            getContentListByCatalog();
        }
    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
    }
}
