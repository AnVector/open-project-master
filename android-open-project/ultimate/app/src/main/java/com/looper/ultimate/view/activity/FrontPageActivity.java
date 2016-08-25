package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.common.VolleyManager;
import com.looper.ultimate.presenter.ActivityPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.util.LogUtils;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.ChannelCategoryAdapter;
import com.looper.ultimate.view.adapter.OnItemClickListener;
import com.looper.ultimate.view.widget.MiddleHorizontalScrollView;
import com.looper.ultimate.view.widget.glidetransformations.RoundedCornersTransformation;
import com.open.androidtvwidget.view.MainLayout;
import com.open.androidtvwidget.view.MainUpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class FrontPageActivity extends BaseActivity implements ViewImpl {

    @BindView(R.id.rec_img)
    ImageView mRecImg;
    @BindView(R.id.bottom_left_img)
    ImageView mBottomLeftImg;
    @BindView(R.id.bottom_right_img)
    ImageView mBottomRightImg;
    @BindView(R.id.channel_category_rv)
    RecyclerView mChannelCategoryRv;
    private View preFocusView;

    private ActivityPresenter mPresenter;

    //View
    private SparseArray<View> mItemViews = new SparseArray<>();
    //data
    private List<CatalogInfoBean.CatalogInfo> mCatalogInfoBeans;
    private List<CatalogInfoBean.CatalogInfo> mChannelCategoryInfoBeans = new ArrayList<>();
    //adapter
    private ChannelCategoryAdapter mChannelCategoryAdapter;
    //test data
    private List<Map<String, Integer>> mChannelList = new ArrayList<>();
    //test data
    private Map<String, Integer> mChannelMap1 = new HashMap<>();
    private Map<String, Integer> mChannelMap2 = new HashMap<>();
    private Map<String, Integer> mChannelMap3 = new HashMap<>();
    private Map<String, Integer> mChannelMap4 = new HashMap<>();
    private Map<String, Integer> mChannelMap5 = new HashMap<>();


    @BindView(R.id.main_lay)
    MainLayout mMainLay;
    @BindView(R.id.hscroll_view)
    MiddleHorizontalScrollView mHscrollView;
    @BindView(R.id.mainUpView1)
    MainUpView mMainUpView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_front_page;
    }

    @Override
    protected void getExtraParams() {

    }

    private void getCatalogInfo() {
        LogUtils.d("init", "init2");
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "181693");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //原始框架获取接口请求数据
//       mPresenter.fetchData(json,1, InterfaceType.getCatalogInfo);
        //Volley异步请求框架获取接口数据
