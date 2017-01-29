package com.rezmike.flowapplication.ui.abstracts;

import android.util.Log;

import com.rezmike.flowapplication.utils.ScreenScoper;

import flow.ClassKey;

import static android.content.ContentValues.TAG;

public abstract class AbstractScreen<T> extends ClassKey {

    public String getScopeName() {
        return getClass().getName();
    }

    public abstract Object createScreenComponent(T parentComponent);

    // TODO: 27.11.2016 unregister scope
    public void unregisterScope() {
        Log.d(TAG, "unregisterScope: " + getScopeName());
        ScreenScoper.destroyScreenScope(getScopeName());
    }
}
