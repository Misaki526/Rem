package com.zzr.rem.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RSecondKill;
import com.zzr.rem.cons.NetworkConst;

/**
 * Created by Misaki on 2017/8/6.
 */

public class SecondKillAdapter extends RemBaseAdapter<RSecondKill> {

    public SecondKillAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        SmartImageView smIv;
        TextView pointPriceTv;
        TextView allPriceTv;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.home_seckill_item, parent, false);
            holder = new ViewHolder();
            holder.smIv = (SmartImageView) contentView.findViewById(R.id.image_iv);
            holder.pointPriceTv = (TextView) contentView.findViewById(R.id.nowprice_tv);
            holder.allPriceTv = (TextView) contentView.findViewById(R.id.normalprice_tv);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        RSecondKill bean = mDatas.get(position);
        // TODO
        // holder.smIv.setImageUrl(NetworkConst.BASE_URL + bean.getIconUrl());
        holder.smIv.setImageUrl("http://mall.520it.com" + bean.getIconUrl());
        holder.pointPriceTv.setText("￥ " + bean.getPointPrice());
        holder.allPriceTv.setText(" ￥ " + bean.getAllPrice() + " ");
        holder.allPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);      // 中划线
        return contentView;
    }
}
