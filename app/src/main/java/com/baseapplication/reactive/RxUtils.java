package com.baseapplication.reactive;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Keval on 20-Dec-16.
 * All the utils related to Rx.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class RxUtils {

    /**
     * Create an observable on worker thread and observe it on main thread.
     *
     * @param observable observable to run on worker thread.
     * @param action1    observer to run in UI thread.
     * @return new {@link Subscription}
     */
    public static Subscription startObservable(Observable.OnSubscribe observable, Action1 action1) {
        //noinspection unchecked
        return Observable.create(observable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
