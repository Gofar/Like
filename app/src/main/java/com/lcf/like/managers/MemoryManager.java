package com.lcf.like.managers;

import android.util.LruCache;

/**
 * @author LiChaofeng
 * @description Memory Manager
 * @date 2016/7/6 17:51
 * @since 1.0
 */
public enum MemoryManager {
    /**
     * 1.从Java1.5开始支持;
     * 2.无偿提供序列化机制;
     * 3.绝对防止多次实例化，即使在面对复杂的序列化或者反射攻击的时候;
     */
    instance;
    private LruCache<String, Object> dataCacher = new LruCache<>((int) Runtime.getRuntime().maxMemory() / 8);

    MemoryManager() {

    }

    public Object push(String key, Object data) {
        return dataCacher.put(key, data);
    }

    public Object pop(String key) {
        return dataCacher.get(key);
    }

    public Object remove(String key) {
        return dataCacher.remove(key);
    }
}
