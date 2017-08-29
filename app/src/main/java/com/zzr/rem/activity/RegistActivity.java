package com.zzr.rem.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.zzr.rem.R;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.UserController;

/**
 * Created by Misaki on 2017/8/1.
 */

public class RegistActivity extends BaseActivity{

    private EditText mNameEt;
    private EditText mPwdEt;
    private EditText mSurePwdEt;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.REGIST_ACTION_RESULT:
                handleRegistResult(msg);
                break;
        }
    }

    private void handleRegistResult(Message msg) {
        RResult rResult = (RResult) msg.obj;
        if (rResult.isSuccess()) {
            // 注册成功，跳到登录界面
            tip("注册成功~");
            finish();
        } else {
            tip("注册失败：" + rResult.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initController();
        initUI();
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = (EditText) findViewById(R.id.username_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
        mSurePwdEt = (EditText) findViewById(R.id.surepwd_et);
    }

    /**
     * 点击注册
     */
    public void registClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        String surePwd = mSurePwdEt.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(surePwd)) {
            tip("请输入完整的信息~");
            return;
        }

        if (!pwd.equals(surePwd)) {
            tip("两次密码不一致~");
            return;
        }

        // 注册用户
        mController.sendAsyncMessage(IdiyMessage.REGIST_ACTION, name, pwd);
    }
}
