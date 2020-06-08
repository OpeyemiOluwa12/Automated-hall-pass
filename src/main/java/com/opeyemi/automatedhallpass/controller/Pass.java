package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @FXML
    private TableColumn<HallpassEntity, String> actionCol;


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


    @FXML
    private DatePicker bookingDatePicker;

    @FXML
    private JFXTextField destinationText;

    @FXML
    private JFXTextField purposeOfVistText;

    @FXML
    private JFXTextField nameOfHostText;

    @FXML
    private JFXTextField addressOfHostText;

    @FXML
    private DatePicker timeOutPicker;

    @FXML
    private DatePicker timeOfArrivalPicker;

    @FXML
    private JFXTextField signOutText;

    @FXML
    private JFXButton saveRequest;

    @FXML
    private JFXButton cancel;


    @FXML
    private VBox requestStatusContainer;

    @FXML
    private JFXComboBox<String> status;

    @FXML
    private TextArea remarks;

    @FXML
    private JFXButton saveRemark;


    @FXML
    private VBox newRequestContainer;


    @Autowired
    private Utils utils;

    private StudentHallEntity studentHallEntity;

    private int currentRow;

    private HallpassEntity hallpassEntity;


    @FXML
    public void initialize() {


        studentHallEntity = ((StudentHallEntity) data.getData());
        generateStudentPass(studentHallEntity);
        addNewRequest.visibleProperty().set(!contentNode.isAdmin());

        name.editableProperty().set(!contentNode.isAdmin());
        faculty.editableProperty().set(!contentNode.isAdmin());
        matricNo.editableProperty().set(!contentNode.isAdmin());
        level.editableProperty().set(!contentNode.isAdmin());
        course.editableProperty().set(!contentNode.isAdmin());
        hall.editableProperty().set(!contentNode.isAdmin());
        roomNo.editableProperty().set(!contentNode.isAdmin());
        address.editableProperty().set(!contentNode.isAdmin());
        phone.editableProperty().set(!contentNode.isAdmin());
        email.editableProperty().set(!contentNode.isAdmin());

        tableColumnConverter();
        loadHallPassData();

        formatDate(bookingDatePicker);
        formatDate(timeOfArrivalPicker);
        formatDate(timeOutPicker);
        addComboBoxList();
    }

    private void addComboBoxList(){
        List<String> statusList =  new ArrayList<>();
        statusList.add("Pending");
        statusList.add("Approved");
        statusList.add("Rejected");

        this.status.setItems( FXCollections.observableList(statusList));
    }

    @FXML
    void addNewRequest(ActionEvent event) {
        newRequestContainer.setVisible(true);
    }

    @FXML
    void cancelRequest(ActionEvent event) {
        newRequestContainer.setVisible(false);
    }

    @FXML
    void saveRemark() {
        this.hallpassEntity.setRemarks(remarks.getText());
        this.hallpassEntity.setStatus(status.getSelectionModel().getSelectedItem());
        HallpassEntity hallPassEntity1 = hallPassRepo.save(hallpassEntity);
        alert(hallPassEntity1, "Add Remark", "Remarks successfully Added");
        requestStatusContainer.setVisible(false);

    }

    @FXML
    void cancelRemark(ActionEvent event) {
        requestStatusContainer.setVisible(false);
    }

    @FXML
    void saveRequest() {
        newRequestContainer.setVisible(false);
        HallpassEntity hallpassEntity;
        if (saveRequest.getText().toLowerCase().equals("update")) {
            hallpassEntity = this.hallpassEntity;
        } else {
            hallpassEntity = new HallpassEntity();
        }
        hallpassEntity.setBookingDate(Date.from(bookingDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hallpassEntity.setDestination(destinationText.getText());
        hallpassEntity.setPurposeOfVisit(purposeOfVistText.getText());
        hallpassEntity.setNameOfHost(nameOfHostText.getText());
        hallpassEntity.setAddressOfHost(addressOfHostText.getText());
        hallpassEntity.setTimeOut(Date.from(timeOutPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hallpassEntity.setTimeOfArrival(Date.from(timeOfArrivalPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hallpassEntity.setHallAdminId(1);
        hallpassEntity.setStudentId(studentHallEntity.getStudentId());
        hallpassEntity.setStatus("pending");

        HallpassEntity hallPassEntity1 = hallPassRepo.save(hallpassEntity);
        alert(hallPassEntity1, "Request Pass!!!", "Request pass successful");
        saveRequest.setText("Save");
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

    private void formatDate(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        });
    }

    private void formatDatePicker(TableColumn<HallpassEntity, Date> tableColumn) {


        tableColumn.setCellFactory(tc -> new TableCell<HallpassEntity, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(date.toString());


                }
                setGraphic(null);
            }
        });

    }

    private void loadHallPassData() {
        List<HallpassEntity> hallpassEntityList = hallPassRepo.findAllByStudentId(studentHallEntity.getStudentId());
        passTable.getItems().addAll(hallpassEntityList);
    }


    private void tableColumnConverter() {


        formatDatePicker(bookingDateCol);
        bookingDateCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getBookingDate()));

        formatStringCell(destinationCol);
        destinationCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDestination()));

        formatStringCell(purposeOfVisitCol);
        purposeOfVisitCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPurposeOfVisit()));

        formatStringCell(nameOfHostCol);
        nameOfHostCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNameOfHost()));

        formatStringCell(addressOfHostCol);
        addressOfHostCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAddressOfHost()));

        formatDatePicker(timeOutCol);
        timeOutCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTimeOut()));

        formatDatePicker(timeOfArrivalCol);
        timeOfArrivalCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTimeOfArrival()));

        formatStringCell(signInCol);
        signInCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSignIn()));

        formatStringCell(hallAdminCol);
        hallAdminCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAdmindetailsByHallAdminId()
                != null ? param.getValue().getAdmindetailsByHallAdminId().getFirstName() : ""));

        formatStringCell(remarksCol);
        remarksCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRemarks()));

        statusCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStatus()));

        actionCol.setCellValueFactory(new PropertyValueFactory<>("ACTION"));

        Callback<TableColumn<HallpassEntity, String>, TableCell<HallpassEntity, String>> cellFactory
                = //
                new Callback<TableColumn<HallpassEntity, String>, TableCell<HallpassEntity, String>>() {
                    @Override
                    public TableCell call(final TableColumn<HallpassEntity, String> param) {
                        final TableCell<HallpassEntity, String> cell = new TableCell<HallpassEntity, String>() {

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Button btn;
                                    if (contentNode.isAdmin()) {
                                        btn = new Button("Add remark");
                                    } else {
                                        btn = new Button("Edit");
                                    }

                                    if (!getTableView().getItems().get(getIndex()).getStatus().toLowerCase().equals("pending")) {
                                        btn.setText("");
                                        btn.setGraphic(null);
                                    }
                                    btn.setOnAction(event -> {
                                        if (btn.getText().equals("Edit")) {
                                            HallpassEntity hallpassEntity = getTableView().getItems().get(getIndex());
                                            editRequest(hallpassEntity);
                                        } else {
                                            HallpassEntity hallpassEntity = getTableView().getItems().get(getIndex());
                                            addRemark(hallpassEntity);
                                        }

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);


    }

    private void addRemark(HallpassEntity hallpassEntity) {
        requestStatusContainer.setVisible(true);
        this.hallpassEntity = hallpassEntity;

    }

    private void editRequest(HallpassEntity hallpassEntity) {
        newRequestContainer.setVisible(true);
        bookingDatePicker.setValue(hallpassEntity.getBookingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        destinationText.setText(hallpassEntity.getDestination());
        purposeOfVistText.setText(hallpassEntity.getPurposeOfVisit());
        nameOfHostText.setText(hallpassEntity.getNameOfHost());
        addressOfHostText.setText(hallpassEntity.getAddressOfHost());
        timeOutPicker.setValue(hallpassEntity.getTimeOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        timeOfArrivalPicker.setValue(hallpassEntity.getTimeOfArrival().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        saveRequest.setText("Update");
        this.hallpassEntity = hallpassEntity;

    }

    private void formatStringCell(TableColumn<HallpassEntity, String> cell) {
        cell.setCellFactory(tc -> new TableCell<HallpassEntity, String>() {
            @Override
            protected void updateItem(String field, boolean empty) {
                super.updateItem(field, empty);
                if (empty || field == null) {
                    setText(null);
                } else {

                    setText(field);

                }
                setGraphic(null);
            }

        });

    }

    private void alert(HallpassEntity hallpassEntity, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.setTitle(header + "!!!");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();

//        passTable.getItems().add(hallpassEntity);
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


        passTable.getItems().add(data);

        // get last row
        int row = passTable.getItems().size() - 1;
        currentRow = row;
        passTable.getSelectionModel().select(row, pos.getTableColumn());

        // scroll to new row
        passTable.scrollTo(data);

    }
}
