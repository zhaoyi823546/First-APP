package com.net;

import com.app.ConfigType;
import com.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 项目名 FestEC2 on 2018/5/14.
 * 包名   com.net
 * 创建者   82354
 * 创建时间   2018/5/14 19:28
 * 描述  TODO
 */
public class RestCreator {

    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String ,Object> getParams(){

        return ParamsHolder.PARAMS;
    }
    public static Restservice getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }
    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RestServiceHolder{
        private static final Restservice REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(Restservice.class);
    }


}
