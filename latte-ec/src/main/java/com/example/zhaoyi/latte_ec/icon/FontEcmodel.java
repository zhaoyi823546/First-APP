package com.example.zhaoyi.latte_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.example.zhaoyi.latte_ec.icon
 * 创建者   82354
 * 创建时间   2018/5/13 10:17
 * 描述  TODO
 */
public class FontEcmodel implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
