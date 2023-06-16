package controller;
import domain.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.FlightException;
import services.LoginException;
import services.Observer;
import services.Service;
import utils.Resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class MainController implements Observer {
    @FXML TableView<Flight> tableView;
    @FXML TableColumn<Flight, Long> tableColumnId;
    @FXML TableColumn<Flight, String> tableColumnStart;
    @FXML TableColumn<Flight, String> tableColumnDestination;
    @FXML TableColumn<Flight, String> tableColumnStartDate;
    @FXML TableColumn<Flight, String> tableColumnNrOfSeats;
    @FXML Button btnSearch;
    @FXML Button btnLogout;
    @FXML Button btnPurchase;
    @FXML public Text errors;
    public DatePicker datePicker;
    public TextField txtFieldDestination;

    private final ObservableList<Flight> observableList = FXCollections.observableArrayList();
    private Service server;
    Parent parent;
    private PurchaseController purchaseController;

    public void setServer(Service server) {
        this.server = server;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
    }
    public void setController(PurchaseController purchaseController) {
        this.purchaseController = purchaseController;
    }


    public void initialize1() {
        tableColumnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tableColumnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tableColumnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableColumnNrOfSeats.setCellValueFactory(new PropertyValueFactory<>("nrOfSeats"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnId.setVisible(false);
        try {
            observableList.addAll(server.getFlights());
        } catch (FlightException e) {
            System.out.println("FlightException!!!!");
        }
        tableView.setItems(observableList);
    }

    public void updateFlight(Flight flightDto) {
        System.out.println("Update Flight in MainController");
        int i = 0;
        for (Flight flight : observableList) {
            if (Objects.equals(flight.getId(), flightDto.getId()) && flightDto.getNrOfSeats() != 0) {
                flight.setNrOfSeats(flightDto.getNrOfSeats());
                tableView.getItems().set(i, flight);
            }
            if (Objects.equals(flight.getId(), flightDto.getId()) && flightDto.getNrOfSeats() == 0) {
                //observableList.remove(flight);
                tableView.getItems().remove(flight);
            }
            i++;
        }
    }

    public void onLogoutBtnClick(MouseEvent mouseEvent) throws SQLException {
        try {
            server.logout(Resources.getInstance().getCurrentUser(), this);
            Resources.getInstance().logoutCurrentUser();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            errors.setVisible(true);
        }
    }

    public void onSearchBtnClicked(MouseEvent mouseEvent) {
        errors.setVisible(false);
        try {
            Predicate<Flight> p1 = n -> n.getDestination().getName().contains(txtFieldDestination.getText());
            Predicate<Flight> p2 = n -> n.getDestination().getAirport().contains(txtFieldDestination.getText());
            Predicate<Flight> p3 = n -> n.getStartDate().getDayOfMonth() == datePicker.getValue().getDayOfMonth() &&
                    n.getStartDate().getMonth() == datePicker.getValue().getMonth() &&
                    n.getStartDate().getYear() == datePicker.getValue().getYear();
            List<Flight> filteredUsers = server.getFlights()
                    .stream()
                    .filter(p3.and(p1.or(p2)))
                    .toList();
            observableList.setAll(filteredUsers);
        }
        catch (Exception e) {
            errors.setVisible(true);
        }
    }

    public void onPurchaseBtnClicked(MouseEvent mouseEvent) {
        try {
            Flight flight = tableView.getSelectionModel().getSelectedItems().get(0);
            Resources.getInstance().setFlight(flight);
            Stage stage = new Stage();
            stage.setTitle("Purchase window");
            stage.setScene(new Scene(parent));
            purchaseController.initialize1();
            stage.show();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e) {
            errors.setVisible(true);
        }
    }
}
