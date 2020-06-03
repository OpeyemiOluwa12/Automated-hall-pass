package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import com.opeyemi.automatedhallpass.dbmodel.BedspaceEntity;
import com.opeyemi.automatedhallpass.dbmodel.HallpassEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentdetailsEntity;
import com.opeyemi.automatedhallpass.repositories.HallPassRepo;
import com.opeyemi.automatedhallpass.repositories.StudentDetailsRepo;
import com.opeyemi.automatedhallpass.repositories.StudentHallRepo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Pass {

    @FXML
    private StackPane mainPane;

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
    private TableColumn<HallpassEntity, String> bookingDateCol;

    @FXML
    private TableColumn<HallpassEntity, String> destinationCol;

    @FXML
    private TableColumn<HallpassEntity, String> purposeOfVisitCol;

    @FXML
    private TableColumn<HallpassEntity, String> nameOfHostCol;

    @FXML
    private TableColumn<HallpassEntity, String> addressOfHostCol;

    @FXML
    private TableColumn<HallpassEntity, String> timeOutCol;

    @FXML
    private TableColumn<HallpassEntity, String> timeOfArrivalCol;

    @FXML
    private TableColumn<HallpassEntity, String> signInCol;

    @FXML
    private TableColumn<HallpassEntity, String> hallAdminCol;

    @FXML
    private TableColumn<HallpassEntity, String> remarksCol;

    @FXML
    private TableColumn<HallpassEntity, String> statusCol;

    @FXML
    private TableColumn<HallpassEntity, String> saveCol;


    @Autowired
    private StudentDetailsRepo studentDetailsRepo;

    @Autowired
    private StudentHallRepo studentHallRepo;

    @Autowired
    private HallPassRepo hallPassRepo;

    @Autowired
    private Data data;

    @Autowired
    private ContentNode contentNode;


    private ObservableList<HallpassEntity> hallpassData = FXCollections.observableArrayList();

    @FXML
    private JFXButton addNewRequest;

    @Autowired
    private Utils utils;

    private StudentHallEntity studentHallEntity;


    @FXML
    public void initialize() {
        studentHallEntity = ((StudentHallEntity) data.getData());
        generateStudentPass(studentHallEntity);
        addNewRequest.visibleProperty().set(!contentNode.isAdmin());

        tableColumnConverter();
        loadHallPassData();

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

    private void loadHallPassData(){
       List<HallpassEntity> hallpassEntityList = hallPassRepo.findAllByStudentId(studentHallEntity.getStudentId());
       passTable.getItems().addAll(hallpassEntityList);
    }


    private void tableColumnConverter() {

        bookingDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bookingDateCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBookingDate()));

        destinationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        destinationCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDestination()));

        purposeOfVisitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        purposeOfVisitCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPurposeOfVisit()));

        nameOfHostCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfHostCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNameOfHost()));

        addressOfHostCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressOfHostCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAddressOfHost()));

        timeOutCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeOutCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTimeOut()));

        timeOfArrivalCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeOfArrivalCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTimeOfArrival()));

        signInCol.setCellFactory(TextFieldTableCell.forTableColumn());
        signInCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSignIn()));

        hallAdminCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hallAdminCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAdmindetailsByHallAdminId()
                != null ? param.getValue().getAdmindetailsByHallAdminId().getFirstName() : ""));

        remarksCol.setCellFactory(TextFieldTableCell.forTableColumn());
        remarksCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRemarks()));

        statusCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStatus()));


        saveCol.setCellValueFactory(new PropertyValueFactory<>("ACTION"));

        Callback<TableColumn<HallpassEntity, String>, TableCell<HallpassEntity, String>> cellFactory
                = //
                new Callback<TableColumn<HallpassEntity, String>, TableCell<HallpassEntity, String>>() {
                    @Override
                    public TableCell call(final TableColumn<HallpassEntity, String> param) {
                        final TableCell<HallpassEntity, String> cell = new TableCell<HallpassEntity, String>() {

                            final Button btn = new Button("Save");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        HallpassEntity hallpassEntity = getTableView().getItems().get(getIndex());
                                        hallpassEntity.setStudentId(studentHallEntity.getStudentId());
                                        hallpassEntity.setStatus("pending");
                                        //Todo fetch HallAdmin
                                        hallpassEntity.setHallAdminId(1);
//                                        data.setData(studentHallEntity);
                                        hallPassRepo.save(hallpassEntity);
                                        alert();
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        saveCol.setCellFactory(cellFactory);


    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.setTitle("Pass Request!!!");
        alert.setHeaderText("Pass Request");
        alert.setContentText("Request pass successful");
        alert.show();

        passTable.getItems().clear();
        loadHallPassData();

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String date = simpleDateFormat.format(new Date());

        data.setBookingDate(date);
        passTable.getItems().add(data);

        // get last row
        int row = passTable.getItems().size() - 1;
        passTable.getSelectionModel().select(row, pos.getTableColumn());

        // scroll to new row
        passTable.scrollTo(data);

    }
}
