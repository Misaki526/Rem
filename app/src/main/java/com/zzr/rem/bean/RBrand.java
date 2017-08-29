package com.zzr.rem.bean;

/**
 * Created by Misaki on 2017/8/21.
 *
 * {
 *     "id": 品牌id,
 *     "name": "品牌名称"
 * }
 */

public class RBrand {
    private long id;
    private String name;

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
}
