package com.example.filter_sem9;

import com.example.controller.Cerinta1Controller;
import com.example.controller.NotaController;
import com.example.domain.NotaDto;
import com.example.service.ServiceManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ObservableList<NotaDto> observableList = FXCollections.observableArrayList();

        NotaController ctrl=fxmlLoader.getController();
        ctrl.setService( ServiceManager.getInstance(), observableList);

        stage.setTitle("Note");
        stage.setScene(scene);
        stage.show();

        FXMLLoader fxmlLoaderA = new FXMLLoader(HelloApplication.class.getResource("cerinta1View.fxml"));
        Scene sceneA = new Scene(fxmlLoaderA.load(), 320, 240);
        Stage stageA = new Stage();

        Cerinta1Controller cerinta1Controller=fxmlLoaderA.getController();
        cerinta1Controller.setObservableList( observableList);

        stageA.setTitle("A");
        stageA.setScene(sceneA);
        stageA.show();
    }

    public static void main(String[] args) {
        launch();
    }
}