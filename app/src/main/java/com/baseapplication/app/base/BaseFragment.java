package com.baseapplication.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Keval on 17-Dec-16.
 * Base fragment is base class for {@link Fragment}. This handles connection and disconnect of Rx bus.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class BaseFragment extends Fragment {

    /**
     * {@link CompositeSubscription} that holds all the subscriptions.
     */
    private CompositeSubscription mSubscription = new CompositeSubscription();

    /**
     * Add new subscription for the rx bus.
     *
     * @param subscription {@link Subscription} between observer and observables.
     */
    protected void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Bind butter knife
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Unsubscribe all the subscription.
        mSubscription.unsubscribe();
    }
}
