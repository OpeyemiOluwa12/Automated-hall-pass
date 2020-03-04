package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

public class CreateStudent {

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField matricNo;

    @FXML
    private JFXTextField level;

    @FXML
    private JFXTextField faculty;

    @FXML
    private JFXTextField course;

    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXTextField emailAddress;

    @FXML
    private JFXTextField phoneNo;

    @FXML
    private JFXTextArea homeAddress;

    @FXML
    private JFXButton save;

    @FXML
    public void initialize(){
        gender.itemsProperty().setValue(FXCollections.observableArrayList("Male", "Female"));
    }
}
