package com.zzr.rem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.zzr.rem.R;
import com.zzr.rem.fragment.ProductCommentFragment;
import com.zzr.rem.fragment.ProductDetailsFragment;
import com.zzr.rem.fragment.ProductIntroduceFragment;
import java.util.ArrayList;

/**
 * Created by Misaki on 2017/8/28.
 */

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public long mProductId;
    private View mDetailsIndicator;
    private View mIntroduceIndicator;
    private View mCommentIndicator;
    private ViewPager mContainerVp;
    private ContainerAdapter mContainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initData();

        initUI();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mProductId = intent.getLongExtra(ProductListActivity.TODETAILSKEY, 0);
        if (mProductId == 0) {
            tip("数据异常");
            finish();
        }
    }

    @Override
    protected void initUI() {
        findViewById(R.id.introduce_ll).setOnClickListener(this);
        findViewById(R.id.details_ll).setOnClickListener(this);
        findViewById(R.id.comment_ll).setOnClickListener(this);

        mIntroduceIndicator = findViewById(R.id.introduce_view);
        mDetailsIndicator = findViewById(R.id.details_view);
        mCommentIndicator = findViewById(R.id.comment_view);

        mContainerVp = (ViewPager) findViewById(R.id.container_vp);
        mContainerAdapter = new ContainerAdapter(getSupportFragmentManager());
        mContainerVp.setAdapter(mContainerAdapter);
        mContainerVp.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        defaultIndicator();
        switch (view.getId()) {
            case R.id.introduce_ll:
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(0);
                break;
            case R.id.details_ll:
                mDetailsIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(1);
                break;
            case R.id.comment_ll:
                mCommentIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(2);
                break;
        }
    }

    private void defaultIndicator() {
        mIntroduceIndicator.setVisibility(View.INVISIBLE);
        mDetailsIndicator.setVisibility(View.INVISIBLE);
        mCommentIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        defaultIndicator();
        switch (position) {
            case 0:
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                break;
            case 1:
                mDetailsIndicator.setVisibility(View.VISIBLE);
                break;
            case 2:
                mCommentIndicator.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class ContainerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

        public ContainerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(new ProductIntroduceFragment());
            mFragments.add(new ProductDetailsFragment());
            mFragments.add(new ProductCommentFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
