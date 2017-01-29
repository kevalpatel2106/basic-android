package com.baseapplication.ui.activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

        //Bind butter knife
        ButterKnife.bind(this);
    }

    /**
     * Set the toolbar of the activity.
     *
     * @param toolbarId resource id of the toolbar
     * @param title     title of the activity
     */
    private void setToolbar(int toolbarId, @StringRes int title) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);

        setToolbar(title);
    }


    /**
     * Set the actionbar of the activity.
     *
     * @param title title of the activity
     */
    protected void setToolbar(@StringRes int title) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Add the subscription to the {@link CompositeSubscription}.
     *
     * @param subscription {@link Subscription}
     */
    protected void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
