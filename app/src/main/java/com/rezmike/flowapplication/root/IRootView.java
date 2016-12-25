package com.rezmike.flowapplication.root;

import android.support.annotation.Nullable;

import com.rezmike.flowapplication.base.IView;

public interface IRootView extends IView {

    void showMessage(String message);
    void showMessage(int stringResId);
    void showError(Throwable e);

    void showLoad();
    void hideLoad();

    @Nullable
    IView getCurrentScreen();
}
