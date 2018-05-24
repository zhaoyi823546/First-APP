package com.example.zhaoyi.latte_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.latte_ec.database
 * 创建者   82354
 * 创建时间   2018/5/24 14:46
 * 描述  TODO
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper{
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
