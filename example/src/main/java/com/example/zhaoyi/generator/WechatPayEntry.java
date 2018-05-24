package com.example.zhaoyi.generator;

import com.wechat.template.WPayXEntryTemplate;

import annotations.PayEntryGenerator;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.generator
 * 创建者   82354
 * 创建时间   2018/5/24 19:55
 * 描述  TODO
 */
@PayEntryGenerator(
        packageName ="com.example.zhaoyi.festec",
        payEntryTemplete = WPayXEntryTemplate.class
)
public interface WechatPayEntry {
}
