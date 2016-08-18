package com.looper.ultimate.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.looper.ultimate.R;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.view.ViewImpl;
import com.looper.ultimate.view.adapter.UFragmentPagerAdapter;
import com.looper.ultimate.view.fragment.ChatsFragment;
import com.looper.ultimate.view.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HomeActivity extends BaseFragmentActivitiy implements ViewImpl {

    //View
    @BindView(R.id.recommend_vp)
    ViewPager mViewPager;
    @BindView(R.id.radio_button1)
    RadioButton mRadioButton1;
    @BindView(R.id.radio_button2)
    RadioButton mRadioButton2;
    @BindView(R.id.radio_button3)
    RadioButton mRadioButton3;
    @BindView(R.id.radio_button4)
    RadioButton mRadioButton4;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    //Fragment List
    private List<Fragment> mFragments = new ArrayList<>();
    //RadioButton Map
    private Map<Integer, Integer> mRadioButtons = new HashMap<>();


    @Override
    protected int getContentViewId() {
        return R.layout.activity_recommend;
    }

    protected void getExtraParams() {

    }

    private void initViewPager() {
        mFragments.add(new RecommendFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new ChatsFragment());
//      mFragments.add(new RecommendFragment());
        mViewPager.setAdapter(new UFragmentPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.setCurrentItem(0);
    }

    protected void initData() {
        initViewPager();
        mRadioButtons.put(R.id.radio_button1, 0);
        mRadioButtons.put(R.id.radio_button2, 1);
        mRadioButtons.put(R.id.radio_button3, 2);
        mRadioButtons.put(R.id.radio_button4, 3);
    }

    protected void initEvent() {

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(mRadioButtons.get(checkedId));
            }
        });

    }

    @Override
    public void onSuccess(String result, int page, InterfaceType type) {

    }

    @Override
    public void onFailure(String error, int page, InterfaceType type) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
