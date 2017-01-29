package com.rezmike.flowapplication.ui.root;

import android.support.annotation.Nullable;

import com.rezmike.flowapplication.ui.abstracts.IView;

public interface IRootView extends IView {

    void showMessage(String message);
    void showMessage(int stringResId);
    void showError(Throwable e);

    void showLoad();
    void hideLoad();

    @Nullable
    IView getCurrentScreen();
}
