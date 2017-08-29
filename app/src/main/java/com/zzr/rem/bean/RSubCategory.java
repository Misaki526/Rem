package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/8.
 */

public class RSubCategory {
    private long id;       // 2级分类id
    private String name;   // 2级分类名称
    private String thirdCategory;  // 3级分类的所有数据

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        this.thirdCategory = thirdCategory;
    }

}
