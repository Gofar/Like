package com.lcf.like.core.rxcache;

import java.util.HashMap;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/28 16:10
 * @since 1.0
 */
public abstract class RxBaseListCache<T> extends RxCache<T>{
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected boolean cacheInMemory(T model) {
        return false;
    }

    @Override
    protected boolean storeToDisk(T model) {
        return false;
    }

    @Override
    protected boolean arriveFromMemory(T model) {
        return false;
    }

    @Override
    protected boolean arriveFromDisk(T model) {
        return false;
    }

    @Override
    protected boolean arriveFromNetwork(T model) {
        return false;
    }

    @Override
    protected T obtainFromMemory(HashMap<String, Object> param) {
        return null;
    }

    @Override
    protected T obtainFromDisk(HashMap<String, Object> param) {
        return null;
    }
}
