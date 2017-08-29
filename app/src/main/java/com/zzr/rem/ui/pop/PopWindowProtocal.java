package com.zzr.rem.ui.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Misaki on 2017/8/21.
 */

public abstract class PopWindowProtocal {
    protected PopupWindow mPopWindow;
    protected Context mContext;

    public PopWindowProtocal(Context context) {
        mContext = context;
        initUI();
    }

    protected abstract void initUI();

    public abstract void onShow(View anchor);

    public void onDismiss() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }
}
