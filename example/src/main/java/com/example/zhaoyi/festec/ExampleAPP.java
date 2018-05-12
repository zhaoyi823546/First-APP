package com.example.zhaoyi.festec;

import android.app.Application;

import com.app.Latte;

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
                .withApiHost("")
                .configure();
    }
}
