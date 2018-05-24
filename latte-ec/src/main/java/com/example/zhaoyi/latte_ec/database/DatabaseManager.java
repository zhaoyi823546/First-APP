package com.example.zhaoyi.latte_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.latte_ec.database
 * 创建者   82354
 * 创建时间   2018/5/24 14:47
 * 描述  TODO
 */
public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;
    private  DatabaseManager(){

    }
    public   DatabaseManager init(Context context){
        initDao(context);
        return this;
    }
    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }
    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }
    public final UserProfileDao getDao(){
        return mDao;
    }

}
