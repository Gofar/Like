package com.lcf.like.viewmodel;

import android.databinding.BaseObservable;

import rx.Subscription;

/**
 * @Description:  Custom BaseObservable
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 16:54
 */
public abstract class MyBaseObservable extends BaseObservable {
    protected Subscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
