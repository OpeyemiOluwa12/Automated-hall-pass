package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    Utils utils;

    @Autowired
    ContentNode contentNode;

    @FXML
    public void initialize() {
        contentListener();
        contentNode.setAdmin(true);
    }


    @FXML
    void AssignStudent(ActionEvent event) throws IOException {
        adminHome.getChildren().clear();
        adminHome.getChildren().add(utils.loadFXML("classpath:fxml/assignroom.fxml"));

    }

    @FXML
    void addStudent(ActionEvent event) throws IOException {
        adminHome.getChildren().clear();
        adminHome.getChildren().add(utils.loadFXML("classpath:fxml/createstudent.fxml"));

    }

    @FXML
    void generatePass(ActionEvent event) {

    }

    @FXML
    void viewProfile(ActionEvent event) {

    }

    @FXML
    void viewStudents(ActionEvent event) {
        adminHome.getChildren().clear();
        adminHome.getChildren().add(utils.loadFXML("classpath:fxml/viewstudent.fxml"));

    }

    private void contentListener() {
        contentNode.adminNodeProperty().addListener((observable, oldValue, newValue) -> {
            adminHome.getChildren().clear();
            adminHome.getChildren().add(newValue);
        });
    }

}
