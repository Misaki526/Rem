package com.zzr.rem.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.zzr.rem.R;
import com.zzr.rem.activity.ProductDetailsActivity;
import com.zzr.rem.adapter.ProductAdAdapter;
import com.zzr.rem.bean.RProductInfo;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.ProductDetailsController;

import java.util.List;

/**
 * Created by lean on 16/10/28.
 */

public class ProductIntroduceFragment extends BaseFragment {
    private ViewPager AdVp;
    private ProductAdAdapter mAdAdapter;
    private ProductDetailsActivity mActivity;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.PRODUCT_INFO_ACTION_RESULT:
                handleProductInfo(msg);
                break;
        }
    }

    private void handleProductInfo(Message msg) {
        if (msg.obj == null) {
            tip("数据异常");
            mActivity.finish();
            return ;
        }

        RProductInfo bean = (RProductInfo) msg.obj;
        handleAdBanner(bean.getImgUrls());
    }

    /**
     * 处理广告图片信息
     */
    private void handleAdBanner(String imgUrls) {
        List<String> imageUrlList = JSON.parseArray(imgUrls, String.class);
        mAdAdapter.setDatas(getActivity(), imageUrlList);
        mAdAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_introduce, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();

        mActivity = (ProductDetailsActivity) getActivity();
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_INFO_ACTION, mActivity.mProductId);
    }

    @Override
    protected void initController() {
        mController = new ProductDetailsController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        AdVp = (ViewPager) getActivity().findViewById(R.id.advp);
        mAdAdapter = new ProductAdAdapter();
        AdVp.setAdapter(mAdAdapter);

    }
}
