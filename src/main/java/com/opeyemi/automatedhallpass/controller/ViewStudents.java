package com.opeyemi.automatedhallpass.controller;

import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.service.AppServices;
import com.opeyemi.automatedhallpass.testClass;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ViewStudents {

    @FXML
    private TableView<StudentHallEntity> students;

    @FXML
    private TableColumn<StudentHallEntity, String> lastNameCol;

    @FXML
    private TableColumn<StudentHallEntity, String> firstNameCol;

    @FXML
    private TableColumn<StudentHallEntity, String> matricNoCol;

    @FXML
    private TableColumn<StudentHallEntity, String> facultyCol;

    @FXML
    private TableColumn<StudentHallEntity, String> courseCol;

    @FXML
    private TableColumn<StudentHallEntity, String> levelCol;

    @FXML
    private TableColumn<StudentHallEntity, String> emailAddressCol;

    @FXML
    private TableColumn<StudentHallEntity, String> hallCol;

    @FXML
    private TableColumn<StudentHallEntity, String> roomNoCol;

    private ObservableList<StudentHallEntity> studentList = FXCollections.observableArrayList();


    @Autowired
    private AppServices appServices;

    @FXML
    public void initialize() {

//        loadStudents();

        columnConverters();
        rowConverters();
        students.getItems().add(new StudentHallEntity());
        students.getItems().addAll(studentList);
    }

    private void loadStudents() {
        List<StudentHallEntity> studentHallEntityList = appServices.getStudentHall();
        studentList.addAll(studentHallEntityList);
    }

    private void convertStudents() {

    }

    private void rowConverters() {
//    students.setRowFactory(new Callback<TableView<ObservableList<String>>, TableRow<ObservableList<String>>>() {
//        @Override
//        public TableRow<ObservableList<String>> call(TableView<ObservableList<String>> param) {
//            TableRow<ObservableList<String>> row = new TableRow<>();
////            row.setOnMouseClicked(event -> {
////                if(event.getClickCount() == 2){
////                    ObservableList<String> rowItem = FXCollections.observableArrayList();
////                    students.getItems().add(rowItem);
////                }
////            });
//
//            return row;
//        }
//    });
    }



    private void columnConverters() {

        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getLastName()));

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getFirstName()));

        matricNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        matricNoCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getMatricNo()));

        facultyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        facultyCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getFaculty()));

        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getCourse()));

        levelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        levelCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getLevel()));

        emailAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailAddressCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStudentdetailsByStudentId().getEmailAddress()));

        hallCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hallCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBedspaceByAssignedBedId().getRoomsByRoomId().getHallByHallId().getHallDetails()));

        roomNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        roomNoCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBedspaceByAssignedBedId().getRoomsByRoomId().getRoomDescription()));

    }
}
