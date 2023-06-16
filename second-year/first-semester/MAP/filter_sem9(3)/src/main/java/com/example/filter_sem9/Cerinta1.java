package com.example.filter_sem9;
import com.example.controller.Cerinta1Controller;
import com.example.controller.NotaController;
import com.example.service.ServiceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class Cerinta1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("cerinta1View.fxml"));

        //FXMLLoader loader = new FXMLLoader(TestSem8.class.getResource("notaView.fxml"));
        AnchorPane root=loader.load();

        Cerinta1Controller ctrl=loader.getController();

        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
