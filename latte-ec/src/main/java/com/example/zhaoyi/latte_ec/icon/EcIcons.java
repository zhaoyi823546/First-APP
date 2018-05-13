package com.example.zhaoyi.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.example.zhaoyi.latte_ec.icon
 * 创建者   82354
 * 创建时间   2018/5/13 10:24
 * 描述  TODO
 */
public enum EcIcons implements Icon{
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char charcter;

    EcIcons(char charcter) {
        this.charcter = charcter;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return charcter;
    }
}
