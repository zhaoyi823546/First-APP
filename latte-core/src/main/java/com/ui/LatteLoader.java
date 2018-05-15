package com.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.zhaoyi.latte_core.R;
import com.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * 项目名 FestEC2 on 2018/5/15.
 * 包名   com.ui
 * 创建者   82354
 * 创建时间   2018/5/15 21:50
 * 描述  TODO
 */
public class LatteLoader {
    //设置一个缩放比
    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认的Loading 样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator.name();
    public static void showLoading(Context context,Enum<LoaderStyle> type){
         showLoading(context,type.name());
    }

    public static void showLoading(Context context,String type){
        final AppCompatDialog  dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoadCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
           if (dialogWindow !=null){
               WindowManager.LayoutParams lp = dialogWindow.getAttributes();
               lp.width = deviceWidth/LOADER_SIZE_SCALE;
               lp.height = deviceHeight/LOADER_SIZE_SCALE;
               lp.height = lp.height+deviceHeight/LOADER_OFFSET_SCALE;
               lp.gravity = Gravity.CENTER;
           }
           LOADERS.add(dialog);
           dialog.show();
    }
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERS){
             if (dialog != null){
                 if (dialog.isShowing()){
                     dialog.cancel();
                 }
             }
        }
    }
}
