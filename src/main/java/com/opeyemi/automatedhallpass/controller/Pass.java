package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opeyemi.automatedhallpass.dbmodel.BedspaceEntity;
import com.opeyemi.automatedhallpass.dbmodel.HallpassEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentdetailsEntity;
import com.opeyemi.automatedhallpass.repositories.StudentDetailsRepo;
import com.opeyemi.automatedhallpass.repositories.StudentHallRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class Pass {

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

    @FXML
    private TableView<HallpassEntity> passTable;

    @FXML
    private TableColumn<HallpassEntity, Date> bookingDateCol;

    @FXML
    private TableColumn<HallpassEntity, String> destinationCol;

    @FXML
    private TableColumn<HallpassEntity, String> purposeOfVisitCol;

    @FXML
    private TableColumn<HallpassEntity, String> nameOfHostCol;

    @FXML
    private TableColumn<HallpassEntity, String> addressOfHostCol;

    @FXML
    private TableColumn<HallpassEntity, Date> timeOutCol;

    @FXML
    private TableColumn<HallpassEntity, Date> timeOfArrivalCol;

    @FXML
    private TableColumn<HallpassEntity, String> signInCol;

    @FXML
    private TableColumn<HallpassEntity, String> hallAdminCol;

    @FXML
    private TableColumn<HallpassEntity, String> remarksCol;

    @FXML
    private TableColumn<HallpassEntity, String> statusCol;


    @Autowired
    StudentDetailsRepo studentDetailsRepo;

    @Autowired
    StudentHallRepo studentHallRepo;

    @Autowired
    Data data;

    @Autowired
    ContentNode contentNode;

    private ObservableList<HallpassEntity> hallpassData = FXCollections.observableArrayList();
    @FXML
    private JFXButton addNewRequest;


    @FXML
    public void initialize() {
        StudentHallEntity studentHallEntity = ((StudentHallEntity) data.getData());
        generateStudentPass(studentHallEntity);
        addNewRequest.visibleProperty().set(!contentNode.isAdmin());

    }

    @FXML
    void addNewRequest(ActionEvent event) {
        addRow();
    }

    private void generateStudentPass(StudentHallEntity studentHallEntity) {
        StudentdetailsEntity studentdetailsEntity = studentHallEntity.getStudentdetailsByStudentId();
        BedspaceEntity bedspaceEntity = studentHallEntity.getBedspaceByAssignedBedId();
        String hall = bedspaceEntity.getRoomsByRoomId().getHallByHallId().getHallDetails();
        int roomNo = bedspaceEntity.getRoomsByRoomId().getRoomNo();

        name.setText(studentdetailsEntity.getFirstName() + ", " + studentdetailsEntity.getLastName());
        faculty.setText(studentdetailsEntity.getFaculty());
        matricNo.setText(studentdetailsEntity.getMatricNo());
        level.setText(studentdetailsEntity.getLevel());
        course.setText(studentdetailsEntity.getCourse());
        phone.setText("" + studentdetailsEntity.getPhoneNo());
        address.setText(studentdetailsEntity.getHomeAddress());
        email.setText(studentdetailsEntity.getEmailAddress());
        this.hall.setText(hall);
        this.roomNo.setText("" + roomNo);

    }

    /**
     * Insert a new default row to the table, select a cell of it and scroll to it.
     */
    public void addRow() {

        // get current position
        TablePosition pos = passTable.getFocusModel().getFocusedCell();

        // clear current selection
        passTable.getSelectionModel().clearSelection();

        // create new record and add it to the model
        HallpassEntity data = new HallpassEntity();
        data.setBookingDate(new Date());
        passTable.getItems().add(data);

        // get last row
        int row = passTable.getItems().size() - 1;
        passTable.getSelectionModel().select(row, pos.getTableColumn());

        // scroll to new row
        passTable.scrollTo(data);

    }
}
