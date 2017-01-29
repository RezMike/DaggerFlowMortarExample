package com.rezmike.flowapplication.data;

import com.rezmike.flowapplication.App;
import com.rezmike.flowapplication.di.DaggerDataManagerComponent;
import com.rezmike.flowapplication.di.DataManagerComponent;
import com.rezmike.flowapplication.di.LocalModule;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

public class DataManager {

    private static DataManager ourInstance = new DataManager();

    @Inject
    PreferencesManager mPreferencesManager;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        DataManagerComponent component = DaggerService.getComponent(DataManagerComponent.class);
        if (component == null) {
            component = DaggerDataManagerComponent.builder()
                    .appComponent(App.getAppComponent())
                    .localModule(new LocalModule())
                    .build();
            DaggerService.registerComponent(DataManagerComponent.class, component);
        }
        component.inject(this);
    }
}
