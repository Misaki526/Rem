package com.zzr.rem.controller;

import android.content.Context;

import com.zzr.rem.listener.IModeChangeListener;

/**
 * Created by Misaki on 2017/8/1.
 */

public abstract class BaseController {

    protected Context mContext;
    protected IModeChangeListener mListener;

    public BaseController(Context context) {
        mContext = context;
    }

    public void setIModeChangeListener(IModeChangeListener listener) {
        mListener = listener;
    }

    /**
     * @param action 一个页面可能有多个网络请求，action用来区别请求，具体可见IdiyMessage
     * @param values 请求数据
     */
    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            @Override
            public void run() {
                handleMessage(action, values);
            }
        }.start();
    }

    /**
     * 子类处理具体需求的业务代码
     */
    protected abstract void handleMessage(int action, Object... values);
}
