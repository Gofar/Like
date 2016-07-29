package com.lcf.like.core.cache;

import java.util.HashMap;

/**
 * @author LiChaofeng
 * @description Base list cache,only use network,no memory and disk cache
 * @date 2016/7/27 16:52
 * @since 1.0
 */
public abstract class BaseListCache<T> extends Cache<T> {
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected boolean cacheInMemory(T model) {
//        System.out.println("cacheInMemory is on processing.");
//        if (TextUtils.isEmpty(key)||model==null){
//            return false;
//        }
//        MemoryManager.instance.push(key,model);
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
        //cacheInMemory(model);
        return false;
    }

    @Override
    protected T obtainFromMemory(HashMap<String, Object> param) {
        //System.out.println("obtainFromMemory is on processing.");
        //return (T) MemoryManager.instance.pop(key);
        return null;
    }

    @Override
    protected T obtainFromDisk(HashMap<String, Object> param) {
        //System.out.println("obtainFromDisk is on processing.");
        return null;
    }
}
