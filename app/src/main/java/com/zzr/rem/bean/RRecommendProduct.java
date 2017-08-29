package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/7.
 *
 * {
 *     "success": true,
 *     "errorMsg": "",
 *     "result": {
 *         "total": "商品总数",
 *         "rows":[{
 *              "price": 商品价格,
 *              "name": "商品名称",
 *              "iconUrl": "商品图片路径",
 *              "productId": 商品id
 *         }]
 *     }
 */

public class RRecommendProduct {
    private long productId;
    private double price;
    private String name;
    private String iconUrl;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
