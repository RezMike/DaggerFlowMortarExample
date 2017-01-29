package com.rezmike.flowapplication.utils;

import android.util.Log;

import com.rezmike.flowapplication.ui.abstracts.AbstractScreen;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mortar.MortarScope;

public class ScreenScoper {
    private static final String TAG = "ScreenScoper";
    private static Map<String, MortarScope> sScopeMap = new HashMap<>();

    public static MortarScope getScreenScope(AbstractScreen screen) {
        if (!sScopeMap.containsKey(screen.getScopeName())) {
            Log.d(TAG, "getScreenScope: create new scope");
            return createScreenScope(screen);
        } else {
            Log.d(TAG, "getScreenScope: return has scope");
            return sScopeMap.get(screen.getScopeName());
        }
    }

    public static void registerScope(MortarScope scope) {
        sScopeMap.put(scope.getName(), scope);
    }

    public static void cleanScopeMap() {
        Iterator<Map.Entry<String, MortarScope>> iterator = sScopeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, MortarScope> entry = iterator.next();
            if (entry.getValue().isDestroyed()) {
                iterator.remove();
            }
        }
    }

    public static void destroyScreenScope(String scopeName) {
        MortarScope mortarScope = sScopeMap.get(scopeName);
        mortarScope.destroy();
        cleanScopeMap();
    }

    private static String getParentScopeName(AbstractScreen screen) {
        try {
            String genericName = ((Class) ((ParameterizedType) screen.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]).getName();
            String parentScopeName = genericName;
            if (parentScopeName.contains("$")) {
                parentScopeName = parentScopeName.substring(0, genericName.indexOf("$"));
            }
            return parentScopeName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static MortarScope createScreenScope(AbstractScreen screen) {
        Log.d(TAG, "createScreenScope: with name : " + screen.getScopeName());
        MortarScope parentScope = sScopeMap.get(getParentScopeName(screen));
        Object screenComponent = screen.createScreenComponent(parentScope.getService(DaggerService.SERVICE_NAME));
        MortarScope newScope = parentScope.buildChild()
                .withService(DaggerService.SERVICE_NAME, screenComponent)
                .build(screen.getScopeName());
        registerScope(newScope);
        return newScope;
    }
}
