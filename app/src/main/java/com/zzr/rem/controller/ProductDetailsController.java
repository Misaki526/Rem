package com.zzr.rem.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zzr.rem.bean.RCommentCount;
import com.zzr.rem.bean.RGoodComment;
import com.zzr.rem.bean.RProductComment;
import com.zzr.rem.bean.RProductInfo;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.fragment.ProductCommentFragment;
import com.zzr.rem.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            case IdiyMessage.GOOD_COMMENT_ACTION:
                mListener.onModeChanged(IdiyMessage.GOOD_COMMENT_ACTION_RESULT, loadGoodComment((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_COUNT_ACTION:
                mListener.onModeChanged(IdiyMessage.GET_COMMENT_COUNT_ACTION_RESULT, loadCommentCount((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_ACTION:
                mListener.onModeChanged(IdiyMessage.GET_COMMENT_ACTION_RESULT, loadComment((Long) values[0], (Integer) values[1]));
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

    private List<RGoodComment> loadGoodComment(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        params.put("type", "1");          // 1是好评，2是中评，3是差评

        // TODO
        // String jsonStr = NetworkUtil.doGet(NetworkConst.PRODUCTCOMMENT_URL, params);
        String jsonStr = NetworkUtil.doPost("http://mall.520it.com/productComment", params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);

        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), RGoodComment.class);
        }
        return new ArrayList<RGoodComment>();
    }

    private RCommentCount loadCommentCount(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");

        // TODO
        // String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTCOUNT_URL, params);
        String jsonStr = NetworkUtil.doPost("http://mall.520it.com/commentCount", params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);

        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), RCommentCount.class);
        }
        return null;
    }

    private List<RProductComment> loadComment(long pid, int type) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        if (type == 4) {
            params.put("type", ProductCommentFragment.ALL_COMMENT + "");
            params.put("hasImgCom", "true");
        } else {
            params.put("type", type + "");
        }

        // TODO
        // String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTDETAIL_URL, params);
        String jsonStr = NetworkUtil.doPost("http://mall.520it.com/commentDetail", params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);

        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), RProductComment.class);
        }
        return new ArrayList<RProductComment>();
    }
}
