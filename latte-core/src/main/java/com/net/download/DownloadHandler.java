package com.net.download;

import android.os.AsyncTask;

import com.net.RestCreator;
import com.net.callback.IError;
import com.net.callback.IFailure;
import com.net.callback.IRequest;
import com.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名 FestEC2 on 2018/5/20.
 * 包名   com.net.download
 * 创建者   82354
 * 创建时间   2018/5/20 22:49
 * 描述  TODO
 */
public class DownloadHandler {
    private final String URL ;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILRE;
    private final IError ERROR;

    public DownloadHandler(String URL,
                           IRequest REQUEST,
                           String DOWNLOAD_DIR,
                           String EXTENSION,
                           String NAME,
                           ISuccess SUCCESS,
                           IFailure FAILRE,
                           IError ERROR) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.FAILRE = FAILRE;
        this.ERROR = ERROR;
    }
    public final void handleDownload(){
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    final SavaFileTask task = new SavaFileTask(REQUEST,SUCCESS);
                    final ResponseBody responseBody = response.body();
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,response,NAME);
                    //这里一定要注意判断，否则文件下载不全
                    if (task.isCancelled()){
                        if (REQUEST != null){
                            REQUEST.onRequestEnd();
                        }
                    }
                }else {
                    if (ERROR != null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                 if (FAILRE != null){
                     FAILRE.onFailure();
                 }
            }
        });
    }
}
