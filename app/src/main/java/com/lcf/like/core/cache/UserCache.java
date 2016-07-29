package com.lcf.like.core.cache;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.model.RealmUser;
import com.lcf.like.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/27 17:34
 * @since 1.0
 */
public class UserCache extends BaseCache<User, RealmUser> {
    @Override
    protected User obtainFromDisk(HashMap<String, Object> param) {
        DiskManager<User, RealmUser> manager = new DiskManager<>();
        List<User> user = manager.loadAllFromDisk(new User());
        if (user == null || user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }

    @Override
    protected User obtainFromNetwork(HashMap<String, Object> param) {
        return null;
    }
}
