package pizzashop;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pizzashop.controller.MainGUIController;
import pizzashop.gui.KitchenGUI;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.MenuRepositoryImpl;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryImpl;
import pizzashop.service.PizzaService;
import pizzashop.service.PizzaServiceImpl;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuRepository repoMenu = new MenuRepositoryImpl();
        PaymentRepository payRepo = new PaymentRepositoryImpl();
        PizzaService service = new PizzaServiceImpl(repoMenu, payRepo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainFXML.fxml"));
        //VBox box = loader.load();
        Parent box = loader.load();
        MainGUIController ctrl = loader.getController();
        ctrl.setService(service);
        primaryStage.setTitle("PizzeriaX");
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit the Main window?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = exitAlert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    //Stage stage = (Stage) this.getScene().getWindow();
                    try {
                        System.out.println("Incasari cash: " + service.getTotalAmount(PaymentType.CASH));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        System.out.println("Incasari card: " + service.getTotalAmount(PaymentType.CARD));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    primaryStage.close();
                }
                // consume event
                else if (result.get() == ButtonType.NO) {
                    event.consume();
                } else {
                    event.consume();

                }

            }
        });
        primaryStage.setScene(new Scene(box));
        primaryStage.show();
        KitchenGUI kitchenGUI = new KitchenGUI();
        kitchenGUI.KitchenGUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
