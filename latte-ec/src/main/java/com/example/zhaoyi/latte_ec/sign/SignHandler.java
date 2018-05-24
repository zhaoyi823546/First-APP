package com.example.zhaoyi.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.AccountManager;
import com.example.zhaoyi.latte_ec.database.DatabaseManager;
import com.example.zhaoyi.latte_ec.database.UserProfile;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.latte_ec.sign
 * 创建者   82354
 * 创建时间   2018/5/24 15:07
 * 描述  TODO
 */
public class SignHandler {
    public static void onSignUp(String response,ISignListener mISignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登陆成功
        AccountManager.setSignState(true);
        mISignListener.onSignUpSuccess();
    }
    public static void onSignIn(String response,ISignListener mISignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登陆成功
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();
    }
}
