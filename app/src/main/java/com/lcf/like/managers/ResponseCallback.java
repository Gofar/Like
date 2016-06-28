package com.lcf.like.managers;

/**
 * @Description: Network Callback
 * @author: lichaofeng
 * @since: 1.0
 * @Date: 2016/6/27 17:52
 */
public interface ResponseCallback<T> {
    /**
     * success
     * @param t data
     */
    void onResponse(T t);

    /**
     * error
     * @param e Throwable
     */
    void onErrorResponse(Throwable e);
}
