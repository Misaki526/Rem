package com.zzr.rem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RTopCategory;

/**
 * Created by Misaki on 2017/8/6.
 */

public class TopCategoryAdapter extends RemBaseAdapter<RTopCategory> {

    public int mCurrentTabPosition = -1;      // 当前被点击的item

    public TopCategoryAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        View dividerView;
        TextView nameTv;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.top_category_item, parent, false);
            holder = new ViewHolder();
            holder.dividerView = contentView.findViewById(R.id.divider);
            holder.nameTv = (TextView) contentView.findViewById(R.id.name_tv);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        RTopCategory bean = mDatas.get(position);

        holder.nameTv.setText(bean.getName());
        // 修改样式：
        if (position == mCurrentTabPosition) {
            holder.nameTv.setSelected(true);
            holder.nameTv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
            holder.dividerView.setVisibility(View.INVISIBLE);
        } else {
            holder.nameTv.setSelected(false);
            holder.nameTv.setBackgroundColor(0xFFFAFAFA);
            holder.dividerView.setVisibility(View.VISIBLE);
        }
        return contentView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }
}
