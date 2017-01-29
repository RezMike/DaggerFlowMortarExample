package com.rezmike.flowapplication.di;

import com.rezmike.flowapplication.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = LocalModule.class)
@Singleton
public interface DataManagerComponent {
    void inject(DataManager dataManager);
}
