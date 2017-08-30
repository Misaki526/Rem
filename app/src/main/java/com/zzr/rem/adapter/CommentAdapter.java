package com.zzr.rem.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.bean.RGoodComment;
import com.zzr.rem.bean.RProductComment;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.ui.RatingBar;

import java.util.List;

/**
 * Created by Misaki on 2017/8/6.
 */

public class CommentAdapter extends RemBaseAdapter<RProductComment> {

    public CommentAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        SmartImageView userIconIv;
        TextView userNameTv;
        TextView commentTimeTv;
        RatingBar commentLevelRb;
        TextView commentTv;
        LinearLayout imageContainerLl;
        TextView buyTimeTv;
        TextView buyVersionTv;
        TextView lovecountTv;
        TextView subcommentTv;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder = null;

        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.comment_item_view, parent, false);
            holder = new ViewHolder();
            holder.userIconIv = (SmartImageView) contentView.findViewById(R.id.icon_iv);
            holder.userNameTv = (TextView) contentView.findViewById(R.id.name_tv);
            holder.commentTimeTv = (TextView) contentView.findViewById(R.id.time_tv);
            holder.commentLevelRb = (RatingBar) contentView.findViewById(R.id.rating_bar);
            holder.commentTv = (TextView) contentView.findViewById(R.id.content_tv);
            holder.imageContainerLl = (LinearLayout) contentView.findViewById(R.id.images_container);
            holder.buyTimeTv = (TextView) contentView.findViewById(R.id.buytime_tv);
            holder.buyVersionTv = (TextView) contentView.findViewById(R.id.buyversion_tv);
            holder.lovecountTv = (TextView) contentView.findViewById(R.id.lovecount_tv);
            holder.subcommentTv = (TextView) contentView.findViewById(R.id.subcomment_tv);

            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        RProductComment bean = mDatas.get(position);
        // TODO
        // holder.userIconIv.setImageUrl(NetworkConst.BASE_URL + bean.getUserImg());
        holder.userIconIv.setImageUrl("http://mall.520it.com" + bean.getUserImg());
        holder.userNameTv.setText(bean.getUserName());
        holder.commentTimeTv.setText(bean.getCommentTime());
        holder.commentLevelRb.setRating(bean.getRate());
        holder.commentTv.setText(bean.getComment());
        holder.buyTimeTv.setText("购买时间：" + bean.getBuyTime());
        holder.buyVersionTv.setText("型号：" + bean.getProductType());

        initImageContainer(holder.imageContainerLl, bean.getImgUrls());

        holder.lovecountTv.setText("喜欢（" + bean.getLoveCount() + "）");
        holder.subcommentTv.setText("回复（" + bean.getSubComment() + "）");

        return contentView;
    }

    private void initImageContainer(LinearLayout containerLl, String imageUrlJson) {
        int childCount = containerLl.getChildCount();
        List<String> imgUrls = JSON.parseArray(imageUrlJson, String.class);
        int dataSize = imgUrls.size();
        int realSize = Math.min(childCount, dataSize);

        // 清空老数据
        for (int i = 0; i < childCount; i++) {
            SmartImageView smiv = (SmartImageView) containerLl.getChildAt(i);
            smiv.setImageDrawable(new BitmapDrawable());
        }

        // 设置新数据
        for (int i = 0; i < realSize; i++) {
            SmartImageView smiv = (SmartImageView) containerLl.getChildAt(i);
            // TODO
            // smiv.setImageUrl(NetworkConst.BASE_URL + imgUrls.get(i));
            smiv.setImageUrl("http://mall.520it.com" + imgUrls.get(i));
        }
        containerLl.setVisibility(realSize > 0 ? View.VISIBLE : View.GONE);
    }
}
