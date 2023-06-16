package com.example.marire.controller;

import com.example.marire.domain.Persoana;
import com.example.marire.domain.validator.PasswordValidator;
import com.example.marire.repo.PersoanaRepo;
import com.example.marire.service.PersoanaService;
import com.example.marire.utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField txtFieldNume;
    @FXML public TextField txtFieldPrenume;
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldParola;
    @FXML public TextField txtFieldParolaConfirmare;
    @FXML public ComboBox<String> cBoxFromOras;
    @FXML public TextField txtFieldStrada;
    @FXML public TextField txtFieldNumarStrada;
    @FXML public TextField txtFieldNumarTelefon;


    @FXML public Button btnContinueSignUp;

    final PasswordValidator passwordValidator;
    final PersoanaService persoanaService;

    public RegisterController() {

        this.persoanaService = new PersoanaService(new PersoanaRepo());
        this.passwordValidator = new PasswordValidator();
    }

    @FXML
    public void onContinueSignUp(ActionEvent event) throws Exception {
        final String nume = this.txtFieldNume.getText();
        final String prenume = this.txtFieldPrenume.getText();
        final String username = this.txtFieldUsername.getText();
        final String parola = this.txtFieldParola.getText();
        final String parolaConfirmare = this.txtFieldParolaConfirmare.getText();
        final String strada = this.txtFieldStrada.getText();
        final String numarStrada = this.txtFieldNumarStrada.getText();
        final String telefon = this.txtFieldNumarTelefon.getText();

        final String oras = this.cBoxFromOras.getSelectionModel().getSelectedItem();

        try {
            passwordValidator.validate(parola, parolaConfirmare);
            final Optional<Persoana> result = this.persoanaService.add(nume, prenume,
                    username, parola,oras,  strada, numarStrada, telefon);
            if (result.isPresent()) {
               // CurrentUser.initialize(result.get());
                this.login(result.get().getUsername(), event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ignored) {
            System.out.println("error");
        }
    }

    public void login(String username, ActionEvent event) {

        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ObservableList<String> orase =
                FXCollections.observableArrayList(Constants.ORASE);
        cBoxFromOras = new ComboBox<>();
        this.cBoxFromOras.setItems(orase);
    }
}
