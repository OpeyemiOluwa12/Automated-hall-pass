package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.opeyemi.automatedhallpass.dbmodel.HallEntity;
import com.opeyemi.automatedhallpass.repositories.HallRepo;
import com.opeyemi.automatedhallpass.service.Services;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class AssignRoom {
    @FXML
    private JFXComboBox<String> selectStudent;

    @FXML
    private JFXComboBox<HallEntity> selectHall;

    @FXML
    private JFXComboBox<String> SelectRoom;

    @FXML
    private JFXComboBox<String> SelectBedSpace;

    @FXML
    private JFXButton save;

    @Autowired
    private Services services;


    @FXML
    public void initialize() {

//        populateStudentCombo();
    }

    @FXML
    void save(ActionEvent event) {

    }

    private void populateStudentCombo() {
        List<HallEntity> hallEntities = services.findHalls();
        selectHall.itemsProperty().setValue(FXCollections.observableList(hallEntities));
    }

    private void convertHallEntity() {
        selectHall.setConverter(new StringConverter<HallEntity>() {
            @Override
            public String toString(HallEntity hallEntity) {
                return hallEntity.getHallId();
            }

            @Override
            public HallEntity fromString(String string) {
                return selectHall.getItems().stream().filter(hallEntity -> hallEntity.getHallId().equals(string)).findFirst().orElse(null);
            }
        });
    }
}
