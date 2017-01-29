package com.rezmike.flowapplication.ui.green;

import android.os.Bundle;

import com.rezmike.flowapplication.R;
import com.rezmike.flowapplication.ui.abstracts.AbstractScreen;
import com.rezmike.flowapplication.ui.abstracts.Screen;
import com.rezmike.flowapplication.ui.root.RootActivity;
import com.rezmike.flowapplication.ui.root.RootPresenter;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

import dagger.Provides;
import flow.Flow;
import mortar.MortarScope;
import mortar.ViewPresenter;

@Screen(R.layout.screen_green)
public class GreenScreen extends AbstractScreen<RootActivity.RootComponent> {

    @Override
    public Object createScreenComponent(RootActivity.RootComponent parentComponent) {
        return DaggerGreenScreen_Component.builder()
                .rootComponent(parentComponent)
                .module(new Module())
                .build();
    }

    //region ======================== DI ========================

    @dagger.Module
    public class Module {
        @Provides
        @GreenScope
        GreenScreen.GreenPresenter provideGreenPresenter() {
            return new GreenPresenter();
        }

        @Provides
        @GreenScope
        GreenModel provideGreenModel() {
            return new GreenModel();
        }
    }

    @dagger.Component(dependencies = RootActivity.RootComponent.class, modules = Module.class)
    @GreenScope
    public interface Component {
        void inject(GreenPresenter screen);

        void inject(GreenView view);
    }

    //endregion

    //region ======================== Presenter ========================

    public class GreenPresenter extends ViewPresenter<GreenView> {

        @Inject
        RootPresenter mRootPresenter;
        @Inject
        GreenModel mGreenModel;

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if (getView() == null) return;
            // TODO: 21.12.2016 init view
        }

        @Override
        protected void onEnterScope(MortarScope scope) {
            super.onEnterScope(scope);
            ((Component) scope.getService(DaggerService.SERVICE_NAME)).inject(this);
        }

        public void onButtonClick() {
            Flow.get(getView()).goBack();
        }
    }

    //endregion
}
