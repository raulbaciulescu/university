package utils;
import controller.LoginController;
import controller.MainController;
import controller.ResultsController;
import controller.StartGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rpcprotocol.Proxy;
import services.Service;

import java.io.IOException;
import java.util.Properties;

public class StartClient extends Application {
    private Stage primaryStage;

    private static final int defaultChatPort = 55555;
    private static final String defaultServer = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartClient.class.getResourceAsStream("/client.properties"));
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

        Service server = new Proxy(serverIP, serverPort);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.LOG_IN));
        Parent root = loader.load();

        FXMLLoader startGameLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.START_GAME));
        Parent startGameRoot = startGameLoader.load();

        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.MAIN));
        Parent mainRoot = mainLoader.load();

        FXMLLoader resultsLoader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.Scene.RESULTS));
        Parent resultsRoot = resultsLoader.load();

        LoginController loginController = loader.getController();
        loginController.setServer(server);

        StartGameController startGameController = startGameLoader.getController();
        startGameController.setServer(server);

        ResultsController resultsController = resultsLoader.getController();
        resultsController.setServer(server);

        MainController mainController = mainLoader.getController();
        mainController.setServer(server);

        mainController.setParent(resultsRoot);
        mainController.setResultsController(resultsController);

        startGameController.setMainController(mainController);
        startGameController.setParent(mainRoot);

        loginController.setStartGameController(startGameController);
        loginController.setParent(startGameRoot);
        //purchaseCtrl.setController(mainCtrl);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
