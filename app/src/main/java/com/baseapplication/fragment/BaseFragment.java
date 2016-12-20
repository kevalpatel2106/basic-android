package com.baseapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Keval on 17-Dec-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class BaseFragment extends Fragment {
    private CompositeSubscription mSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription){
        mSubscription.add(subscription);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
