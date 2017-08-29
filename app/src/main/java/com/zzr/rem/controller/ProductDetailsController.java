package com.zzr.rem.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zzr.rem.bean.RProductInfo;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.util.NetworkUtil;

/**
 * Created by Misaki on 2017/8/28.
 */

public class ProductDetailsController extends BaseController {

    public ProductDetailsController(Context context) {
        super(context);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.PRODUCT_INFO_ACTION:
                mListener.onModeChanged(IdiyMessage.PRODUCT_INFO_ACTION_RESULT, loadProductInfo((Long) values[0]));
                break;

        }
    }

    private RProductInfo loadProductInfo(long pid) {
        // TODO
        // String jsonStr = NetworkUtil.doGet(NetworkConst.PRODUCTINFO_URL + "?id=" + pid);
        String jsonStr = NetworkUtil.doGet("http://mall.520it.com/productInfo?id=" + pid);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);

        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), RProductInfo.class);
        }
        return null;
    }
}
