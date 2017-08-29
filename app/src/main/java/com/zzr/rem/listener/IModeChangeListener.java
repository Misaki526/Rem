package com.zzr.rem.listener;

import com.zzr.rem.bean.RResult;

/**
 * Created by Misaki on 2017/8/1.
 */

public interface IModeChangeListener {

    /**
     * 告诉UI，界面需要修改
     * @param action 返回处理不同UI的action
     */
    public void onModeChanged(int action, Object... values);

}
