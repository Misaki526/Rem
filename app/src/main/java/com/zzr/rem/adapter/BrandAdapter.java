package com.zzr.rem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zzr.rem.R;
import com.zzr.rem.bean.RBrand;
import com.zzr.rem.bean.RTopCategory;

/**
 * Created by Misaki on 2017/8/6.
 */

public class BrandAdapter extends RemBaseAdapter<RBrand> {

    public int mCurrentTabPosition = -1;      // 当前被点击的item

    public BrandAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        TextView brandTv = null;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.brand_lv_item_layout, parent, false);
            brandTv = (TextView) contentView.findViewById(R.id.brand_tv);
            contentView.setTag(brandTv);
        } else {
            brandTv = (TextView) contentView.getTag();
        }
        RBrand bean = mDatas.get(position);
        brandTv.setText(bean.getName());

        // 修改样式：
        brandTv.setSelected(position == mCurrentTabPosition);
        return contentView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }
}
