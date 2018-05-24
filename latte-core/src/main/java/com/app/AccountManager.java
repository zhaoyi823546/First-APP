package com.app;

import com.util.storage.LattePreference;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.app
 * 创建者   82354
 * 创建时间   2018/5/24 15:23
 * 描述  TODO
 */
public class AccountManager {
    private enum  SignTag{
        SIGN_TAG
    }
    //保存用户登录状态，登陆后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
