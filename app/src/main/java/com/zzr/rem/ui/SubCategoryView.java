package com.zzr.rem.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.loopj.android.image.SmartImageView;
import com.zzr.rem.R;
import com.zzr.rem.activity.ProductListActivity;
import com.zzr.rem.bean.RSubCategory;
import com.zzr.rem.bean.RTopCategory;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.controller.CategoryController;
import com.zzr.rem.listener.IModeChangeListener;
import com.zzr.rem.listener.IViewContainer;

import java.util.List;

public class SubCategoryView extends FlexiScrollView implements IViewContainer, IModeChangeListener, View.OnClickListener {

    private RTopCategory mTopCategoryBean;
    private LinearLayout mContainerLl;
    private CategoryController mController;
    private static final int sLinePerSize = 3;  // 每行3个
    public static String THIRD_CATEGORY_ID = "THIRD_CATEGORY_ID";
    public static String TOP_CATEGORY_ID = "TOP_CATEGORY_ID";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.SUBCATEGORY_ACTION_RESULT:
                    handleSubCategory(msg);
                    break;
            }
        }
    };

    private void handleSubCategory(Message msg) {
        List<RSubCategory> datas = (List<RSubCategory>) msg.obj;

        for (int i = 0; i < datas.size(); i++) {
            // 添加2级分类名称
            initSecondCategoryNameTv(datas, i);

            // 添加3级分类
            RSubCategory secondCategory = datas.get(i);
            List<RTopCategory> thirdCategories = JSON.parseArray(secondCategory.getThirdCategory(), RTopCategory.class);// 3级分类和1级分类的数据格式一样
            // 计算行数
            int totalSize = thirdCategories.size();
            int lines = (totalSize % sLinePerSize == 0) ? totalSize / sLinePerSize : totalSize / sLinePerSize + 1;

            for (int j = 0; j < lines; j++) {
                // 行的容器
                LinearLayout lineLl = new LinearLayout(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 10, 0, 0);
                lineLl.setLayoutParams(params);
                mContainerLl.addView(lineLl);

                // 每行最多3列数据
                for (int k = 0; k < 3; k++) {
                    if (sLinePerSize * j + k < totalSize) {
                        initThirdCategoryItem(thirdCategories, sLinePerSize * j + k, lineLl);
                    }
                }
            }
        }
    }

    private void initSecondCategoryNameTv(List<RSubCategory> datas, int i) {
        TextView secondCategoryNameTv = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 16, 0, 4);
        secondCategoryNameTv.setLayoutParams(params);
        secondCategoryNameTv.setText(datas.get(i).getName());
        mContainerLl.addView(secondCategoryNameTv);
    }

    private void initThirdCategoryItem(List<RTopCategory> thirdCategories, int index, LinearLayout lineLl) {
        RTopCategory thirdCategory = thirdCategories.get(index);
        // 创建列到lineL1
        LinearLayout columnLl = new LinearLayout(getContext());
        LinearLayout.LayoutParams columnParams = new LinearLayout.LayoutParams((getWidth() - 16) / 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        columnLl.setLayoutParams(columnParams);
        columnLl.setOrientation(LinearLayout.VERTICAL);
        columnLl.setGravity(Gravity.CENTER_HORIZONTAL);
        lineLl.addView(columnLl);
        columnLl.setOnClickListener(this);
        columnLl.setTag(thirdCategory); // 绑定参数

        // 往容器里添加图片
        // TODO
        // String imageUrlPath = NetworkConst.BASE_URL + thirdCategory.getBannerUrl();
        String imageUrlPath = "http://mall.520it.com" + thirdCategory.getBannerUrl();
        SmartImageView bannerIv = new SmartImageView(getContext());
        LinearLayout.LayoutParams bannerIvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (getWidth() - 16) / 3
        );
        bannerIv.setLayoutParams(bannerIvParams);
        bannerIv.setImageUrl(imageUrlPath);
        columnLl.addView(bannerIv);

        // 往容器里添加文本
        TextView thirdCategoryNameTv = new TextView(getContext());
        LinearLayout.LayoutParams thirdCategoryNameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        thirdCategoryNameTv.setLayoutParams(thirdCategoryNameParams);
        thirdCategoryNameTv.setText(thirdCategory.getName());
        thirdCategoryNameTv.setTextSize(12);
        columnLl.addView(thirdCategoryNameTv);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initController();
        initUI();
    }

    private void initController() {
        mController = new CategoryController(getContext());
        mController.setIModeChangeListener(this);
    }

    private void initUI() {
        mContainerLl = (LinearLayout) findViewById(R.id.child_container_ll);
    }

    @Override
	public void onShow(Object... values) {
        mTopCategoryBean = (RTopCategory) values[0];
        // 1. 清空容器
        mContainerLl.removeAllViews();
        // 2. 往容器里添加广告图片
        initBannerIv();
        // 3. 请求二级、三级分类中的数据
        mController.sendAsyncMessage(IdiyMessage.SUBCATEGORY_ACTION, mTopCategoryBean.getId());
	}

    private void initBannerIv() {
        // TODO
        // String imageUrlPath = NetworkConst.BASE_URL + mTopCategoryBean.getBannerUrl();
        String imageUrlPath = "http://mall.520it.com" + mTopCategoryBean.getBannerUrl();
        SmartImageView bannerIv = new SmartImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 8, 8, 8);
        bannerIv.setLayoutParams(params);
        bannerIv.setScaleType(ImageView.ScaleType.FIT_XY);
        bannerIv.setImageUrl(imageUrlPath);
        mContainerLl.addView(bannerIv);
    }

    @Override
    public void onModeChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View view) {
        RTopCategory thirdCategory = (RTopCategory) view.getTag();
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        intent.putExtra(SubCategoryView.THIRD_CATEGORY_ID, thirdCategory.getId());
        intent.putExtra(SubCategoryView.TOP_CATEGORY_ID, mTopCategoryBean.getId());
        getContext().startActivity(intent);
    }
}
