package com.zzr.rem.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zzr.rem.R;
import com.zzr.rem.RemApplication;
import com.zzr.rem.bean.RLoginResult;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.UserController;
import com.zzr.rem.util.ActivityUtil;
import com.zzr.rem.db.UserDao.UserInfo;

/**
 * Created by Misaki on 2017/8/1.
 */

public class LoginActivity extends BaseActivity {

    private EditText mNameEt;
    private EditText mPwdEt;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                handleLoginResult(msg);
                break;
            case IdiyMessage.SAVE_USERTODB_RESULT:
                handleSaveUser2Db(msg);
                break;
            case IdiyMessage.GET_USER_ACTION_RESULT:
                handleGetUser(msg);
                break;
        }
    }

    private void handleLoginResult(Message msg) {
        RResult rResult = (RResult) msg.obj;
        if (rResult.isSuccess()) {
            // 登录成功
            // 1. 将账号密码保存到数据库（数据库操作也是耗时操作，尽量创建子线程）
            String name = mNameEt.getText().toString();
            String pwd = mPwdEt.getText().toString();
            mController.sendAsyncMessage(IdiyMessage.SAVE_USERTODB, name, pwd);

            // 2. 将用户的信息保存到Application中
            RLoginResult bean = JSON.parseObject(rResult.getResult(), RLoginResult.class);
            ((RemApplication)getApplication()).setRLoginResult(bean);
        } else {
            tip("登录失败：" + rResult.getErrorMsg());
        }
    }

    private void handleSaveUser2Db(Message msg) {
        boolean ifSuccess = (boolean) msg.obj;
        if (ifSuccess) {
            ActivityUtil.start(this, MainActivity.class , true);
        } else {
            tip("登录异常~");
        }
    }

    private void handleGetUser(Message msg) {
        if (msg.obj != null) {
            UserInfo userInfo = (UserInfo) msg.obj;
            mNameEt.setText(userInfo.username);
            mPwdEt.setText(userInfo.pwd);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initController();
        initUI();
    }

    @Override
    protected void initUI() {
        mNameEt = (EditText) findViewById(R.id.name_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);

        // 如果数据库表中有登录数据，则将账号密码读取出来
        mController.sendAsyncMessage(IdiyMessage.GET_USER_ACTION, 0);
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModeChangeListener(this);
    }

    /**
     * 登录按钮
     */
    public void loginClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            tip("请输入账号密码");
            return;
        }

        // 发送网络请求，请求网络数据
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);
    }

    /**
     * 注册按钮
     */
    public void registClick(View v) {
        ActivityUtil.start(this, RegistActivity.class, false);
    }
}
