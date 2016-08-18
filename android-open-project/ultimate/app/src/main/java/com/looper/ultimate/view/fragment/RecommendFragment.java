package com.looper.ultimate.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.looper.ultimate.R;
import com.looper.ultimate.bean.Gallery;
import com.looper.ultimate.bean.GalleryList;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.common.VolleyManager;
import com.looper.ultimate.presenter.FragmentPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.GalleryAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment implements ViewImpl {

    //static
    private static final String TNGOU = "http://www.tngou.net/tnfs/api/list?";
    //Presenter
    private FragmentPresenter mPresenter;
    //View
    private RecyclerView mRecyclerView;
    //Adapter
    private GalleryAdapter mGalleryAdapter;
    //bean
    private GalleryList mGalleryList;
    //list
    private List<Gallery> mGalleryBeans = new ArrayList<>();

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initData() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recommend_rv);
        mGalleryAdapter = new GalleryAdapter(getContext(),R.layout.recommend_recyclerview_item);
        mRecyclerView.setAdapter(mGalleryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        fetchData();
    }

    @Override
    protected void initEvent() {

    }

    private JSONObject getTNJsonParams(){
        Map<String,String> map = new HashMap<>();
        map.put("page","2");
        map.put("rows","20");
        JSONObject json = new JSONObject(map);
        return json;
    }
    private void fetchData(){
        if(mPresenter == null){
            mPresenter = PresenterHolder.getInstance().createPresenter(this,getContext());
        }
        mPresenter.fetchDataByVolley(TNGOU,getTNJsonParams(),"phone",0,InterfaceType.TNGOU);
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {
        if(type == InterfaceType.TNGOU){
            mGalleryList = GsonUtils.getBeanFromJson(result,GalleryList.class);
            mGalleryBeans.addAll(mGalleryList.getTngou());
            mGalleryAdapter.add(0,mGalleryList.getTngou().size(),mGalleryBeans);
        }
    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
        VolleyManager.getInstance(getContext()).cancelRequest("phone");

    }
}
