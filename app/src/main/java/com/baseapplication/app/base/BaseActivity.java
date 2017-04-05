package com.baseapplication.app.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Keval on 17-Dec-16.
 * This is the root class for the activity that extends {@link AppCompatActivity}.
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return false;
        }else {
            return super.onOptionsItemSelected(item);
        }
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
