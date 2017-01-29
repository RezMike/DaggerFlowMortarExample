package com.rezmike.flowapplication.ui.abstracts;

import com.rezmike.flowapplication.data.DataManager;
import com.rezmike.flowapplication.di.DaggerModelComponent;
import com.rezmike.flowapplication.di.ModelComponent;
import com.rezmike.flowapplication.di.ModelModule;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

public class AbstractModel {

    @Inject
    protected DataManager mDataManager;

    public AbstractModel() {
        ModelComponent component = DaggerService.getComponent(ModelComponent.class);
        if (component == null) {
            component = DaggerModelComponent.builder()
                    .modelModule(new ModelModule())
                    .build();
            DaggerService.registerComponent(ModelComponent.class, component);
        }
        component.inject(this);
    }
}
