package com.lcf.like.core.rxcache;

import android.text.TextUtils;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.managers.MemoryManager;
import com.lcf.like.model.BaseModel;

import java.util.HashMap;

import io.realm.RealmObject;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/2 16:12
 * @since 1.0
 */
public abstract class RxBaseListCache2<T extends BaseModel,R extends RealmObject> extends RxCache<T> {
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected boolean cacheInMemory(T model) {
        if (TextUtils.isEmpty(key)||model==null){
            return false;
        }
        MemoryManager.instance.push(key,model);
        return true;
    }

    @Override
    protected boolean storeToDisk(T model) {
        if (TextUtils.isEmpty(key)||model==null){
            return false;
        }
        DiskManager<T,R> manager=new DiskManager<>();
        manager.saveToDisk(model);
        manager.closeRealm();
        return true;
    }

    @Override
    protected boolean arriveFromMemory(T model) {
        return false;
    }

    @Override
    protected boolean arriveFromDisk(T model) {
        cacheInMemory(model);
        return true;
    }

    @Override
    protected boolean arriveFromNetwork(T model) {
        cacheInMemory(model);
        storeToDisk(model);
        return true;
    }

    @Override
    protected T obtainFromMemory(HashMap<String, Object> param) {
        System.out.println("obtainFromMemory is on processing.");
        return (T) MemoryManager.instance.pop(key);
    }

    @Override
    protected T obtainFromDisk(HashMap<String, Object> param) {
        System.out.println("obtainFromDisk is on processing.");
        DiskManager<T,R> manager=new DiskManager<>();
        //List<T> tList=manager.loadAllFromDisk(new T())
        return null;
    }
}
