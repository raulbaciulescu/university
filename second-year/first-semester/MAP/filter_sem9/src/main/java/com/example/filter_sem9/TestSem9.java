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

//        FXMLLoader loader=new FXMLLoader();
//        loader.setLocation(getClass().getResource("notaView.fxml"));
//
//        FXMLLoader loader1 = new FXMLLoader();
//        loader1.setLocation(getClass().getResource("cerinta_b.fxml"));
//
//
//        //FXMLLoader loader = new FXMLLoader(TestSem8.class.getResource("notaView.fxml"));
//        AnchorPane root=loader1.load();
//
//        NotaController ctrl=loader1.getController();
//        ctrl.setService(new ServiceManager());
//
//        primaryStage.setScene(new Scene(root, 700, 500));
//        primaryStage.setTitle("Hello World");
//        primaryStage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cerinta_b.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        primaryStage.setTitle("cerinta_b");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
