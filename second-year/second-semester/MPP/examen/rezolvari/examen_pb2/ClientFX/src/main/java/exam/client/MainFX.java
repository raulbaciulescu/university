package exam.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import exam.client.gui.LoginController;
import exam.client.gui.MainController;
import exam.networking.rpcprotocol.ExamServicesRpcProxy;
import exam.services.IExamServices;

import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    private Stage primaryStage;
    private static int defaultPort = 55554;
    private static String defaultServer = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(MainFX.class.getResourceAsStream("/examClient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find examClient.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("model.server.host", defaultServer);
        int serverPort = defaultPort;

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("model.server.port"));

        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        //IExamServices server = new ExamServicesRpcProxy(serverIP, serverPort);
        RestClient server=new RestClient();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/login.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.<LoginController>getController();
        loginController.setServer(server);

        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/main.fxml"));
        Parent mainRoot = mainLoader.load();

        MainController mainController = mainLoader.<MainController>getController();
        mainController.setServer(server);

        loginController.setMainController(mainController);
        loginController.setParent(mainRoot);

        primaryStage.setTitle("MPP swimming contest");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
