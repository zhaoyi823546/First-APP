package com.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.delegates.LatteDelegate;
import com.example.zhaoyi.latte_core.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.activitys
 * 创建者   82354
 * 创建时间   2018/5/13 11:07
 * 描述  TODO
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegare();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("zhaoyi","ProxyActivity");
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            Log.i("zhaoyi","initContainer");
            loadRootFragment(R.id.delegate_container,setRootDelegare());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
