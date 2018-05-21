package com.net;

import android.content.Context;

import com.app.ConfigType;
import com.net.callback.IError;
import com.net.callback.IFailure;
import com.net.callback.IRequest;
import com.net.callback.ISuccess;
import com.net.callback.RequestCallbacks;
import com.net.download.DownloadHandler;
import com.ui.LatteLoader;
import com.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.PUT;

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
    private final IRequest  REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILRE;
    private final IError   ERROR;
    private final RequestBody  BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestClient(String URL,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILRE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
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
        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.get(URL,PARAMS);
                break;
            case POST_RAM:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.get(URL,PARAMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.get(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
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
                ERROR,
                LOADER_STYLE
        );
   }
   public final void get(){
        request(HttpMethod.GET);
   }
    public final void post(){
        if (BODY == null){
            request(HttpMethod.POST);
        }else{
            if (!PARAMS.isEmpty()){
                throw  new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }

    }
    public final void put(){
        if (BODY == null){
            request(HttpMethod.PUT);
        }else{
            if (!PARAMS.isEmpty()){
                throw  new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAM);
        }
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }
    public final void download(){
        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,
                NAME,SUCCESS,FAILRE,ERROR).handleDownload();
    }



}
