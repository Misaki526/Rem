package com.zzr.rem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zzr.rem.cons.DbConst;

/**
 * Created by Misaki on 2017/8/2.
 */

public class UserDao {

    private DbOpenHelper mHelper;

    public UserDao(Context c) {
        mHelper = new DbOpenHelper(c);
    }

    /**
     * 保存操作
     */
    public boolean saveUser(String name, String pwd) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbConst._NAME, name);
        values.put(DbConst._PWD, pwd);
        long insertId = db.insert(DbConst.USER_TABLE, null, values);

        return insertId != -1;
    }

    /**
     * 清空数据表
     */
    public void clearUsers() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DbConst.USER_TABLE, null, null);
    }

    /**
     * 获取最近登录的用户
     */
    public UserInfo aquireLastestUser() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(DbConst.USER_TABLE, new String[]{DbConst._NAME, DbConst._PWD}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            String username = cursor.getString(0);
            String pwd = cursor.getString(1);
            return new UserInfo(username, pwd);
        }
        return null;
    }

    public class UserInfo {
        public String username;
        public String pwd;

        public UserInfo(String username, String pwd) {
            this.username = username;
            this.pwd = pwd;
        }

    }
}
