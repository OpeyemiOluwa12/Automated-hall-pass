package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class AdminPage {

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton generatePass;

    @FXML
    private JFXButton assignStudent;

    @FXML
    private JFXButton viewStudents;

    @FXML
    private JFXButton addStudent;

    @FXML
    private StackPane adminHome;

    @FXML
    public void initialize() {

    }


    @FXML
    void AssignStudent(ActionEvent event) throws IOException {
        adminHome.getChildren().add(new FXMLLoader(getClass().getResource("/fxml/assignroom.fxml")).load());

    }

    @FXML
    void addStudent(ActionEvent event) throws IOException {
        adminHome.getChildren().add(new FXMLLoader(getClass().getResource("/fxml/createstudent.fxml")).load());

    }

    @FXML
    void generatePass(ActionEvent event) {

    }

    @FXML
    void viewProfile(ActionEvent event) {

    }

    @FXML
    void viewStudents(ActionEvent event) {

    }

}
