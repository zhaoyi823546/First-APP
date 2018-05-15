package com.net.callback;

import android.os.Handler;

import com.ui.LatteLoader;
import com.ui.LoaderStyle;


import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名 FestEC2 on 2018/5/14.
 * 包名   com.net.callback
 * 创建者   82354
 * 创建时间   2018/5/14 22:43
 * 描述  TODO
 */
public class RequestCallbacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILRE;
    private final IError   ERROR;
    private final LoaderStyle LOADER_STYLE;
    //handler设置成static 避免内存泄露
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILRE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }
       stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
          if (FAILRE != null){
              FAILRE.onFailure();
          }
          if (REQUEST != null){
              REQUEST.onRequestEnd();
          }
          stopLoading();
    }
    private void stopLoading(){
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);

        }
    }
}
