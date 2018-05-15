package com.net;

import com.net.callback.IError;
import com.net.callback.IFailure;
import com.net.callback.IRequest;
import com.net.callback.ISuccess;
import com.net.callback.RequestCallbacks;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 项目名 FestEC2 on 2018/5/14.
 * 包名   com.net
 * 创建者   82354
 * 创建时间   2018/5/14 19:03
 * 描述  TODO
 */
public class RestClient {
    private final String URL ;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILRE;
    private final IError   ERROR;
    private final RequestBody  BODY;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILRE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
    private void request(HttpMethod method){
        final Restservice service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null){
            REQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.get(URL,PARAMS);
                break;
            case PUT:
                call = service.get(URL,PARAMS);
                break;
            case DELETE:
                call = service.get(URL,PARAMS);
                break;
            default:
                break;

        }
        if (call != null){
            call.enqueue(getRequestCallback());
        }

    }
   private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILRE,
                ERROR
        );
   }
   public final void get(){
        request(HttpMethod.GET);
   }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
