package com.rezmike.flowapplication.di;

import com.rezmike.flowapplication.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    @Singleton
    DataManager provideDataManager() {
        return DataManager.getInstance();
    }
}
