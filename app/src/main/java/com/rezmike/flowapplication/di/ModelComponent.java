package com.rezmike.flowapplication.di;

import com.rezmike.flowapplication.ui.abstracts.AbstractModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = ModelModule.class)
@Singleton
public interface ModelComponent {
    void inject(AbstractModel model);
}
