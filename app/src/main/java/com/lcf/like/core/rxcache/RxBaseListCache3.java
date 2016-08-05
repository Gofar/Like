package com.lcf.like.core.rxcache;

import android.text.TextUtils;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.managers.MemoryManager;
import com.lcf.like.model.BaseModel;

import java.util.HashMap;
import java.util.List;

import io.realm.RealmObject;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/2 16:57
 * @since 1.0
 */
public abstract class RxBaseListCache3<T extends BaseModel,R extends RealmObject> extends RxCache<List<T>>{
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected boolean cacheInMemory(List<T> model) {
        if (TextUtils.isEmpty(key)||model==null){
            return false;
        }
        MemoryManager.instance.push(key,model);
        return true;
    }

    @Override
    protected boolean storeToDisk(List<T> model) {
        if (TextUtils.isEmpty(key)||model==null){
            return false;
        }
        DiskManager<T,R> manager=new DiskManager<>();
        manager.saveToDisk(model);
        manager.closeRealm();
        return true;
    }

    @Override
    protected boolean arriveFromMemory(List<T> model) {
        return false;
    }

    @Override
    protected boolean arriveFromDisk(List<T> model) {
        cacheInMemory(model);
        return true;
    }

    @Override
    protected boolean arriveFromNetwork(List<T> model) {
        cacheInMemory(model);
        storeToDisk(model);
        return true;
    }

    @Override
    protected List<T> obtainFromMemory(HashMap<String, Object> param) {
        System.out.println("obtainFromMemory is on processing.");
        return (List<T>) MemoryManager.instance.pop(key);
    }
}
