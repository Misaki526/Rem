package com.zzr.rem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zzr.rem.cons.DbConst;

/**
 * Created by Misaki on 2017/8/2.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context) {
        super(context, DbConst.DB_NAME, null, DbConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbConst.USER_TABLE + "(" +
                DbConst._ID + " integer primary key autoincrement, " +
                DbConst._NAME + " text, " +
                DbConst._PWD + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
