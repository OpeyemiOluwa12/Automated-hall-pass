package com.opeyemi.automatedhallpass.controller;

import com.opeyemi.automatedhallpass.bootstrap.Utils;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.service.AppServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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


    @FXML
    private TableColumn<StudentHallEntity, String> viewPass;

    private ObservableList<StudentHallEntity> studentList = FXCollections.observableArrayList();


    @Autowired
    private AppServices appServices;

    @Autowired
    private Data data;

    @Autowired
    ContentNode contentNode;

    @Autowired
    Utils utils;

    @FXML
    public void initialize() {

        loadStudents();

        columnConverters();
        rowConverters();
        students.getItems().addAll(studentList);
    }

    private void loadStudents() {
        List<StudentHallEntity> studentHallEntityList = appServices.getStudentHall();
        studentList.clear();
        studentList.addAll(studentHallEntityList);
    }

    private void convertStudents() {

    }

    private void rowConverters() {
        students.setRowFactory(tv -> {
            TableRow<StudentHallEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    StudentHallEntity rowData = row.getItem();
                    System.out.println(rowData);
                }
            });
            return row;
        });
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

//


        viewPass.setCellValueFactory(new PropertyValueFactory<>("ACTION"));

        Callback<TableColumn<StudentHallEntity, String>, TableCell<StudentHallEntity, String>> cellFactory
                = //
                new Callback<TableColumn<StudentHallEntity, String>, TableCell<StudentHallEntity, String>>() {
                    @Override
                    public TableCell call(final TableColumn<StudentHallEntity, String> param) {
                        final TableCell<StudentHallEntity, String> cell = new TableCell<StudentHallEntity, String>() {

                            final Button btn = new Button("View Pass");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        StudentHallEntity studentHallEntity = getTableView().getItems().get(getIndex());
                                        System.out.println(studentHallEntity.getStudentdetailsByStudentId().getLastName()
                                                + "   " + studentHallEntity.getStudentdetailsByStudentId().getFirstName());
                                        data.setData(studentHallEntity);
                                        contentNode.setAdminNode(utils.loadFXML("classpath:fxml/pass.fxml"));
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        viewPass.setCellFactory(cellFactory);


    }
}
