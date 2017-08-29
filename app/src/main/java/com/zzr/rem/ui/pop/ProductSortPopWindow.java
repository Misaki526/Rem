package com.zzr.rem.ui.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.zzr.rem.R;
import com.zzr.rem.listener.IProductSortChanegListener;

/**
 * Created by Misaki on 2017/8/21.
 * 商品搜索弹出框
 */
public class ProductSortPopWindow extends PopWindowProtocal implements View.OnClickListener {
    private IProductSortChanegListener mListener;
    public void setListener(IProductSortChanegListener listener) {
        mListener = listener;
    }

    public ProductSortPopWindow(Context context) {
        super(context);
    }

    @Override
    protected void initUI() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null, false);
        contentView.findViewById(R.id.left_v).setOnClickListener(this);
        contentView.findViewById(R.id.all_sort).setOnClickListener(this);
        contentView.findViewById(R.id.new_sort).setOnClickListener(this);
        contentView.findViewById(R.id.comment_sort).setOnClickListener(this);

        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // 设置PopWindow内部控件能够点击
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.update();
    }

    @Override
    public void onShow(View anchor) {
        if (mPopWindow != null) {
            if (Build.VERSION.SDK_INT < 24) {
                mPopWindow.showAsDropDown(anchor, 0, 5);
            } else {
                // 适配android 7.0 以上版本
                int[] location = new int[2];
                anchor.getLocationOnScreen(location);
                mPopWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, 0, location[1] + anchor.getHeight() + 5);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.all_sort:
                    mListener.onSortChanged(IProductSortChanegListener.ALLSORT);
                    break;
                case R.id.new_sort:
                    mListener.onSortChanged(IProductSortChanegListener.NEWSSORT);
                    break;
                case R.id.comment_sort:
                    mListener.onSortChanged(IProductSortChanegListener.COMMENTSORT);
                    break;
            }
        }
        onDismiss();
    }
}
