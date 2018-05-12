package com.app;

import java.util.WeakHashMap;

/**
 * 项目名 FestEC2 on 2018/5/12.
 * 包名   com.app
 * 创建者   82354
 * 创建时间   2018/5/12 22:44
 * 描述  TODO
 */
public class Configurator {
    private static final WeakHashMap<String ,Object> LATTE_CONFIGS = new WeakHashMap<>();
    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }
    //线程安全的懒汉模式
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    final WeakHashMap<String,Object> getLatteConfigs (){
        return LATTE_CONFIGS;
    }

    private static class  Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    //线程安全的懒汉模式
    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }
   public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
   }
   private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call config");
        }
   }
   final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
   }
}
