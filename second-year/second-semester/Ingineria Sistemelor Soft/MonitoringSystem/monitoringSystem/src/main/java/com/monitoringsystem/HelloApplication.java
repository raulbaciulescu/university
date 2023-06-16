package com.monitoringsystem;

import com.monitoringsystem.controller.NavigationController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.monitoringsystem.utils.Constants;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            final String entryPointRoute = Constants.Scene.LOGIN;
            new NavigationController(stage, entryPointRoute, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}