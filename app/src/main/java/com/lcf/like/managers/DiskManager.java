package com.lcf.like.managers;

import com.lcf.like.App;
import com.lcf.like.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * @author LiChaofeng
 * @description Disk Manager
 * @date 2016/7/6 18:15
 * @since 1.0
 */
public class DiskManager<K extends BaseModel, V extends RealmObject> {
    private static Realm realm;

    public static Realm getInstance() {
        if (realm == null) {
            RealmConfiguration config = new RealmConfiguration.Builder(App.getContext()).name("like.realm").build();
            try {
                realm = Realm.getInstance(config);
            } catch (RealmMigrationNeededException e) {
                Realm.deleteRealm(config);
                realm = Realm.getInstance(config);
            }
        }
        return realm;
    }

    public void saveToDisk(K model) {
        getInstance().beginTransaction();
        getInstance().copyToRealmOrUpdate((V) model.transformToRealm());
        getInstance().commitTransaction();
    }

    public void saveToDisk(List<K> models) {
        getInstance().beginTransaction();
        for (K model : models) {
            getInstance().copyToRealmOrUpdate((V) model.transformToRealm());
        }
        getInstance().commitTransaction();
    }

    public List<K> loadAllFromDisk(K model) {
        if (model == null) {
            return null;
        }
        RealmResults<V> realmModels = getInstance().where(model.getRealmClass()).findAll();
        if (realmModels == null || realmModels.isEmpty()) {
            return null;
        }
        List<K> models = new ArrayList<>(realmModels.size());
        for (V realmModel : realmModels) {
            models.add((K) model.transformFromRealm(realmModel));
        }
        return models;
    }

    public List<K> loadFromDiskWithCondition(K model, String field, String value) {
        if (model == null) {
            return null;
        }
        RealmResults<V> realmModels = getInstance().where(model.getRealmClass()).contains(field, value).findAll();
        if (realmModels == null || realmModels.isEmpty()) {
            return null;
        }
        List<K> models = new ArrayList<>(realmModels.size());
        for (V realmModel : realmModels) {
            models.add((K) model.transformFromRealm(realmModel));
        }
        return models;
    }

    public void closeRealm() {
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }
}
