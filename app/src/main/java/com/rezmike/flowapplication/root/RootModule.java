package com.rezmike.flowapplication.root;

import dagger.Provides;

@dagger.Module
public class RootModule {
    @Provides
    @RootScope
    RootPresenter provideRootPresenter() {
        return new RootPresenter();
    }
}
