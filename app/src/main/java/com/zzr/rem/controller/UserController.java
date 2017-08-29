package com.zzr.rem.controller;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.db.UserDao;
import com.zzr.rem.util.AESUtils;
import com.zzr.rem.util.NetworkUtil;
import java.util.HashMap;
import com.zzr.rem.db.UserDao.UserInfo;

/**
 * Created by Misaki on 2017/8/1.
 */

public class UserController extends BaseController {

    private UserDao mUserDao;

    public UserController(Context context) {
        super(context);
        mUserDao = new UserDao(mContext);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        // 登录请求
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                RResult rResultLogin = login((String) values[0], (String) values[1]);
                // 告诉Activity数据加载完毕
                mListener.onModeChanged(IdiyMessage.LOGIN_ACTION_RESULT, rResultLogin);
                break;
            case IdiyMessage.REGIST_ACTION:
                RResult rResultRegist = regist((String) values[0], (String) values[1]);
                mListener.onModeChanged(IdiyMessage.REGIST_ACTION_RESULT, rResultRegist);
                break;
            case IdiyMessage.SAVE_USERTODB:
                boolean saveUser2Db = saveUser2Db((String) values[0], (String) values[1]);
                mListener.onModeChanged(IdiyMessage.SAVE_USERTODB_RESULT, saveUser2Db);
                break;
            case IdiyMessage.GET_USER_ACTION:
                UserInfo userInfo = aquireUser();
                mListener.onModeChanged(IdiyMessage.GET_USER_ACTION_RESULT, userInfo);
                break;
            case IdiyMessage.CLEAR_USER_ACTION:
                clearUser();
                mListener.onModeChanged(IdiyMessage.CLEAR_USER_ACTION_RESULT, 0);
                break;
        }
    }

    private RResult login(String username, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("pwd", pwd);

        // TODO 后面再把网络请求加上
        // String jsonStr = NetworkUtil.doPost(NetworkConst.LOGIN_URL, params);
        String jsonStr = "{ 'success':true, 'errorMsg':'', 'result':{'id':1, 'userName':'admin', 'userIcon':'aa.png', 'waitPayCount':1 , 'waitReceiveCount':1 , 'userLevel':1} }";
        // String jsonStr = "{ 'success':false, 'errorMsg':'用户名或密码错误~', 'result': {} }";

        // 处理json
        return JSON.parseObject(jsonStr, RResult.class);
    }

    private RResult regist(String username, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("pwd", pwd);

        // TODO 后面再把网络请求加上
        // String jsonStr = NetworkUtil.doPost(NetworkConst.REGIST_URL, params);
        String jsonStr = "{ 'success':true, 'errorMsg':'', 'result':{'id':1} }";
        // String jsonStr = "{ 'success':false, 'errorMsg':'用户名已经存在~', 'result': {} }";

        return JSON.parseObject(jsonStr, RResult.class);
    }

    private boolean saveUser2Db(String username, String pwd) {
        mUserDao.clearUsers();

        // 可逆性加密
        try {
            username = AESUtils.encrypt(username);
            pwd = AESUtils.encrypt(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mUserDao.saveUser(username, pwd);
    }

    private UserInfo aquireUser() {
        UserInfo userInfo = mUserDao.aquireLastestUser();

        if (userInfo != null) {
            try {
                userInfo.username = AESUtils.decrypt(userInfo.username);
                userInfo.pwd = AESUtils.decrypt(userInfo.pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userInfo;
        }

        return null;
    }

    private void clearUser() {
        mUserDao.clearUsers();
    }
}
