package com.zzr.rem.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zzr.rem.R;
import com.zzr.rem.listener.IBottomBarClickListener;

/**
 * Created by Misaki on 2017/8/2.
 */

public class BottomBar extends LinearLayout implements OnClickListener {
    private ImageView mHomeIv;
    private TextView mHomeTv;
    private ImageView mCategoryIv;
    private TextView mCategoryTv;
    private ImageView mShopcarIv;
    private TextView mShopcarTv;
    private ImageView mMineIv;
    private TextView mMineTv;
    private IBottomBarClickListener mListener;
    private int mCurrentId;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIBottomBarClickListener(IBottomBarClickListener listener) {
        mListener = listener;
    }

    /**
     * 当所有控件测量排布后，调用该方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);

        mHomeIv = (ImageView) findViewById(R.id.frag_main_iv);
        mHomeTv = (TextView) findViewById(R.id.frag_main);
        mCategoryIv = (ImageView) findViewById(R.id.frag_category_iv);
        mCategoryTv = (TextView) findViewById(R.id.frag_category);
        mShopcarIv = (ImageView) findViewById(R.id.frag_shopcar_iv);
        mShopcarTv = (TextView) findViewById(R.id.frag_shopcar);
        mMineIv = (ImageView) findViewById(R.id.frag_mine_iv);
        mMineTv = (TextView) findViewById(R.id.frag_mine);

        setFontType(mHomeTv);
        setFontType(mCategoryTv);
        setFontType(mShopcarTv);
        setFontType(mMineTv);

        // 默认选中首页
        findViewById(R.id.frag_main_ll).performClick();
    }

    // 修改字体
    private void setFontType(TextView tv) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font.ttf");
        tv.setTypeface(tf);
    }

    @Override
    public void onClick(View view) {
        // 避免重复切换
        if (mCurrentId == view.getId()) {
            return;
        }

        mHomeIv.setSelected(view.getId() == R.id.frag_main_ll);
        mHomeTv.setSelected(view.getId() == R.id.frag_main_ll);
        mCategoryIv.setSelected(view.getId() == R.id.frag_category_ll);
        mCategoryTv.setSelected(view.getId() == R.id.frag_category_ll);
        mShopcarIv.setSelected(view.getId() == R.id.frag_shopcar_ll);
        mShopcarTv.setSelected(view.getId() == R.id.frag_shopcar_ll);
        mMineIv.setSelected(view.getId() == R.id.frag_mine_ll);
        mMineTv.setSelected(view.getId() == R.id.frag_mine_ll);

        if (mListener != null) {
            mListener.onItemClick(view.getId());
            mCurrentId = view.getId();
        }
    }

}
