package com.rezmike.flowapplication.ui.abstracts;

import android.support.annotation.Nullable;

public abstract class AbstractPresenter<T extends IView> {
    private T mView;

    public void takeView(T view) {
        mView = view;
    }

    public void dropView() {
        mView = null;
    }

    public abstract void initView();

    @Nullable
    public T getView() {
        return mView;
    }
}
