package com.example.filter_sem9;
import com.example.controller.NotaController;
import com.example.service.ServiceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class TestSem9 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("notaView.fxml"));

        AnchorPane root=loader.load();

        NotaController ctrl=loader.getController();
        //ctrl.setService( ServiceManager.getInstance(), );

        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
