package com.example.zhaoyi.generator;

import com.wechat.template.WXEntryTemplate;

import annotations.EntryGenerator;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.generator
 * 创建者   82354
 * 创建时间   2018/5/24 19:54
 * 描述  TODO
 */
@EntryGenerator(
        packageName ="com.example.zhaoyi.festec",
        entryTemplete = WXEntryTemplate.class
)
public interface WechatEntry {
}
