package com.zzr.rem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.zzr.rem.R;
import com.zzr.rem.adapter.BrandAdapter;
import com.zzr.rem.adapter.ProductListAdapter;
import com.zzr.rem.bean.RBrand;
import com.zzr.rem.bean.RProductList;
import com.zzr.rem.bean.SProductList;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.CategoryController;
import com.zzr.rem.listener.IProductSortChanegListener;
import com.zzr.rem.ui.SubCategoryView;
import com.zzr.rem.ui.pop.ProductSortPopWindow;
import com.zzr.rem.util.FixedViewUtil;
import java.util.List;

/**
 * Created by Misaki on 2017/8/20.
 */

public class ProductListActivity extends BaseActivity implements View.OnClickListener, IProductSortChanegListener, AdapterView.OnItemClickListener {
    private long mCategoryId;    // 3级分类id
    private long mTopCategoryId; // 1级分类id
    private GridView mBrandGv;
    private BrandAdapter mBrandAdapter;
    private TextView mAllIndicatorTv;
    private TextView mSaleIndicatorTv;
    private TextView mPriceIndicatorTv;
    private TextView mChooseIndicatorTv;
    private ProductSortPopWindow mProductSortPop;
    private SProductList mSendArgs;
    private TextView mJDTakeTv;
    private TextView mPayWhenReceiveTv;
    private TextView mJustHasStockTv;
    private DrawerLayout mDrawerLayout;
    private View mSlideView;
    private EditText mMinPriceTv;
    private EditText mMaxPriceTv;
    private ListView mProductLv;
    private ProductListAdapter mAdapter;
    public static final String TODETAILSKEY = "TODETAILSKEY";

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.BRAND_ACTION_RESULT:
                handleBrand(msg);
                break;
            case IdiyMessage.PRODUCT_LIST_ACTION_RESULT:
                handleProductList(msg);
                break;
        }
    }

    // 处理品牌信息
    private void handleBrand(Message msg) {
        List<RBrand> datas = (List<RBrand>) msg.obj;

        if (datas.size() != 0) {
            mBrandAdapter.setDatas(datas);
            mBrandAdapter.notifyDataSetChanged();

            // 重置高度
            FixedViewUtil.setGridViewHeightBasedOnChildren(mBrandGv, 3);
        }
    }

    private void handleProductList(Message msg) {
        List<RProductList> datas = (List<RProductList>) msg.obj;

        if (datas.size() != 0) {
            mAdapter.setDatas(datas);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        initData();
        initController();
        initUI();

        mController.sendAsyncMessage(IdiyMessage.BRAND_ACTION, mTopCategoryId);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mCategoryId = intent.getLongExtra(SubCategoryView.THIRD_CATEGORY_ID, 0);
        mTopCategoryId = intent.getLongExtra(SubCategoryView.TOP_CATEGORY_ID, 0);

        if (mCategoryId == 0 || mTopCategoryId == 0) {
            tip("数据异常");
            finish();
        }
        mSendArgs = new SProductList();
        mSendArgs.setCategoryId(mCategoryId);
    }

    @Override
    protected void initController() {
        mController = new CategoryController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        initMainUI();
        initSlideUI();
    }

    private void initSlideUI() {
        mSlideView = findViewById(R.id.slide_view);
        mJDTakeTv = (TextView) findViewById(R.id.jd_take_tv);
        mJDTakeTv.setOnClickListener(this);
        mPayWhenReceiveTv = (TextView) findViewById(R.id.paywhenreceive_tv);
        mPayWhenReceiveTv.setOnClickListener(this);
        mJustHasStockTv = (TextView) findViewById(R.id.justhasstock_tv);
        mJustHasStockTv.setOnClickListener(this);

        mMinPriceTv = (EditText) findViewById(R.id.minPrice_et);
        mMaxPriceTv = (EditText) findViewById(R.id.maxPrice_et);

        mBrandGv = (GridView) findViewById(R.id.brand_gv);
        mBrandAdapter = new BrandAdapter(this);
        mBrandGv.setAdapter(mBrandAdapter);
        mBrandGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBrandAdapter.mCurrentTabPosition = position;
                mBrandAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 确定按钮
     */
    public void chooseSearchClick(View view) {
        // 1. 确定选择服务
        int deliverChoose = 0;
        if (mJDTakeTv.isSelected()) {
            deliverChoose += 1;
        }
        if (mPayWhenReceiveTv.isSelected()) {
            deliverChoose += 2;
        }
        if (mJustHasStockTv.isSelected()) {
            deliverChoose += 4;
        }
        mSendArgs.setDeliverChoose(deliverChoose);

        // 2. 确定价格区间
        String minPrice = mMinPriceTv.getText().toString().trim();
        String maxPrice = mMaxPriceTv.getText().toString().trim();
        if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
            mSendArgs.setMinPrice(Integer.parseInt(minPrice));
            mSendArgs.setMaxPrice(Integer.parseInt(maxPrice));
        }

        // 3. 确定品牌
        if (mBrandAdapter.mCurrentTabPosition != -1) {
            long brandId = mBrandAdapter.getItemId(mBrandAdapter.mCurrentTabPosition);
            mSendArgs.setBrandId(brandId);
        }

        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
        mDrawerLayout.closeDrawer(mSlideView);
    }

    /**
     * 重置按钮
     */
    public void resetClick(View view) {
        mSendArgs = new SProductList();
        mSendArgs.setCategoryId(mCategoryId);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
        mDrawerLayout.closeDrawer(mSlideView);
    }

    private void initMainUI() {
        mAllIndicatorTv = (TextView) findViewById(R.id.all_indicator);
        mAllIndicatorTv.setOnClickListener(this);
        mSaleIndicatorTv = (TextView) findViewById(R.id.sale_indicator);
        mSaleIndicatorTv.setOnClickListener(this);
        mPriceIndicatorTv = (TextView) findViewById(R.id.price_indicator);
        mPriceIndicatorTv.setOnClickListener(this);
        mChooseIndicatorTv = (TextView) findViewById(R.id.choose_indicator);
        mChooseIndicatorTv.setOnClickListener(this);

        mProductLv = (ListView) findViewById(R.id.product_lv);
        mAdapter = new ProductListAdapter(this);
        mProductLv.setAdapter(mAdapter);
        mProductLv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_indicator:
                if (mProductSortPop == null) {
                    mProductSortPop = new ProductSortPopWindow(this);
                    mProductSortPop.setListener(this);
                }
                mProductSortPop.onShow(view);
                break;
            case R.id.sale_indicator:
                mSendArgs.setSortType(1);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case R.id.price_indicator:
                int sortType = mSendArgs.getSortType();
                if (sortType == 0 || sortType == 1 || sortType == 3) {
                    mSendArgs.setSortType(2);
                } else {
                    mSendArgs.setSortType(3);
                }
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case R.id.choose_indicator:
                mDrawerLayout.openDrawer(mSlideView);
                break;
            case R.id.jd_take_tv:
            case R.id.paywhenreceive_tv:
            case R.id.justhasstock_tv:
                view.setSelected(!view.isSelected());
                break;
        }
    }

    @Override
    public void onSortChanged(int action) {
        switch (action) {
            case IProductSortChanegListener.ALLSORT:
                mAllIndicatorTv.setText("综合");
                mSendArgs.setFilterType(1);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case IProductSortChanegListener.NEWSSORT:
                mAllIndicatorTv.setText("新品");
                mSendArgs.setFilterType(2);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case IProductSortChanegListener.COMMENTSORT:
                mAllIndicatorTv.setText("评价");
                mSendArgs.setFilterType(3);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pid = mAdapter.getItemId(position);
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductListActivity.TODETAILSKEY, pid);
        startActivity(intent);
    }
}
