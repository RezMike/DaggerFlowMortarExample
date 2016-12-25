package com.rezmike.flowapplication.root;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.rezmike.flowapplication.BuildConfig;
import com.rezmike.flowapplication.R;
import com.rezmike.flowapplication.app.AppComponent;
import com.rezmike.flowapplication.base.IView;
import com.rezmike.flowapplication.red.RedScreen;
import com.rezmike.flowapplication.utils.TreeKeyDispatcher;

import flow.Flow;

public class RootActivity extends AppCompatActivity implements IRootView {

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .dispatcher(new TreeKeyDispatcher(this))
                .defaultKey(new RedScreen())
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //region ======================== IRootView ========================

    @Override
    public void showMessage(String message) {
        //Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int stringResId) {
        showMessage(getString(stringResId));
    }

    @Override
    public void showError(Throwable e) {
        if (BuildConfig.DEBUG) {
            showMessage(e.getMessage());
            e.printStackTrace();
        } else {
            //showMessage(getString(R.string.sorry_something_wrong));
            // TODO: 23.10.2016 send error stacktrace to crashlytics
        }
    }

    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Nullable
    @Override
    public IView getCurrentScreen() {
        return null;
    }

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    //endregion

    //region ======================== DI ========================

    @RootScope
    @dagger.Component(dependencies = AppComponent.class, modules = RootModule.class)
    public interface RootComponent {
        void inject(RootActivity activity);

        RootPresenter getRootPresenter();
    }

    //endregion
}
