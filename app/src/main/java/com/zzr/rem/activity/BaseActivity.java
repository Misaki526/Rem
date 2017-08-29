package com.zzr.rem.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;
import com.zzr.rem.controller.BaseController;
import com.zzr.rem.listener.IModeChangeListener;

/**
 * Created by Misaki on 2017/8/1.
 */

public abstract class BaseActivity extends FragmentActivity implements IModeChangeListener {

    protected BaseController mController;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    };
    protected void handlerMessage(Message msg) {
        // 默认空实现，具体实现交给子类。不写成抽象方法是因为有些子类不需要回调
    }

    protected void initController() {
        // default empty implement
    }
    protected abstract void initUI();

    protected void initData() {
        // default empty implement
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void tip(String tipStr) {
        Toast.makeText(this, tipStr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onModeChanged(int action, Object... values) {
        // 此时在子线程中运行，不能修改直接UI
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
