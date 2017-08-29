package com.zzr.rem;

import android.app.Application;

import com.zzr.rem.bean.RLoginResult;

/**
 * Created by Misaki on 2017/8/3.
 */

public class RemApplication extends Application {

    public RLoginResult mRLoginResult;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setRLoginResult(RLoginResult rLoginResult) {
        mRLoginResult = rLoginResult;
    }
}
