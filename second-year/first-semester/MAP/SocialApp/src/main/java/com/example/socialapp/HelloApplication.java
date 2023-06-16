package com.example.socialapp;

import com.example.socialapp.domain.Friendship;
import com.example.socialapp.domain.User;
import com.example.socialapp.utils.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Resources resource = Resources.getInstance();
        User user1 = new User(14681386618L, "raul", "baciulescu");
        //User user2 = new User(1L, "stan", "castan");
        Resources.userService.add(user1);
        //Resources.userService.add(user2);
        //Resources.friendshipRepo.add(user1, user2);



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}