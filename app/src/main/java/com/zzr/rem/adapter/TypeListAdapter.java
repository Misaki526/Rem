package com.zzr.rem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RBrand;

/**
 * Created by Misaki on 2017/8/6.
 */

public class TypeListAdapter extends RemBaseAdapter<String> {

    public int mCurrentTabPosition = -1;      // 当前被点击的item

    public TypeListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        TextView typeTv = null;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.brand_lv_item_layout, parent, false);
            typeTv = (TextView) contentView.findViewById(R.id.brand_tv);
            contentView.setTag(typeTv);
        } else {
            typeTv = (TextView) contentView.getTag();
        }
        String productType = mDatas.get(position);
        typeTv.setText(productType);

        // 修改样式：
        typeTv.setSelected(position == mCurrentTabPosition);
        return contentView;
    }

}
