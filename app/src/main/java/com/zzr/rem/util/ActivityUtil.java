package com.zzr.rem.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.zzr.rem.activity.BaseActivity;

/**
 * Created by Misaki on 2017/8/1.
 */

public class ActivityUtil {

    public static void start(Context c, Class<? extends BaseActivity> clazz, boolean ifFinishSelf) {
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);

        if (ifFinishSelf)
            ((Activity)c).finish();
    }
}
