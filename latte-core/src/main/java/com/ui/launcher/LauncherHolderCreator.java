package com.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * 项目名 FestEC2 on 2018/5/22.
 * 包名   com.ui.launcher
 * 创建者   82354
 * 创建时间   2018/5/22 18:12
 * 描述  TODO
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
