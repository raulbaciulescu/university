package utils;

import controller.MainController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static void navigate(String destination, Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getClassLoader().getResource(destination));
        final Stage stage = (Stage)
                ((Node) event.getTarget()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
