package com.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.logging.Handler;

/**
 * 项目名 FestEC2 on 2018/5/12.
 * 包名   com.app
 * 创建者   82354
 * 创建时间   2018/5/12 22:40
 * 描述  TODO
 */
public final class Latte {
      public static Configurator init(Context context){
         getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
         return Configurator.getInstance();
      }
      public static HashMap<Object,Object> getConfigurations(){
          return Configurator.getInstance().getLatteConfigs();
      }
      public static Context getApplication(){
          return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
      }
      public static Configurator getConfigurator(){
          return Configurator.getInstance();
      }
      public static <T> T getConfiguration(Object key){
          return  getConfigurator().getConfiguration(key);
      }




}
