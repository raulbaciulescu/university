package com.monitoringsystem.controller;

import com.monitoringsystem.utils.Constants;
import com.monitoringsystem.utils.NavResources;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public record GraphController(Scene scene) {

    private static final Map<String, URL> routes = new HashMap<>() {{
        put(Constants.Scene.LOGIN, NavResources.urlOf(Constants.Scene.LOGIN));
        put(Constants.Scene.BOSS, NavResources.urlOf(Constants.Scene.BOSS));
        put(Constants.Scene.EMPLOYEE, NavResources.urlOf(Constants.Scene.EMPLOYEE));
        put(Constants.Scene.ADMIN, NavResources.urlOf(Constants.Scene.ADMIN));
        put(Constants.Scene.ADD_USER, NavResources.urlOf(Constants.Scene.ADD_USER));
        put(Constants.Scene.BOSS_TASKS, NavResources.urlOf(Constants.Scene.BOSS_TASKS));
    }};

    public void navigateTo(final String resourceFileName) {
        navigateTo(routes.get(resourceFileName));
    }

    private void navigateTo(final URL url) {
        if (url != null) {
            navigateTo(NavResources.load(url));
        }
    }

    private void navigateTo(final Parent root) {
        if (root != null) {
            scene.setRoot(root);
        }
    }
}
