package com.rezmike.flowapplication.ui.yellow;

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

@Screen(R.layout.screen_yellow)
public class YellowScreen extends AbstractScreen<RootActivity.RootComponent> {

    @Override
    public Object createScreenComponent(RootActivity.RootComponent parentComponent) {
        return DaggerYellowScreen_Component.builder()
                .rootComponent(parentComponent)
                .module(new Module())
                .build();
    }

    //region ======================== DI ========================

    @dagger.Module
    public class Module {

        @Provides
        @YellowScope
        YellowPresenter provideYellowPresenter() {
            return new YellowPresenter();
        }

        @Provides
        @YellowScope
        YellowModel provideYellowModel() {
            return new YellowModel();
        }
    }

    @dagger.Component(dependencies = RootActivity.RootComponent.class, modules = Module.class)
    @YellowScope
    public interface Component {
        void inject(YellowPresenter presenter);

        void inject(YellowView view);
    }

    //endregion

    //region ======================== Presenter ========================

    public class YellowPresenter extends ViewPresenter<YellowView> {

        @Inject
        RootPresenter mRootPresenter;
        @Inject
        YellowModel mYellowModel;

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
