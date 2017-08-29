package com.zzr.rem.listener;

/**
 * Created by Misaki on 2017/8/21.
 */

public interface IProductSortChanegListener {
    public static final int ALLSORT = 0x001;
    public static final int NEWSSORT = 0x002;
    public static final int COMMENTSORT = 0x003;

    public void onSortChanged(int action);
}
