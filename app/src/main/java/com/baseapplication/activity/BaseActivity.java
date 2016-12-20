package com.baseapplication.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Keval on 17-Dec-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mSubscription = new CompositeSubscription();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void addSubscription(Subscription subscription){
        mSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
