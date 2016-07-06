package com.lcf.like.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author lcf
 * @version 1.0
 * @description Gank Item
 * @time 2016/6/25 12:25
 */
public class GankItem extends BaseModel<RealmGankItem> {
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("publishedAt")
    @Expose
    public String publishedAt;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("used")
    @Expose
    public boolean used;
    @SerializedName("who")
    @Expose
    public String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "GankItem{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    @Override
    public RealmGankItem transformToRealm() {
        RealmGankItem realmGankItem = new RealmGankItem();
        realmGankItem.set_id(_id);
        realmGankItem.setCreatedAt(createdAt);
        realmGankItem.setDesc(desc);
        realmGankItem.setPublishedAt(publishedAt);
        realmGankItem.setSource(source);
        realmGankItem.setType(type);
        realmGankItem.setUrl(url);
        realmGankItem.setUsed(used);
        realmGankItem.setWho(who);
        return realmGankItem;
    }

    @Override
    public BaseModel transformFromRealm(RealmGankItem realm) {
        GankItem gankItem = new GankItem();
        gankItem.set_id(realm.get_id());
        gankItem.setCreatedAt(realm.getCreatedAt());
        gankItem.setDesc(realm.getDesc());
        gankItem.setPublishedAt(realm.getPublishedAt());
        gankItem.setSource(realm.getSource());
        gankItem.setType(realm.getType());
        gankItem.setUrl(realm.getUrl());
        gankItem.setUsed(realm.isUsed());
        gankItem.setWho(realm.getWho());
        return gankItem;
    }

    @Override
    public Class getRealmClass() {
        return RealmGankItem.class;
    }
}
