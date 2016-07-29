package com.lcf.like.core.rxcache;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author LiChaofeng
 * @description
 * @date 2016/7/28 13:59
 * @since 1.0
 */
public abstract class RxCache<T> {
    //Internal Processing
    protected abstract boolean cacheInMemory(T model);

    protected abstract boolean storeToDisk(T model);

    protected abstract boolean arriveFromMemory(T model);

    protected abstract boolean arriveFromDisk(T model);

    protected abstract boolean arriveFromNetwork(T model);

    protected abstract T obtainFromMemory(HashMap<String, Object> param);

    protected abstract T obtainFromDisk(HashMap<String, Object> param);

    protected abstract Observable<T> obtainFromNetwork(HashMap<String, Object> param);

    private Observable<T> memory(final HashMap<String, Object> param) {
        Observable<T> observable = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(obtainFromMemory(param));
                subscriber.onCompleted();
            }
        });
        return observable.doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                arriveFromMemory(t);
            }
        }).compose(logSource("MEMORY"));
    }

    private Observable<T> disk(final HashMap<String, Object> param) {
        Observable<T> observable = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(obtainFromDisk(param));
                subscriber.onCompleted();
            }
        });
        return observable.doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                arriveFromDisk(t);
            }
        }).compose(logSource("DISK"));
    }

    private Observable<T> network(final HashMap<String, Object> param) {
        Observable<T> observable = obtainFromNetwork(param);
        return observable.doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                arriveFromNetwork(t);
            }
        }).compose(logSource("NETWORK"));
    }

    public Observable<T> processing(boolean refresh, HashMap<String, Object> param) {
        return refresh ? network(param) : Observable.concat(
                memory(param),
                disk(param),
                network(param))
                .first(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        return t != null;
                    }
                });
    }

    private Observable.Transformer<T, T> logSource(final String source) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                tObservable.doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (t == null) {
                            System.out.println(source + " does not have any data.");
                        } else {
                            System.out.println(source + " has the data you are looking for!");
                        }
                    }
                });
                return tObservable;
            }
        };
    }
}
