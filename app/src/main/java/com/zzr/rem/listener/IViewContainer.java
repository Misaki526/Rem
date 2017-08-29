package com.zzr.rem.listener;

/**
 * Created by Misaki on 2017/8/7.
 *
 * 只要是容器都可以实现它
 */

public interface IViewContainer {

    // 让容器显示界面
    public void onShow(Object... values);
}
