package com.example.zhaoyi.festec;

import android.app.Application;

import com.app.Latte;
import com.example.zhaoyi.latte_ec.database.DatabaseManager;
import com.example.zhaoyi.latte_ec.icon.FontEcmodel;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.net.Interceptors.DebugIntercptor;

/**
 * 项目名 FestEC2 on 2018/5/12.
 * 包名   com.example.zhaoyi.festec
 * 创建者   82354
 * 创建时间   2018/5/12 22:41
 * 描述  TODO
 */
public class ExampleAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcmodel())
                .withApiHost("http://192.168.43.134/")
                .withInterceptor(new DebugIntercptor("index",R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
