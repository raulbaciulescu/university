package com.example.controller;

import com.example.domain.NotaDto;
import com.example.service.ServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NoteStudentController {
    private ServiceManager service;
    ObservableList<NotaDto> observableArrayList = FXCollections.observableArrayList();
    @FXML
    TableView<NotaDto> tableView;

    @FXML
    TableColumn<NotaDto, Double> tableColumnNota;

    @FXML
    TableColumn<NotaDto, String> tableColumnTema;

    @FXML
    Label numeStudent;



    private List<NotaDto> getNotaDTOList() {
        long id = 0L;
        try {
            File myObj = new File("C:\\Users\\raulb\\Desktop\\filter_sem9\\src\\main\\java\\com\\example\\controller\\citire_student.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = Long.parseLong(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        long finalId = id;
        numeStudent.setText(service.findById(id).getName());
        return service.findAllGrades()
                .stream()
                .filter(n -> n.getStudent().getId() == finalId)
                .map(n -> new NotaDto(n.getStudent().getName(), n.getTema().getId(), n.getValue(), n.getProfesor()))
                .collect(Collectors.toList());
    }


    @FXML
    public void initialize() {
        service = new ServiceManager();
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<NotaDto, Double>("nota"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("temaId"));
        observableArrayList.setAll(getNotaDTOList());


        tableView.setItems(observableArrayList);
    }

}
