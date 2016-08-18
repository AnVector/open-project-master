package com.looper.ultimate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.looper.ultimate.R;
import com.looper.ultimate.view.widget.GuidViewPager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class GuidActivity extends BaseActivity {

    //View
    @BindView(R.id.guid_vp)
    GuidViewPager mGuidVp;
    @BindView(R.id.guid_dot_1)
    RadioButton mGuidDot1;
    @BindView(R.id.guid_dot_2)
    RadioButton mGuidDot2;
    @BindView(R.id.guid_dot_3)
    RadioButton mGuidDot3;
    @BindView(R.id.guid_dot_4)
    RadioButton mGuidDot4;

    //RadioButton dot Map
    private Map<Integer, RadioButton> mRadioButtonMap = new HashMap<>();
    //Image Resource
    private static final int[] mGuidPics = {R.drawable.guid_pic_1,
            R.drawable.guid_pic_2, R.drawable.guid_pic_3,
            R.drawable.guid_pic_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_guid;
    }

    @Override
    protected void getExtraParams() {

    }

    protected void initData() {
        mRadioButtonMap.put(0, mGuidDot1);
        mRadioButtonMap.put(1, mGuidDot2);
        mRadioButtonMap.put(2, mGuidDot3);
        mRadioButtonMap.put(3, mGuidDot4);
        initViewPager();
    }

    protected void initEvent() {
        mGuidVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioButtonMap.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mGuidVp.setOnGuidViewPagerListener(new GuidViewPager.OnGuidViewPagerListener() {
            @Override
            public void onRightPageSelected() {
                Intent intent = new Intent(GuidActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLeftPageSelected() {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViewPager() {
        mGuidVp.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(GuidActivity.this);
                img.setImageResource(mGuidPics[position]);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(img);
                mGuidVp.setObjectForPosition(img, position);
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return mGuidPics.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
