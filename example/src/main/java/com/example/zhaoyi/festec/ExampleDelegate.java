package com.example.zhaoyi.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.delegates.LatteDelegate;
import com.net.RestClient;
import com.net.callback.IError;
import com.net.callback.IFailure;
import com.net.callback.ISuccess;

/**
 * 项目名 FestEC2 on 2018/5/13.
 * 包名   com.example.zhaoyi.festec
 * 创建者   82354
 * 创建时间   2018/5/13 17:22
 * 描述  TODO
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
          testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("https://news.baidu.com/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
