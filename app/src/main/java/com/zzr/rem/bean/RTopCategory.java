package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/7.
 *
 * {
 *     "success": true,
 *     "errorMsg": "",
 *     "result": [{
 *          "id": 分类id,
 *          "bannerUrl": "分类图片路径",
 *          "name": "分类名称"
 *     }]
 * }
 */

public class RTopCategory {
    private long id;
    private String bannerUrl;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
