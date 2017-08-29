package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/6.
 *
 * {
 *     "success": true,
 *     "errorMsg": "",
 *     "result": {
 *         "total": "商品总数",
 *         "rows":[{
 *              "allPrice": "原价",
 *              "pointPrice": "秒杀价格",
 *              "iconUrl": "商品图片路径",
 *              "timeLeft": 秒杀剩余时间（分钟）,
 *              "type": 秒杀类型（1为年货，2为超值，3为热卖）,
 *              "productId": 商品id
 *         }]
 *     }
 * }
 */

public class RSecondKill {
    private long productId;
    private double allPrice;
    private double pointPrice;
    private String iconUrl;
    private int timeLeft;
    private int type;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public double getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(double pointPrice) {
        this.pointPrice = pointPrice;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
