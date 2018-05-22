package com.app;

import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 项目名 FestEC2 on 2018/5/12.
 * 包名   com.app
 * 创建者   82354
 * 创建时间   2018/5/12 22:44
 * 描述  TODO
 */
public class Configurator {
    private static final HashMap<Object ,Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS =new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private Configurator(){
        Log.i("zhaoyi","Configurator");
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }
    //线程安全的懒汉模式
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    final HashMap<Object,Object> getLatteConfigs (){
        return LATTE_CONFIGS;
    }

    private static class  Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    //线程安全的懒汉模式
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }
   public final Configurator withApiHost(String host){
       Log.i("zhaoyi","withApiHost = "+host);
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
   }
   private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call config");
        }
   }
   final <T> T getConfiguration(Object key){
        for (int i=0;i<LATTE_CONFIGS.size();i++){
            Log.i("fanxina","key = "+ LATTE_CONFIGS.keySet());
        }
        checkConfiguration();
        Log.i("fanxina","LATTE_CONFIGS==null   "+(LATTE_CONFIGS==null)+ "key= "+ key);
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null){
            throw  new NullPointerException(key.toString()+"IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
   }
   private void initIcons(){
        if (ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
   }
   public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
   }
   public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
   }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withLoaderDelayed(long delayed){
         LATTE_CONFIGS.put(ConfigType.LOADER_DELAYED,delayed);
         return this;
    }

}
