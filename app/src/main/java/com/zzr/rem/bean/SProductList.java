package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/21.
 * 发送参数
 */

public class SProductList {
    private long categoryId;       // 分类id
    private int filterType = 1;    // 排序类型（1-综合  2-新品   3-评价）
    private int sortType;          // 排序条件（1-销量  2-价格从高到低   3-价格从低到高）
    private int deliverChoose;    // 选择类型（0-无选择   1-京东配送   2-货到付款   4-仅看有货   3-1+2   5-1+4   6-2+4）
    private int minPrice;         // 最低价格
    private int maxPrice;         // 最高价格
    private long brandId;         // 品牌id

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getDeliverChoose() {
        return deliverChoose;
    }

    public void setDeliverChoose(int deliverChoose) {
        this.deliverChoose = deliverChoose;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "SProductList{" +
                "categoryId=" + categoryId +
                ", filterType=" + filterType +
                ", sortType=" + sortType +
                ", deliverChoose=" + deliverChoose +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", brandId=" + brandId +
                '}';
    }
}
