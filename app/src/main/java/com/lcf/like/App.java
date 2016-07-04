package com.lcf.like;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

/**
 * @author LiChaofeng
 * @description Custom Application
 * @date 2016/6/29 18:51
 * @since 1.0
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Init Fresco
        Fresco.initialize(this);
        // Init Stetho
        Stetho.initializeWithDefaults(this);
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(
//                                Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(
//                                Stetho.defaultInspectorModulesProvider(this))
//                        .build());
//
//        new OkHttpClient.Builder()
//                .addNetworkInterceptor(new StethoInterceptor())
//                .build();

    }

}
