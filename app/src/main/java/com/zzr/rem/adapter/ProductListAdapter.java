package com.zzr.rem.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RProductList;
import com.zzr.rem.bean.RSecondKill;

import org.w3c.dom.Text;

/**
 * Created by Misaki on 2017/8/6.
 */

public class ProductListAdapter extends RemBaseAdapter<RProductList> {

    public ProductListAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        SmartImageView smIv;
        TextView nameTv;
        TextView priceTv;
        TextView commrateTv;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.product_lv_item, parent, false);
            holder = new ViewHolder();
            holder.smIv = (SmartImageView) contentView.findViewById(R.id.product_iv);
            holder.nameTv = (TextView) contentView.findViewById(R.id.name_tv);
            holder.priceTv = (TextView) contentView.findViewById(R.id.price_tv);
            holder.commrateTv = (TextView) contentView.findViewById(R.id.commrate_tv);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        RProductList bean = mDatas.get(position);

        // TODO
        // holder.smIv.setImageUrl(NetworkConst.BASE_URL + bean.getIconUrl());
        holder.smIv.setImageUrl("http://mall.520it.com" + bean.getIconUrl());
        holder.nameTv.setText(bean.getName());
        holder.priceTv.setText("￥ " + bean.getPrice());
        holder.commrateTv.setText(bean.getCommentCount() + "条评价 好评" + bean.getFavcomRate() + "%");
        return contentView;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }
}
