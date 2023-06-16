package com.monitoringsystem.controller;

import com.monitoringsystem.model.User;
import com.monitoringsystem.utils.NavResources;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class NavigationController {
    public static final HashMap<Long, NavigationController> navigationController = new HashMap<>();
    private final Stage stage;
    private final User user;
    private final GraphController graphController;

    public NavigationController(final String entryPointRoute, final User user) {
        this(new Stage(), entryPointRoute, user);
    }

    public NavigationController(final Stage stage,
                                final String entryPointRoute,
                                final User user) {
        this.stage = stage;
        this.user = user;

        final Parent parent = NavResources.load(entryPointRoute);
        final Scene scene = new Scene(parent);
        this.graphController = new GraphController(scene);
        setSceneAndShowStage();
        navigationController.put(user != null ? user.getId() : null, this);
    }

    private void setSceneAndShowStage() {
        final Scene scene = graphController.scene();
        stage.setScene(scene);
        stage.show();
    }

    public User getUser() {
        return user;
    }

    public GraphController getGraphController() {
        return graphController;
    }

    public static void navigateTo(final Long userId, final String route) {
        navigationController.get(userId).getGraphController().navigateTo(route);
    }
}
