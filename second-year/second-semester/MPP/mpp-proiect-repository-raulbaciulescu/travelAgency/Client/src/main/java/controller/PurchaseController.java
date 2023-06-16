package controller;
import domain.Purchase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.logging.log4j.core.util.Loader;
import services.FlightException;
import services.PurchaseException;
import services.Service;
import utils.Constants;
import utils.Navigation;
import utils.Resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseController {
    @FXML TextField txtFieldClientName;
    @FXML TextField txtFieldClientAddress;
    @FXML TextField txtFieldNrOfSeats;
    @FXML Button btnBack;
    @FXML Text text;
    @FXML VBox pane;
    @FXML public Text errors;
    private Service server;
    private Loader loader;
    private MainController mainController;
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void newFields(){
        HBox hb = new HBox();
        pane.getChildren().add(hb);
        TextField tf = new TextField();
        tf.setPromptText("Name here");
        hb.getChildren().addAll(tf);
    }

    public PurchaseController() {
    }

    public void setServer(Service server) {
        this.server = server;
    }

    void initialize1() throws SQLException {
        text.setText("Flight from " + Resources.getInstance().getSelectedFlight().getStart() + " to " +
                Resources.getInstance().getSelectedFlight().getDestination());
    }

    public void onBackBtnClicked(MouseEvent mouseEvent) throws IOException {
        //TODO
        Stage stage = new Stage();
        stage.setTitle("Purchase window");
        stage.setScene(((Node)(mouseEvent.getSource())).getParent().getScene());
        mainController.initialize1();
        stage.show();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void onPurchaseBtnClicked(MouseEvent mouseEvent) throws SQLException {
        errors.setVisible(false);
        List<String> tourists = new ArrayList<>();
        ObservableList<Node> childsVB = pane.getChildren();
        for (Node node: childsVB) {
            HBox hb = (HBox) node;
            ObservableList<Node> childsHB = hb.getChildren();
            TextField tf = (TextField)childsHB.get(0);
            tourists.add(tf.getText());
        }

        String clientName = txtFieldClientName.getText();
        String clientAddress = txtFieldClientAddress.getText();
        int nrOfSeats = Integer.parseInt(txtFieldNrOfSeats.getText());

        try {
            Purchase purchase = new Purchase(Resources.getInstance().getSelectedFlight(), clientName, clientAddress, tourists, nrOfSeats);
            server.purchase(purchase);
        } catch (PurchaseException e) {
            System.out.println("Purchase error " + e);
        }
    }
}
