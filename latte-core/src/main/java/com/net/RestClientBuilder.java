package com.net;

import com.net.callback.IError;
import com.net.callback.IFailure;
import com.net.callback.IRequest;
import com.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目名 FestEC2 on 2018/5/14.
 * 包名   com.net
 * 创建者   82354
 * 创建时间   2018/5/14 20:47
 * 描述  TODO
 */
public class RestClientBuilder {

    private  String mUrl ;
    private  static final Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  RequestBody mBody;

     RestClientBuilder() {
    }
    public final RestClientBuilder url(String url){
         this.mUrl = url;
         return this;
    }
    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
         return this;
    }
    public final RestClientBuilder params(String key,Object value){

         PARAMS.put(key,value);
         return this;
    }
    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }
    public final RestClientBuilder raw(String raw){
         this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
         return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
         this.mISuccess = iSuccess;
         return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }
    public final RestClient build(){
         return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody);
    }















}
