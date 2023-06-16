package com.example.socnet;

import com.example.socnet.domain.util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

public class SocNet extends Application {

    public static void main(@NotNull final String[] args) {
        Application.launch();
    }

    @Override
    public void start(@NotNull final Stage stage)
            throws IOException {
        @Nullable final URL url = this.getClass()
                .getResource(Constants.UI.Scene.LOG_IN);
        final FXMLLoader fxmlLoader = new FXMLLoader(url);
        final Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}