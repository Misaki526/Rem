package com.zzr.rem.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.image.SmartImageView;
import com.zzr.rem.cons.NetworkConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misaki on 2017/8/28.
 */

public class ProductAdAdapter extends PagerAdapter {
    private List<String> mImageUrlList;
    private List<SmartImageView> mSmartImageViews;

    public void setDatas(Context context, List<String> imageUrlList) {
        mImageUrlList = imageUrlList;
        mSmartImageViews = new ArrayList<SmartImageView>();

        for (int i = 0; i < imageUrlList.size(); i++) {
            // 创建控件 -> 设置宽高 -> 设置数据源 -> 添加到View容器
            SmartImageView smiv = new SmartImageView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            smiv.setLayoutParams(params);

            // TODO
            // smiv.setImageUrl(NetworkConst.BASE_URL + mImageUrlList.get(i));
            smiv.setImageUrl("http://mall.520it.com/" + mImageUrlList.get(i));
            mSmartImageViews.add(smiv);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SmartImageView smiv = mSmartImageViews.get(position);
        container.addView(smiv);
        return smiv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        SmartImageView smiv = mSmartImageViews.get(position);
        container.removeView(smiv);
    }

    @Override
    public int getCount() {
        return mSmartImageViews != null ? mSmartImageViews.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
