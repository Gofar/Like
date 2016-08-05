package com.lcf.like.core.cache;

import com.lcf.like.managers.DiskManager;
import com.lcf.like.managers.NetworkManager;
import com.lcf.like.model.RealmUser;
import com.lcf.like.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/27 17:34
 * @since 1.0
 */
public class UserCache extends BaseCache<User, RealmUser> {
    @Override
    protected User obtainFromDisk(HashMap<String, Object> param) {
        System.out.println("obtainFromDisk is on processing.");
        String login = (String) param.get("login");
        DiskManager<User, RealmUser> manager = new DiskManager<>();
        List<User> user = manager.loadFromDiskWithCondition(new User(), "login", login);
        if (user == null || user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }

    @Override
    protected User obtainFromNetwork(HashMap<String, Object> param) {
        System.out.println("obtainFromNetwork is on processing.");
        String login = (String) param.get("login");
        Call<User> call = NetworkManager.getGitHubApi().getUserDetails2(login);
        User user = null;
        try {
            Response<User> response = call.execute();
            user = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
