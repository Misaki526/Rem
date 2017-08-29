package com.zzr.rem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RRecommendProduct;

/**
 * Created by Misaki on 2017/8/6.
 */

public class RecommendAdapter extends RemBaseAdapter<RRecommendProduct> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        SmartImageView smIv;
        TextView nameTv;
        TextView priceTv;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.recommend_gv_item, parent, false);
            holder = new ViewHolder();
            holder.smIv = (SmartImageView) contentView.findViewById(R.id.image_iv);
            holder.nameTv = (TextView) contentView.findViewById(R.id.name_tv);
            holder.priceTv = (TextView) contentView.findViewById(R.id.price_tv);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        RRecommendProduct bean = mDatas.get(position);
        // TODO
        // holder.smIv.setImageUrl(NetworkConst.BASE_URL + bean.getIconUrl());
        holder.smIv.setImageUrl("http://mall.520it.com" + bean.getIconUrl());
        holder.nameTv.setText(bean.getName());
        holder.priceTv.setText("￥ " + bean.getPrice());
        return contentView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getProductId() : 0;
    }
}
