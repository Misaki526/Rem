package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/1.
 *
 * 封装返回参数
 * 返回的json的格式统一如下：
 * {
 *     "success": true,           # 登录是否成功
 *     "errorMsg": "",            # 如果不成功，则为错误信息
 *     "result": { }              # 返回结果，如果不成功，则为空串
 * }
 */

public class RResult {
    private boolean success;
    private String errorMsg;
    private String result;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
