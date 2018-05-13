package com.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.delegates
 * 创建者   82354
 * 创建时间   2018/5/13 11:10
 * 描述  TODO
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    private Unbinder mUnbinder = null;
    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootview);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }
        if (rootView != null){
         mUnbinder = ButterKnife.bind(this,rootView);
         //   mUnbinder = ButterKnife.bind(this,rootView);
          onBindView(savedInstanceState,rootView);
        }
        return  rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
