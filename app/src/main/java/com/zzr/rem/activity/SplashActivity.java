package com.zzr.rem.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.zzr.rem.R;
import com.zzr.rem.util.ActivityUtil;

/**
 * Created by Misaki on 2017/8/1.
 */

public class SplashActivity extends BaseActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
        alphaAnim();
    }

    private void alphaAnim() {
        AlphaAnimation anim = new AlphaAnimation(0.2f, 1.0f);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 启动登录
                ActivityUtil.start(SplashActivity.this, LoginActivity.class, true);
            }
        });

        anim.setDuration(3000);
        anim.setFillAfter(true);
        mIv.startAnimation(anim);
    }

    @Override
    protected void initUI() {
        mIv = (ImageView) findViewById(R.id.logo_iv);
    }
}
