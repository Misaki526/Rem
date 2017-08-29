package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/28.
 * 商品详情信息
 */

public class RProductInfo {
    private long id;                 // 商品id
    private String imgUrls;          // 所有的图片地址，json格式，"imgUrls":["/img/info/pp1.jpg", "/img/info/pp2.jpg"]
    private double price;
    private boolean ifSaleOneself;  // 是否自营
    private String name;              // 商品名称
    private int stockCount;          // 库存数
    private String typeList;          // 商品版本，json格式

    private int favcomRate;          // 好评率
    private int commentCount;        // 评论数
    private long recomProductId;    // 推荐商品id
    private String recomProduct;     // 推荐商品标题

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIfSaleOneself() {
        return ifSaleOneself;
    }

    public void setIfSaleOneself(boolean ifSaleOneself) {
        this.ifSaleOneself = ifSaleOneself;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public int getFavcomRate() {
        return favcomRate;
    }

    public void setFavcomRate(int favcomRate) {
        this.favcomRate = favcomRate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getRecomProductId() {
        return recomProductId;
    }

    public void setRecomProductId(long recomProductId) {
        this.recomProductId = recomProductId;
    }

    public String getRecomProduct() {
        return recomProduct;
    }

    public void setRecomProduct(String recomProduct) {
        this.recomProduct = recomProduct;
    }
}
