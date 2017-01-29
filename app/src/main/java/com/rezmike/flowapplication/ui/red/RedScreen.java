package com.rezmike.flowapplication.ui.red;

import android.os.Bundle;

import com.rezmike.flowapplication.R;
import com.rezmike.flowapplication.ui.abstracts.AbstractScreen;
import com.rezmike.flowapplication.ui.abstracts.Screen;
import com.rezmike.flowapplication.ui.green.GreenScreen;
import com.rezmike.flowapplication.ui.root.RootActivity;
import com.rezmike.flowapplication.ui.root.RootPresenter;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

import dagger.Provides;
import flow.Flow;
import mortar.MortarScope;
import mortar.ViewPresenter;

@Screen(R.layout.screen_red)
public class RedScreen extends AbstractScreen<RootActivity.RootComponent> {

    @Override
    public Object createScreenComponent(RootActivity.RootComponent parentComponent) {
        return DaggerRedScreen_Component.builder()
                .rootComponent(parentComponent)
                .module(new Module())
                .build();
    }

    //region ======================== DI ========================

    @dagger.Module
    public class Module {
        @Provides
        @RedScope
        RedPresenter provideRedPresenter() {
            return new RedPresenter();
        }

        @Provides
        @RedScope
        RedModel provideRedModel() {
            return new RedModel();
        }
    }

    @dagger.Component(dependencies = RootActivity.RootComponent.class, modules = Module.class)
    @RedScope
    public interface Component {
        void inject(RedPresenter screen);

        void inject(RedView view);
    }

    //endregion

    //region ======================== Presenter ========================

    public class RedPresenter extends ViewPresenter<RedView> {

        @Inject
        RootPresenter mRootPresenter;
        @Inject
        RedModel mRedModel;

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
            Flow.get(getView()).set(new GreenScreen());
        }
    }

    //endregion
}
