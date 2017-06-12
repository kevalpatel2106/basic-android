package com.baseapplication.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baseapplication.utils.Utils;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Keval on 17-Dec-16.
 * This is the root class for the activity that extends {@link AppCompatActivity}. Use this class instead
 * of {@link AppCompatActivity} through out the application.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        //Bind butter knife
        ButterKnife.bind(this);
    }

    /**
     * Set the toolbar of the activity.
     *
     * @param toolbarId    resource id of the toolbar
     * @param title        title of the activity
     * @param showUpButton true if toolbar should display up indicator.
     */
    protected void setToolbar(int toolbarId, @StringRes int title, boolean showUpButton) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        setToolbar(title, showUpButton);
    }

    /**
     * Set the toolbar of the activity.
     *
     * @param toolbarId    resource id of the toolbar
     * @param title        title of the activity
     * @param showUpButton true if toolbar should display up indicator.
     */
    protected void setToolbar(int toolbarId, @NonNull String title, boolean showUpButton) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        setToolbar(title, showUpButton);
    }

    /**
     * Set the toolbar.
     *
     * @param title        Activity title string resource
     * @param showUpButton true if toolbar should display up indicator.
     */
    protected void setToolbar(@StringRes int title, boolean showUpButton) {
        setToolbar(getString(title), showUpButton);
    }

    /**
     * Set the toolbar.
     *
     * @param title        Activity title string.
     * @param showUpButton true if toolbar should display up indicator.
     */
    @SuppressWarnings("ConstantConditions")
    protected void setToolbar(@NonNull String title, boolean showUpButton) {
        //set the title
        getSupportActionBar().setTitle(title);

        //Set the up indicator
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(showUpButton);
        getSupportActionBar().setHomeButtonEnabled(showUpButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showUpButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Hide the keyboard if any view is currently in focus.
            if (getCurrentFocus() != null) Utils.hideKeyboard(getCurrentFocus());
            finish();
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Add the subscription to the {@link CompositeDisposable}.
     *
     * @param disposable {@link Disposable}
     */
    protected void addSubscription(@Nullable Disposable disposable) {
        if (disposable == null) return;
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
}
