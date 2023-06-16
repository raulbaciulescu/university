package com.example.controller;

import com.example.domain.NotaDto;
import com.example.service.ServiceManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Cerinta1Controller {
    ObservableList<NotaDto> observableList;

    @FXML
    TextField fieldStudent;

    @FXML
    TextField fieldNota;

    @FXML
    TextField fieldTema;

    public void setObservableList(ObservableList<NotaDto> observableList) {
        this.observableList = observableList;
    }

    @FXML
    public void adaugaTema(ActionEvent actionEvent) {
            observableList.add(new NotaDto(fieldStudent.getText(),
                    fieldTema.getText(),
                    Double.parseDouble(fieldNota.getText()),
                    ""));
    }
}
