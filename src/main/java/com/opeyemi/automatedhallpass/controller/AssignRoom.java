package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.opeyemi.automatedhallpass.dbmodel.*;
import com.opeyemi.automatedhallpass.service.AppServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AssignRoom {

    @FXML
    private StackPane main;

    @FXML
    private JFXComboBox<StudentdetailsEntity> selectStudent;

    @FXML
    private JFXComboBox<HallEntity> selectHall;

    @FXML
    private JFXComboBox<RoomsEntity> SelectRoom;

    @FXML
    private JFXComboBox<BedspaceEntity> SelectBedSpace;

    @FXML
    private JFXButton save;

    @Autowired
    private AppServices appServices;


    @FXML
    public void initialize() {
        convertHallEntity();
        convertStudentEntity();
        convertRoomEntity();
        convertBedEntity();
        populateHallCombo();
        populateStudentCombo();
        populateRoomCombo();
        populateBedCombo();
    }

    @FXML
    void save(ActionEvent event) {

        int studentId  = selectStudent.getSelectionModel().getSelectedItem().getId();
        int bedSpaceId = selectStudent.getSelectionModel().getSelectedItem().getId();
        StudentHallEntity studentHallEntity = new StudentHallEntity();
        studentHallEntity.setAssignedBedId(bedSpaceId);
        studentHallEntity.setStudentId(studentId);

      StudentHallEntity studentHallEntity1 =  appServices.saveStudentHall(studentHallEntity);


        if(studentHallEntity1 != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(main.getScene().getWindow());
            alert.setTitle("Assign Student!!!");
            alert.setHeaderText("Assign Student");
            alert.setContentText("Student successfully assigned to hall");
            alert.show();

            selectStudent.getSelectionModel().select(-1);
            selectHall.getSelectionModel().select(-1);
            SelectRoom.getSelectionModel().select(-1);
            SelectBedSpace.getSelectionModel().select(-1);

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getScene().getWindow());
            alert.setTitle("Assign Student!!!");
            alert.setHeaderText("Assign Student");
            alert.setContentText("Student not successfully assigned to hall");
            alert.show();
        }
    }

    private void populateHallCombo() {
        List<HallEntity> hallEntities = appServices.findHalls();
        selectHall.itemsProperty().setValue(FXCollections.observableList(hallEntities));
    }

    private void convertHallEntity() {
        selectHall.setConverter(new StringConverter<HallEntity>() {
            @Override
            public String toString(HallEntity hallEntity) {
                return hallEntity.getHallDetails();
            }

            @Override
            public HallEntity fromString(String string) {
                return selectHall.getItems().stream().filter(hallEntity -> hallEntity.getHallDetails().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void populateStudentCombo() {
        List<StudentdetailsEntity> studentdetailsEntities = appServices.findStudents();
        selectStudent.itemsProperty().setValue(FXCollections.observableList(studentdetailsEntities));
    }

    private void convertStudentEntity() {
        selectStudent.setConverter(new StringConverter<StudentdetailsEntity>() {
            @Override
            public String toString(StudentdetailsEntity studentdetailsEntity) {
                return studentdetailsEntity.getLastName() + "(" + studentdetailsEntity.getMatricNo() + ")";
            }

            @Override
            public StudentdetailsEntity fromString(String string) {
                return selectStudent.getItems().stream().filter(studentdetailsEntity -> studentdetailsEntity.getLastName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void populateRoomCombo() {
        List<RoomsEntity> roomsEntities = appServices.findRoom();
        SelectRoom.itemsProperty().setValue(FXCollections.observableList(roomsEntities));
    }

    private void convertRoomEntity() {
        SelectRoom.setConverter(new StringConverter<RoomsEntity>() {
            @Override
            public String toString(RoomsEntity roomsEntity) {
                return roomsEntity.getRoomDescription();
            }

            @Override
            public RoomsEntity fromString(String string) {
                return SelectRoom.getItems().stream().filter(roomsEntity -> roomsEntity.getRoomDescription().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void populateBedCombo() {
        List<BedspaceEntity> bedspaceEntities = appServices.findBedSpace();
        SelectBedSpace.itemsProperty().setValue(FXCollections.observableList(bedspaceEntities));
    }

    private void convertBedEntity() {
        SelectBedSpace.setConverter(new StringConverter<BedspaceEntity>() {
            @Override
            public String toString(BedspaceEntity bedspaceEntity) {
                return bedspaceEntity.getBedSpace();
            }

            @Override
            public BedspaceEntity fromString(String string) {
                return SelectBedSpace.getItems().stream().filter(bedspaceEntity -> bedspaceEntity.getBedSpace().equals(string)).findFirst().orElse(null);
            }
        });
    }
}