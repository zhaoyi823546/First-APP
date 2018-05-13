package com.example.zhaoyi.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.delegates.LatteDelegate;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.example.zhaoyi.festec
 * 创建者   82354
 * 创建时间   2018/5/13 17:22
 * 描述  TODO
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

    }
}
