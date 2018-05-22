package com.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;


/**
 * 项目名 FestEC2 on 2018/5/22.
 * 包名   com.ui.launcher
 * 创建者   82354
 * 创建时间   2018/5/22 18:14
 * 描述  TODO
 */
public class LauncherHolder implements Holder<Integer>{
   private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
       mImageView.setBackgroundResource(data);
    }
}
