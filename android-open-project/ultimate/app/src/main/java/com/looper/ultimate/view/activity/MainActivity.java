package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.looper.ultimate.R;
import com.looper.ultimate.bean.CatalogInfoBean;
import com.looper.ultimate.bean.Gallery;
import com.looper.ultimate.bean.GalleryList;
import com.looper.ultimate.common.AppConstant;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.common.VolleyManager;
import com.looper.ultimate.presenter.ActivityPresenter;
import com.looper.ultimate.presenter.PresenterHolder;
import com.looper.ultimate.util.GsonUtils;
import com.looper.ultimate.view.ViewImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewImpl {

    private static final String TAG_NAME = MainActivity.class.getSimpleName();
    private static final String TNGOU = "http://www.tngou.net/tnfs/api/list?";

    //View
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.txt)
    TextView mTxt;
    @BindView(R.id.imv)
    ImageView mImv;
    @BindView(R.id.imv_glide)
    ImageView mImvGlide;

    //bean
    private CatalogInfoBean mCatalogInfoBean;

    protected ActivityPresenter mPresenter;
    protected int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getExtraParams() {

    }

    private JSONObject getJsonParams() {
        JSONObject json = new JSONObject();
        try {
            json.put("catalogId", "244562");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private JSONObject getTNJsonParams() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "2");
        map.put("rows", "4");
        JSONObject json = new JSONObject(map);
        return json;
    }

    @Override
    protected void initData() {
        mBtn.setText(TAG_NAME);
        fetchData();
    }

    protected void initEvent() {

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                startActivity(intent);

            }
        });

    }

    private void fetchData() {
        if (mPresenter == null) {
            mPresenter = PresenterHolder.getInstance().createPresenter(this);
        }
        mPresenter.fetchData(getJsonParams(), page, InterfaceType.getCatalogInfo);
        mPresenter.fetchDataByVolley(TNGOU, getTNJsonParams(), "get", 0,InterfaceType.TNGOU);
    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {
        if(type == InterfaceType.TNGOU){
            Gallery mGalleryBean = GsonUtils.getBeanFromJson(result, GalleryList.class).getTngou().get(10);
            String url = AppConstant.TNGOUAPI + mGalleryBean.getImg();
            Glide.with(this).load(url).into(mImv);
        }

        if(type == InterfaceType.getCatalogInfo){
            mCatalogInfoBean = GsonUtils.getBeanFromJson(result, CatalogInfoBean.class);
            String url = mCatalogInfoBean.getCatalogInfo().getChildren().get(0).getCoverImages().get(0).getImageUrl();
            Glide.with(this).load(url).into(mImvGlide);
        }
    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleyManager.getInstance(this).cancelRequest("get");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PresenterHolder.getInstance().remove(this);
    }
}
