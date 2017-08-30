package com.zzr.rem.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RGoodComment;
import com.zzr.rem.bean.RSecondKill;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.ui.RatingBar;

import java.util.List;

/**
 * Created by Misaki on 2017/8/6.
 */

public class GoodCommentAdapter extends RemBaseAdapter<RGoodComment> {

    public GoodCommentAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        RatingBar commentLevelRb;
        TextView nameTv;
        TextView commentContentTv;
        LinearLayout imageContainerLl;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.good_comment_item_view, parent, false);
            holder = new ViewHolder();
            holder.commentLevelRb = (RatingBar) contentView.findViewById(R.id.rating_bar);
            holder.nameTv = (TextView) contentView.findViewById(R.id.name_tv);
            holder.commentContentTv = (TextView) contentView.findViewById(R.id.content_tv);
            holder.imageContainerLl = (LinearLayout) contentView.findViewById(R.id.images_container);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        RGoodComment bean = mDatas.get(position);
        holder.commentLevelRb.setRating(bean.getRate());
        holder.nameTv.setText(bean.getUserName());
        holder.commentContentTv.setText(bean.getComment());
        initImageContainer(holder, bean);

        return contentView;
    }

    private void initImageContainer(ViewHolder holder, RGoodComment bean) {
        int childCount = holder.imageContainerLl.getChildCount();
        List<String> imgUrls = JSON.parseArray(bean.getImgUrls(), String.class);
        int dataSize = imgUrls.size();
        int realSize = Math.min(childCount, dataSize);

        // 清空老数据
        for (int i = 0; i < childCount; i++) {
            SmartImageView smiv = (SmartImageView) holder.imageContainerLl.getChildAt(i);
            smiv.setImageDrawable(new BitmapDrawable());
        }

        // 设置新数据
        for (int i = 0; i < realSize; i++) {
            SmartImageView smiv = (SmartImageView) holder.imageContainerLl.getChildAt(i);
            // TODO
            // smiv.setImageUrl(NetworkConst.BASE_URL + imgUrls.get(i));
            smiv.setImageUrl("http://mall.520it.com" + imgUrls.get(i));
        }
        holder.imageContainerLl.setVisibility(realSize > 0 ? View.VISIBLE : View.GONE);
    }
}
