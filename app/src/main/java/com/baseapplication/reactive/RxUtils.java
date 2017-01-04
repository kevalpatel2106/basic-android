package com.baseapplication.reactive;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Keval on 20-Dec-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class RxUtils {

    public static Subscription startObservable(Observable.OnSubscribe observable, Action1 action1) {
        //noinspection unchecked
        return Observable.create(observable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    public static Subscription startObservable(Observable.OnSubscribe observable, Observer observer) {
        //noinspection unchecked
        return Observable.create(observable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
