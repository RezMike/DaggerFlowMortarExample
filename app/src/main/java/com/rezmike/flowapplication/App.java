package com.rezmike.flowapplication;

import android.app.Application;

import com.rezmike.flowapplication.di.AppComponent;
import com.rezmike.flowapplication.di.AppModule;
import com.rezmike.flowapplication.di.DaggerAppComponent;
import com.rezmike.flowapplication.ui.root.DaggerRootActivity_RootComponent;
import com.rezmike.flowapplication.ui.root.RootActivity;
import com.rezmike.flowapplication.ui.root.RootModule;
import com.rezmike.flowapplication.utils.DaggerService;
import com.rezmike.flowapplication.utils.ScreenScoper;

import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class App extends Application {

    private static AppComponent sAppComponent;
    private MortarScope mRootScope;
    private MortarScope mRootActivityScope;
    private RootActivity.RootComponent mRootActivityRootComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createAppComponent();
        createRootActivityRootComponent();

        mRootScope = MortarScope.buildRootScope()
                .withService(DaggerService.SERVICE_NAME, sAppComponent)
                .build("Root");

        mRootActivityScope = mRootScope.buildChild()
                .withService(DaggerService.SERVICE_NAME, mRootActivityRootComponent)
                .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                .build(RootActivity.class.getName());

        ScreenScoper.registerScope(mRootScope);
        ScreenScoper.registerScope(mRootActivityScope);
    }

    @Override
    public Object getSystemService(String name) {
        return mRootScope.hasService(name) ? mRootScope.getService(name) : super.getSystemService(name);
    }

    private void createAppComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    private void createRootActivityRootComponent() {
        mRootActivityRootComponent = DaggerRootActivity_RootComponent.builder()
                .appComponent(sAppComponent)
                .rootModule(new RootModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
