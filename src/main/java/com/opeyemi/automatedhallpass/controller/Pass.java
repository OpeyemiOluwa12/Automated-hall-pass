package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opeyemi.automatedhallpass.dbmodel.BedspaceEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentdetailsEntity;
import com.opeyemi.automatedhallpass.repositories.StudentDetailsRepo;
import com.opeyemi.automatedhallpass.repositories.StudentHallRepo;
import com.opeyemi.automatedhallpass.testClass;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Pass {

    @FXML
    TableView<String> passTable;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField faculty;

    @FXML
    private JFXTextField matricNo;

    @FXML
    private JFXTextField level;

    @FXML
    private JFXTextField course;

    @FXML
    private JFXTextField hall;

    @FXML
    private JFXTextField roomNo;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField email;

    @Autowired
    StudentDetailsRepo studentDetailsRepo;

    @Autowired
    StudentHallRepo studentHallRepo;

    @FXML
    private TableColumn<?, ?> bookingDateCol;

    @FXML
    private TableColumn<?, ?> destinationCol;

    @FXML
    private TableColumn<?, ?> purposeOfVisitCol;

    @FXML
    private TableColumn<?, ?> nameAndAddressOfHostCol;

    @FXML
    private TableColumn<?, ?> timeOutCol;

    @FXML
    private TableColumn<?, ?> timeOfArrivalCol;

    @FXML
    private TableColumn<?, ?> signInCol;

    @FXML
    private TableColumn<?, ?> hallAdminCol;

    @FXML
    private TableColumn<?, ?> remarksCol;

    @FXML
    public void initialize(){



    }

    private void generateStudentPass(int studentId){
       StudentHallEntity studentHallEntity = studentHallRepo.findAllByStudentId(studentId);
      StudentdetailsEntity studentdetailsEntity =  studentHallEntity.getStudentdetailsByStudentId();
        BedspaceEntity bedspaceEntity = studentHallEntity.getBedspaceByAssignedBedId();
        String hall = bedspaceEntity.getRoomsByRoomId().getHallByHallId().getHallDetails();
        int roomNo = bedspaceEntity.getRoomsByRoomId().getRoomNo();

        name.setText(studentdetailsEntity.getFirstName() + ", " + studentdetailsEntity.getLastName());
        faculty.setText(studentdetailsEntity.getFaculty());
        matricNo.setText(studentdetailsEntity.getMatricNo());
        level.setText(studentdetailsEntity.getLevel());
        course.setText(studentdetailsEntity.getCourse());
        phone.setText(""+studentdetailsEntity.getPhoneNo());
        address.setText(studentdetailsEntity.getHomeAddress());
        email.setText(studentdetailsEntity.getEmailAddress());
        this.hall.setText(hall);
        this.roomNo.setText(""+ roomNo);

    }

    /**
     * Insert a new default row to the table, select a cell of it and scroll to it.
     */
    public void addRow() {

        // get current position
        TablePosition pos = table.getFocusModel().getFocusedCell();

        // clear current selection
        table.getSelectionModel().clearSelection();

        // create new record and add it to the model
        testClass.Data data = new testClass.Data(0d, 0d);
        table.getItems().add(data);

        // get last row
        int row = table.getItems().size() - 1;
        table.getSelectionModel().select(row, pos.getTableColumn());

        // scroll to new row
        table.scrollTo(data);

    }
}
