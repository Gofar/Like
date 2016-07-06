package com.lcf.like;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

/**
 * @author LiChaofeng
 * @description Custom Application
 * @date 2016/6/29 18:51
 * @since 1.0
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // Init Fresco
        Fresco.initialize(this);
        // Init Stetho
        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return context;
    }
}
