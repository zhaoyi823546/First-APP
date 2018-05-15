package com.net.callback;

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

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILRE = failure;
        this.ERROR = error;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
          if (FAILRE != null){
              FAILRE.onFailure();
          }
          if (REQUEST != null){
              REQUEST.onRequestEnd();
          }
    }
}
