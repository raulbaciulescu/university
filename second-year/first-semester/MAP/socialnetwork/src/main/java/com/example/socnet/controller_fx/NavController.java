package com.example.socnet.controller_fx;

import com.example.socnet.SocNet;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;

public class NavController {

    public static void navigate(@NotNull final String destinationResource,
                                @NotNull final Event event) {
        try {
            @Nullable final URL url = SocNet.class
                    .getResource(destinationResource);
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            final Scene scene = new Scene(fxmlLoader.load());
            final Stage stage = (Stage)
                    ((Node) event.getTarget()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
