package com.zzr.rem.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.zzr.rem.R;
import com.zzr.rem.adapter.TopCategoryAdapter;
import com.zzr.rem.bean.RTopCategory;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.CategoryController;
import com.zzr.rem.ui.SubCategoryView;

import java.util.List;

/**
 * Created by Misaki on 2017/8/2.
 */

public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView mTopCategoryLv;
    private TopCategoryAdapter mTopCategoryAdapter;
    private SubCategoryView mSubCategoryView;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.TOPCATEGORY_ACTION_RESULT:
                handleTopCategory(msg);
                break;
        }
    }

    private void handleTopCategory(Message msg) {
        List<RTopCategory> datas = (List<RTopCategory>) msg.obj;

        if (datas.size() != 0) {
            mTopCategoryAdapter.setDatas(datas);
            mTopCategoryAdapter.notifyDataSetChanged();
            mTopCategoryLv.performItemClick(null, 0, 0);    // 默认点击第一个
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initController();
        initUI();
        mController.sendAsyncMessage(IdiyMessage.TOPCATEGORY_ACTION, 0);
    }

    @Override
    protected void initController() {
        mController = new CategoryController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mTopCategoryLv = (ListView) getActivity().findViewById(R.id.top_lv);
        mTopCategoryAdapter = new TopCategoryAdapter(getActivity());
        mTopCategoryLv.setAdapter(mTopCategoryAdapter);
        mTopCategoryLv.setOnItemClickListener(this);

        mSubCategoryView = (SubCategoryView) getActivity().findViewById(R.id.subcategory);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 1. 记录当前哪个item被点击
        mTopCategoryAdapter.mCurrentTabPosition = position;

        // 2. 修改样式，刷新列表
        mTopCategoryAdapter.notifyDataSetChanged();

        // 将数据添加到SubCategoryView
        RTopCategory topCategoryBean = (RTopCategory) mTopCategoryAdapter.getItem(position);
        mSubCategoryView.onShow(topCategoryBean);
    }
}
