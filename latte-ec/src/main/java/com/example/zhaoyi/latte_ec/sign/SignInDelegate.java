package com.example.zhaoyi.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.delegates.LatteDelegate;
import com.example.zhaoyi.latte_ec.R;
import com.example.zhaoyi.latte_ec.R2;
import com.net.RestClient;
import com.net.callback.ISuccess;
import com.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   com.example.zhaoyi.latte_ec.sign
 * 创建者   82354
 * 创建时间   2018/5/24 9:22
 * 描述  TODO
 */
public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE",response);
                            SignHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
            // Toast.makeText(getContext(),"点击你了",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R2.id.icon_sign_we_chat)
    void onClickWeChat(){

    }
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }
    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }
        if (password.isEmpty()||password.length()<6){
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else {
            mPassword.setError(null);
        }
        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

    }
}
