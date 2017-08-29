package com.zzr.rem.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zzr.rem.R;
import com.zzr.rem.fragment.CategoryFragment;
import com.zzr.rem.fragment.HomeFragment;
import com.zzr.rem.fragment.MineFragment;
import com.zzr.rem.fragment.ShopcarFragment;
import com.zzr.rem.listener.IBottomBarClickListener;
import com.zzr.rem.ui.BottomBar;

public class MainActivity extends BaseActivity implements IBottomBarClickListener {

    private BottomBar mBottomBar;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        initController();
        initUI();
    }

    @Override
    protected void initController() {

    }

    @Override
    protected void initUI() {
        // 初始化底部栏
        mBottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        mBottomBar.setIBottomBarClickListener(this);

        // 初始化Fragment，默认选中首页
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.top_bar, new HomeFragment());
        transaction.commit();
    }

    // 切换Fragment
    @Override
    public void onItemClick(int action) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        switch (action) {
            case R.id.frag_main_ll:
                transaction.replace(R.id.top_bar, new HomeFragment());
                break;
            case R.id.frag_category_ll:
                transaction.replace(R.id.top_bar, new CategoryFragment());
                break;
            case R.id.frag_shopcar_ll:
                transaction.replace(R.id.top_bar, new ShopcarFragment());
                break;
            case R.id.frag_mine_ll:
                transaction.replace(R.id.top_bar, new MineFragment());
                break;
        }

        transaction.commit();
    }
}
