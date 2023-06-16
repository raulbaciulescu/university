package com.monitoringsystem.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class NavResources {
    public static URL urlOf(final String resourceFileName) {
        return ClassLoader.getSystemResource(resourceFileName);
    }

    public static Parent load(final String resourceFileName) {
        return load(urlOf(resourceFileName));
    }

    public static Parent load(final URL url) {
        try {
            return FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
