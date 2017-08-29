package com.zzr.rem.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.zzr.rem.controller.BaseController;
import com.zzr.rem.listener.IModeChangeListener;

/**
 * Created by Misaki on 2017/8/2.
 */

public abstract class BaseFragment extends Fragment implements IModeChangeListener {

    /**
     * 以下代码为子线程网络有关代码
     */

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

    @Override
    public void onModeChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    public void tip(String tipStr) {
        Toast.makeText(getActivity(), tipStr, Toast.LENGTH_SHORT).show();
    }
}
