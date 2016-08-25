package com.looper.ultimate.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.bean.ContentInfoBean;
import com.looper.ultimate.bean.ContentListBean;
import com.looper.ultimate.bridge.EffectBridge;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.common.VolleyManager;
import com.looper.ultimate.presenter.FragmentPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.DetailInfoAdapter;
import com.looper.ultimate.view.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.looper.ultimate.view.adapter.HeaderSpanSizeLookup;
import com.looper.ultimate.view.adapter.OnItemFocusChangeListener;
import com.looper.ultimate.view.widget.VerticalSeekBar;

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
    RecyclerView mRecyclerView;
    @BindView(R.id.vertical_seekbar)
    VerticalSeekBar mVerticalSeekbar;

    //Presenter
    private FragmentPresenter mPresenter;
    //Effect Bridge
    private EffectBridge mRecyclerViewBridge;
    private GridLayoutManager mGridLayoutManager;
    //RecyclerViewAdapter
    private DetailInfoAdapter mRecyclerViewAdapter;
    private View oldView;
    //data
    private boolean finished = false;
    private List<ContentInfoBean> mContentInfoBeans = new ArrayList<>();
    private List<CatalogInfoBean.CatalogInfo> mCatalogInfoBeans = new ArrayList<>();
    //数据分页加载
    private int page = 0;
    private int start = 1;
    private static final int COUNT = 24;
    //footer data
    private List<String> mFooterList = new ArrayList<>();
    //Fragment params
    private int index = 0;
    private String catalogId;
    public static final String INDEX = "index";
    public static final String CATALOG_ID = "catalog_id";
    //Item Total Count
    private int itemTotalCount;
    //RecyclerView focused child position
    private int position = 0;

    //Fragment callback
    private FragmentListener mFragmentListener;


    public interface FragmentListener{
        void onItemSelected(boolean left, boolean right,boolean top, boolean bottom);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mFragmentListener = (FragmentListener) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(INDEX);
            catalogId = bundle.getString(CATALOG_ID);
        }
    }

    @Override
    protected void initData() {
        mRecyclerViewBridge = new EffectBridge(R.id.scale_zone, R.id.item_study_count, R.id.item_title,1.05f);
        mRecyclerViewAdapter = new DetailInfoAdapter(getContext(), R.layout.recyclerview_item, R.layout.recyclerview_header_item, R.layout.recyclerview_footer_item);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mGridLayoutManager = new GridLayoutManager(getContext(),4);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGridLayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), mGridLayoutManager.getSpanCount()));
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mPresenter = PresenterHolder.getInstance().createPresenter(this);
        if (index == 0) {
            mVerticalSeekbar.setVisibility(View.GONE);
            getCatalogInfo();
        } else {
            getContentListByCatalog();
        }
    }

    private void getContentListByCatalog() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", catalogId);
            json.put("channelCode", "");
            json.put("start", start);
            json.put("count", COUNT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.VolleyRequestWithAuth(json,this,page,InterfaceType.getContentListByCatalog);
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
        mPresenter.VolleyRequestWithAuth(json, this, 0, InterfaceType.getCatalogInfo);
    }

    @Override
    protected void initEvent() {
        mRecyclerViewAdapter.setOnItemFocusChangeListener(new OnItemFocusChangeListener() {
            @Override
            public void onItemFocusChanged(ViewGroup parent, View view, Object o, int position, boolean hasFocus) {
                setAnimation(view,hasFocus);
                if (hasFocus) {
                    setProgress(position);
                    fetchMoreData(position);
                    callBack(position);
                }else{

                }
            }
        });


    }

    private void callBack(int position){
        boolean left = isLeft(position,4);
        boolean right = isRight(position,4);
        boolean top = isTop(position,4);
        boolean bottom = isBottom(position,4);
        if(mFragmentListener!=null){
            mFragmentListener.onItemSelected(left,right,top,bottom);
        }
    }

    private void fetchMoreData(int position){
        this.position = position;
        if (position > mContentInfoBeans.size() - 5 && !finished) {
            getContentListByCatalog();
        }
    }

    private void setAnimation(View view,boolean hasFocus){
        mRecyclerViewBridge.setViewAnimation(view,hasFocus);
        oldView = view;
    }

    private void setProgress(int position){
        int divResult = itemTotalCount / 4;
        int modResult = itemTotalCount % 4;
        int idivResult = position / 4;
        if (modResult == 0) {
            mVerticalSeekbar.setMax(divResult - 1);
            mVerticalSeekbar.setThumb(divResult - idivResult - 1);
        } else {
            mVerticalSeekbar.setMax(divResult);
            mVerticalSeekbar.setThumb(divResult - idivResult);
        }
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

            itemTotalCount = Integer.parseInt(bean.getTotalCount());
            if (mContentInfoBeans.size() == itemTotalCount) {
                finished = true;
//                mFooterList.add("-----end-----");
//                mRecyclerViewAdapter.addFooter(0, mFooterList.size(), mFooterList);
            } else {
                ++this.page;
                start += COUNT;
            }
        }

        if (type == InterfaceType.getCatalogInfo) {
            CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
            if(mCatalogInfoBeans.isEmpty()){
                mCatalogInfoBeans.add(bean.getCatalogInfo().getChildren().get(0));
                mCatalogInfoBeans.add(bean.getCatalogInfo().getChildren().get(1));
            }
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

    public void handleOnKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            if(mRecyclerView!=null&&mRecyclerView.getChildAt(position)!=null){
                mRecyclerView.getChildAt(position).requestFocus();
            }
        }

        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            mRecyclerViewBridge.setViewAnimation(oldView,false);
            oldView = null;
        }
    }

    private boolean isLeft(int position, int columnCount) {

        if (position % columnCount == 0) {
            return true;
        }
        return false;
    }

    private boolean isRight(int position, int columnCount) {
        if ((position + 1) % columnCount == 0||(position==itemTotalCount-1)) {
            return true;
        }
        return false;
    }

    private boolean isTop(int position, int columnCount) {
        if (position < columnCount && position > -1) {
            return true;
        }
        return false;
    }

    private boolean isBottom(int position, int columnCount) {
        int mod = ((itemTotalCount % columnCount == 0) ? columnCount
                : itemTotalCount % columnCount);
        boolean atBottom = ((position >= itemTotalCount - mod) ? true : false);
        if (itemTotalCount != -1 && atBottom) {
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PresenterHolder.getInstance().remove(this);
        VolleyManager.getInstance(getContext()).cancelRequest(this);
    }
}
