package com.zzr.rem.controller;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.zzr.rem.bean.Banner;
import com.zzr.rem.bean.RRecommendProduct;
import com.zzr.rem.bean.RResult;
import com.zzr.rem.bean.RSecondKill;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.cons.NetworkConst;
import com.zzr.rem.util.NetworkUtil;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misaki on 2017/8/4.
 */

public class HomeController extends BaseController {

    public HomeController(Context context) {
        super(context);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.GET_BANNER_ACTION:
                mListener.onModeChanged(IdiyMessage.GET_BANNER_ACTION_RESULT, loadBanner((Integer) values[0]));
                break;
            case IdiyMessage.SECOND_KILL_ACTION:
                mListener.onModeChanged(IdiyMessage.SECOND_KILL_ACTION_RESULT, loadSecondKill());
                break;
            case IdiyMessage.RECOMMEND_PRODUCT_ACTION:
                mListener.onModeChanged(IdiyMessage.RECOMMEND_PRODUCT_ACTION_RESULT, loadRecommendProduct());
                break;
        }
    }

    private List<Banner> loadBanner(int type) {
        List<Banner> result = new ArrayList<Banner>();

        // TODO 后面再把网络请求加上
        // String jsonStr = NetworkUtil.doGet(NetworkConst.BANNER_URL + "?adKind=" + type);
        // String jsonStr = "{ 'success':true, 'errorMsg':'', 'result':[{'id':1, 'type':1, 'adUrl':'xxx', 'webUrl':'xxx', 'adKind':1}, {'id':2, 'type':1, 'adUrl':'xxx', 'webUrl':'xxx', 'adKind':1}] }";
        String jsonStr = NetworkUtil.doGet("http://mall.520it.com/banner?adKind=" + type);

        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Banner.class);
        }
        return result;
    }

    private List<RSecondKill> loadSecondKill() {
        // TODO 后面再把网络请求加上
        // String jsonStr = NetworkUtil.doGet(NetworkConst.SECKILL_URL);
        String jsonStr = NetworkUtil.doGet("http://mall.520it.com/seckill");

        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObj = new JSONObject(resultBean.getResult());     // result中：是total和rows
                String rowsJSON = jsonObj.getString("rows");
                return JSON.parseArray(rowsJSON, RSecondKill.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RSecondKill>();
    }

    private List<RRecommendProduct> loadRecommendProduct() {
        // TODO 后面再把网络请求加上
        // String jsonStr = NetworkUtil.doGet(NetworkConst.GETYOURFAV_URL);
        String jsonStr = NetworkUtil.doGet("http://mall.520it.com/getYourFav");

        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObj = new JSONObject(resultBean.getResult());
                String rowsJSON = jsonObj.getString("rows");
                return JSON.parseArray(rowsJSON, RRecommendProduct.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RRecommendProduct>();
    }
}
