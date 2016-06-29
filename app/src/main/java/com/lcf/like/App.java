package com.lcf.like;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

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
    }
}
