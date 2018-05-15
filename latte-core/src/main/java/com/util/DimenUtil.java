package com.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.app.Latte;

/**
 * 项目名 FestEC2 on 2018/5/15.
 * 包名   com.util
 * 创建者   82354
 * 创建时间   2018/5/15 22:11
 * 描述  TODO
 */
public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