//        mPresenter.VolleyRequestWithAuth(json, this, 0, InterfaceType.getCatalogInfo);
        //OkHttp异步请求框架获取接口数据
        mPresenter.OkHttpRequestWithAuth(json,this,0,InterfaceType.getCatalogInfo);
    }

    private void fetchCatalogInfo() {
        LogUtils.d("init", "init3");
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "552179");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.VolleyRequestWithAuth(json, this, 1, InterfaceType.getCatalogInfo);
    }

    private void fetchChannelCatalogInfo() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "509044");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.VolleyRequestWithAuth(json, this, 2, InterfaceType.getCatalogInfo);
    }

    protected void initData() {
        LogUtils.d("init", "init1");
        mChannelMap1.put("亲子幼儿", R.drawable.infant_education);
        mChannelMap2.put("小学课堂", R.drawable.primary_education);
        mChannelMap3.put("中学课堂", R.drawable.secondary_education);
        mChannelMap4.put("健康生活", R.drawable.healthy_life);
        mChannelMap5.put("赢在职场", R.drawable.professional_life);

        mChannelList.add(mChannelMap1);
        mChannelList.add(mChannelMap2);
        mChannelList.add(mChannelMap3);
        mChannelList.add(mChannelMap4);
        mChannelList.add(mChannelMap5);
//      mMainUpView1.setUpRectResource(R.drawable.rectangle);
        mPresenter = PresenterHolder.getInstance().createPresenter(this);
        mChannelCategoryAdapter = new ChannelCategoryAdapter(this, R.layout.channel_category_item);
        mChannelCategoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChannelCategoryRv.setHasFixedSize(true);

        getCatalogInfo();
        fetchCatalogInfo();
        fetchChannelCatalogInfo();
    }

    protected void initEvent() {
        mMainLay.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();// 防止放大的view被压在下面. (建议使用MainLayout)
                    mMainUpView1.setFocusView(newFocus, preFocusView, 1.05f);
                    preFocusView = newFocus; // 4.3以下需要自己保存.
                }

            }
        });

        mHscrollView.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                mMainUpView1.setVisibility(View.GONE);
                return false;
            }
        });

        mChannelCategoryAdapter.setOnItemClickListener(new OnItemClickListener<Map<String, Integer>>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Map<String, Integer> stringIntegerMap, int position) {
//                Intent intent = new Intent(FrontPageActivity.this, ChannelActivity.class);
                Intent intent = new Intent(FrontPageActivity.this, ChannelFragmentActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Map<String, Integer> stringIntegerMap, int position) {
                return false;
            }
        });

    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {

        if (type == InterfaceType.getCatalogInfo) {
            if (page == 0) {
                LogUtils.d("init", "init4");
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mCatalogInfoBeans = bean.getCatalogInfo().getChildren();
                for (int i = 0; i < mCatalogInfoBeans.size(); i++) {
                    CatalogInfoBean.CatalogInfo catalogInfo = mCatalogInfoBeans.get(i);
                    displayAllViews(catalogInfo, i);
                }
            }
            mMainUpView1.bringToFront();
            if (page == 1) {
                LogUtils.d("init", "init5");
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                Glide.with(this)
                        .load(bean.getCatalogInfo().getChildren().get(0).getCoverImages().get(0).getImageUrl())
                        .placeholder(R.drawable.default8)
                        .bitmapTransform(new RoundedCornersTransformation(this, 6, 0, RoundedCornersTransformation.CornerType.ALL))
                        .into(mRecImg);
            }

            if (page == 2) {
                LogUtils.d("init", "init6");
                CatalogInfoBean bean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
                mChannelCategoryAdapter.add(0, mChannelList.size(), mChannelList);
//                filter(bean.getCatalogInfo().getChildren());
//                mChannelCategoryInfoBeans = bean.getCatalogInfo().getChildren();
//                mChannelCategoryAdapter.add(0,mChannelCategoryInfoBeans.size(),mChannelCategoryInfoBeans);
                mChannelCategoryRv.setAdapter(mChannelCategoryAdapter);
            }
        }
    }

    private void displayAllViews(CatalogInfoBean.CatalogInfo catalogInfo, int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.front_page_item_view, null);
        if (view != null) {
            view.setId(i + 1);//id=0 设置相对布局出现问题
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (i == 0) {
                lp.addRule(RelativeLayout.RIGHT_OF, mRecImg.getId());
                lp.addRule(RelativeLayout.ALIGN_TOP, mRecImg.getId());

            } else {
                lp.addRule(RelativeLayout.RIGHT_OF, mItemViews.get(i).getId());
                lp.addRule(RelativeLayout.ALIGN_TOP, mItemViews.get(i).getId());
            }
            view.setLayoutParams(lp);
            ImageView mImageView = (ImageView) view.findViewById(R.id.top_img);
            TextView mTextView = (TextView) view.findViewById(R.id.top_title);
            String url = catalogInfo.getCoverImages().get(0).getImageUrl();
            String title = catalogInfo.getCatalogName();
            mTextView.setText(title);
//            mImageView.setTag(catalogInfo);
            mItemViews.put(view.getId(), view);
            mMainLay.addView(view);
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.default6)
                    .bitmapTransform(new RoundedCornersTransformation(this, 6, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(mImageView);
        }
    }

    /*
    * RxJava的正确使用姿势
    *
    * */

//    private void filter(final List<CatalogInfoBean.CatalogInfo> beans) {
//        Observable.from(beans).filter(new Func1<CatalogInfoBean.CatalogInfo, Boolean>() {
//            @Override
//            public Boolean call(CatalogInfoBean.CatalogInfo catalogInfo) {
//                String catalogName = catalogInfo.getCatalogName();
//                if(catalogName.equals("亲子幼儿")||catalogName.equals("中小学")||catalogName.equals("赢在职场")||catalogName.equals("健康生活")){
//                    return true;
//                }
//                return false;
//            }
//        }).subscribe(new Observer<CatalogInfoBean.CatalogInfo>() {
//            @Override
//            public void onCompleted() {
//                mChannelCategoryAdapter.add(0,mChannelCategoryInfoBeans.size(),mChannelCategoryInfoBeans);
//                mChannelCategoryRv.setAdapter(mChannelCategoryAdapter);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(CatalogInfoBean.CatalogInfo catalogInfo) {
//                mChannelCategoryInfoBeans.add(catalogInfo);
//            }
//        });
//    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        //在onStop()方法里执行取消网络请求的操作会使退出应用后在进入应用无数据显示
        VolleyManager.getInstance(this).cancelRequest(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
    }
}
