package com.lcf.like.model;

/**
 * @author lcf
 * @version 1.0
 * @description Parent Class that every Model must extends
 * @time 2016/6/25 11:59
 */
public abstract class BaseModel<T> {
    public abstract T transformToRealm();
    public abstract BaseModel transformFromRealm(T realm);
    public abstract Class getRealmClass();
}
