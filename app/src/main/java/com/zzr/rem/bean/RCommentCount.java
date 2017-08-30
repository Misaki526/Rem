package com.zzr.rem.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class RCommentCount {
    private int allComment;
    private int negativeCom;
    private int moderateCom;
    private int positiveCom;
    private int hasImgCom;

    public int getAllComment() {
        return allComment;
    }

    public void setAllComment(int allComment) {
        this.allComment = allComment;
    }

    public int getNegativeCom() {
        return negativeCom;
    }

    public void setNegativeCom(int nagativeCom) {
        this.negativeCom = nagativeCom;
    }

    public int getModerateCom() {
        return moderateCom;
    }

    public void setModerateCom(int moderateCom) {
        this.moderateCom = moderateCom;
    }

    public int getPositiveCom() {
        return positiveCom;
    }

    public void setPositiveCom(int positiveCom) {
        this.positiveCom = positiveCom;
    }

    public int getHasImgCom() {
        return hasImgCom;
    }

    public void setHasImgCom(int hasImgCom) {
        this.hasImgCom = hasImgCom;
    }
}
