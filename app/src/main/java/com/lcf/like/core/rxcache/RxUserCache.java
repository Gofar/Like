package com.lcf.like.core.rxcache;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.RealmUser;
import com.lcf.like.model.User;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/8/5 16:21
 * @since 1.0
 */
public class RxUserCache extends RxBaseCache<User,RealmUser>{
    @Override
    protected User obtainFromMemory(HashMap<String, Object> param) {
        System.out.println("obtainFromDisk is on processing.");
        String login= (String) param.get("login");
        DiskManager<User,RealmUser> manager=new DiskManager<>();
        List<User> users=manager.loadFromDiskWithCondition(new User(),"login",login);
        if (users==null||users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    protected Observable<User> obtainFromNetwork(HashMap<String, Object> param) {
        System.out.println("obtainFromNetwork is on processing.");
        String login= (String) param.get("login");
        return NetworkManager.getGitHubApi().getUserDetails(login);
    }
}
