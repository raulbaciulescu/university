package utils;

import controller.LoginController;
import controller.MainController;
import controller.PurchaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.rpcprotocol.ServiceRpcProxy;
import services.Service;

import java.io.IOException;
import java.util.Properties;

public class StartRpcClient extends Application {
    private Stage primaryStage;

    private static final int defaultChatPort = 55555;
    private static final String defaultServer = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartRpcClient.class.getResourceAsStream("/client.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatclient.properties " + e);
            return;
        }


        String serverIP = clientProps.getProperty("server.host", defaultServer);
        int serverPort = defaultChatPort;
        try {
            serverPort = Integer.parseInt(clientProps.getProperty("server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        Service server = new ServiceRpcProxy(serverIP, serverPort);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.LOG_IN));
        Parent root = loader.load();


        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.MAIN));
        Parent mainRoot = mainLoader.load();

        FXMLLoader purchaseLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.PURCHASE));
        Parent purchaseRoot = purchaseLoader.load();

        LoginController loginController = loader.getController();
        loginController.setServer(server);

        PurchaseController purchaseCtrl = purchaseLoader.getController();
        purchaseCtrl.setServer(server);

        MainController mainCtrl = mainLoader.getController();
        mainCtrl.setServer(server);

        mainCtrl.setParent(purchaseRoot);
        mainCtrl.setController(purchaseCtrl);

        loginController.setMainController(mainCtrl);
        loginController.setParent(mainRoot);
        purchaseCtrl.setController(mainCtrl);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
}
