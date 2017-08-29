package com.zzr.rem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.activity.ProductDetailsActivity;
import com.zzr.rem.activity.ProductListActivity;
import com.zzr.rem.adapter.RecommendAdapter;
import com.zzr.rem.adapter.SecondKillAdapter;
import com.zzr.rem.bean.Banner;
import com.zzr.rem.bean.RRecommendProduct;
import com.zzr.rem.bean.RSecondKill;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.HomeController;
import com.zzr.rem.ui.HorizontalListView;
import com.zzr.rem.util.FixedViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * Created by Misaki on 2017/8/2.
 */

public class HomeFragment extends BaseFragment implements OnPageChangeListener, AdapterView.OnItemClickListener {

    private ViewPager mAdVp;
    private ADAdpater mADAapter;
    private LinearLayout mIndicatorLl;
    private Timer mTimer;
    private HorizontalListView mSecondKillLv;
    private SecondKillAdapter mSecondKillAdapter;
    private GridView mRecommendGv;
    private RecommendAdapter mRecommendAdapter;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.GET_BANNER_ACTION_RESULT:
                // 处理广告结果
                handleBannerResult(msg);
                break;
            case IdiyMessage.SECOND_KILL_ACTION_RESULT:
                // 处理秒杀结果
                handleSecondKillResult(msg);
                break;
            case IdiyMessage.RECOMMEND_PRODUCT_ACTION_RESULT:
                // 处理推荐结果
                handleRecommendProductResult(msg);
                break;
        }
    }

    private void handleBannerResult(Message msg) {
        List<Banner> datas = (List<Banner>) msg.obj;

        if (datas.size() != 0) {
            mADAapter.setDatas(datas);
            mADAapter.notifyDataSetChanged();

            // 底部小点点
            initBannerIndicator(datas);
            // 启动一个定时器，自动滑动
            initBannerTimer(datas);
        }
    }

    private void handleSecondKillResult(Message msg) {
        List<RSecondKill> datas = (List<RSecondKill>) msg.obj;

        if (datas.size() != 0) {
            mSecondKillAdapter.setDatas(datas);
            mSecondKillAdapter.notifyDataSetChanged();
        }
    }

    private void handleRecommendProductResult(Message msg) {
        List<RRecommendProduct> datas = (List<RRecommendProduct>) msg.obj;

        if (datas.size() != 0) {
            mRecommendAdapter.setDatas(datas);
            mRecommendAdapter.notifyDataSetChanged();

            // 根据数据的数量，计算GridView的高度
            FixedViewUtil.setGridViewHeightBasedOnChildren(mRecommendGv, 2);
        }
    }

    private void initBannerIndicator(List<Banner> datas) {
        for (int i = 0; i < datas.size(); i++) {
            View view = new View(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(10, 0, 0, 0);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.ad_indicator_bg);
            view.setEnabled(i == 0);
            mIndicatorLl.addView(view);
        }
    }

    private void initBannerTimer(final List<Banner> datas) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeBannerItem(datas);
            }
        }, 3000, 3000);
    }

    private void changeBannerItem(final List<Banner> datas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int currentItem = mAdVp.getCurrentItem();
                currentItem++;
                if (currentItem > datas.size() - 1) {
                    currentItem = 0;
                }
                mAdVp.setCurrentItem(currentItem);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.GET_BANNER_ACTION, 1);
        mController.sendAsyncMessage(IdiyMessage.SECOND_KILL_ACTION, 0);
        mController.sendAsyncMessage(IdiyMessage.RECOMMEND_PRODUCT_ACTION, 0);
    }

    @Override
    protected void initController() {
        mController = new HomeController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        // 初始化广告页面
        mAdVp = (ViewPager) getActivity().findViewById(R.id.ad_vp);
        mADAapter = new ADAdpater();
        mAdVp.setAdapter(mADAapter);
        mAdVp.setOnPageChangeListener(this);
        mIndicatorLl = (LinearLayout) getActivity().findViewById(R.id.ad_indicator);

        // 初始化横向的ListView
        mSecondKillLv = (HorizontalListView) getActivity().findViewById(R.id.horizon_listview);
        mSecondKillAdapter = new SecondKillAdapter(getActivity());
        mSecondKillLv.setAdapter(mSecondKillAdapter);

        // 推荐商品模块
        mRecommendGv = (GridView) getActivity().findViewById(R.id.recommend_gv);
        mRecommendAdapter = new RecommendAdapter(getActivity());
        mRecommendGv.setAdapter(mRecommendAdapter);
        mRecommendGv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pid = mRecommendAdapter.getItemId(position);
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra(ProductListActivity.TODETAILSKEY, pid);
        startActivity(intent);
    }

    public class ADAdpater extends PagerAdapter {
        private List<Banner> mDatas;
        private List<SmartImageView> mChildViews;

        @Override
        public int getCount() {
            return mDatas != null ? mDatas.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public void setDatas(List<Banner> datas) {
            mDatas = datas;
            mChildViews = new ArrayList<SmartImageView>(mDatas.size());
            for (Banner banner : mDatas) {
                SmartImageView smiv = new SmartImageView(getActivity());

                smiv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                smiv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // TODO
                // smiv.setImageUrl(NetworkConst.BASE_URL + banner.getAdUrl());
                smiv.setImageUrl("http://mall.520it.com" + banner.getAdUrl());

                mChildViews.add(smiv);
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SmartImageView smiv = mChildViews.get(position);
            container.addView(smiv);
            return smiv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            SmartImageView smiv = mChildViews.get(position);
            container.removeView(smiv);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 修改底部小点点样式
        int childCount = mIndicatorLl.getChildCount();
        for (int i = 0; i < childCount; i++) {
            mIndicatorLl.getChildAt(i).setEnabled(i == position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
