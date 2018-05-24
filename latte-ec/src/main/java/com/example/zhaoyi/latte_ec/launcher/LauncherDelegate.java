package com.example.zhaoyi.latte_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.app.AccountManager;
import com.app.IUserChecker;
import com.delegates.LatteDelegate;


import com.example.zhaoyi.latte_ec.R;
import com.example.zhaoyi.latte_ec.R2;
import com.net.callback.IFailure;
import com.ui.launcher.ILauncherListener;
import com.ui.launcher.OnLauncherFinishTag;
import com.ui.launcher.ScrollLauncherTag;
import com.util.storage.LattePreference;
import com.util.timer.BaseTimerTask;
import com.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名 FestEC2 on 2018/5/22.
 * 包名   com.example.zhaoyi.latte_ec.launcher
 * 创建者   82354
 * 创建时间   2018/5/22 16:34
 * 描述  TODO
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer =null;
    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer !=null){
            mTimer.cancel();
            mTimer= null;
            checkIsShowScroll();
        }
    }
    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        initTimer();
    }

    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDeleagte(),SINGLETASK);
        }else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                   if (mILauncherListener != null){
                       mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                   }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener !=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount <0){
                       if (mTimer !=null){
                           mTimer.cancel();
                           mTimer= null;
                           checkIsShowScroll();
                       }
                    }
                }
            }
        });
    }
}
