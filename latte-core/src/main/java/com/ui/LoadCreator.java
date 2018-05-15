package com.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * 项目名 FestEC2 on 2018/5/14.
 * 包名   com.ui
 * 创建者   82354
 * 创建时间   2018/5/14 23:57
 * 描述  TODO
 */
public class LoadCreator {
    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();
    static AVLoadingIndicatorView create(String type,Context context){
         final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
         if (LOADING_MAP.get(type) == null){
             final Indicator indicator = getIndicator(type);
             LOADING_MAP.put(type,indicator);
         }
         avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
         return avLoadingIndicatorView;
        }
    private static Indicator getIndicator(String name){
        if (name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
