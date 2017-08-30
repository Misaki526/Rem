package com.zzr.rem.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class RGoodComment {
    private String imgUrls;     // 图片路径，JSON格式
    private String time;        // 评论时间
    private int rate;           // 商品评级
    private String userName;    // 评论用户
    private int type;           // 评论类型，1好评，2中评，3差评
    private String comment;     // 评论内容

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
