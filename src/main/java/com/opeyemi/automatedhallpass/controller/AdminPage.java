package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.opeyemi.automatedhallpass.bootstrap.AppUtils;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import com.opeyemi.automatedhallpass.dbmodel.AdmindetailsEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.repositories.AdminRepo;
import com.opeyemi.automatedhallpass.repositories.StudentHallRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;


@Controller
public class AdminPage {

    @FXML
    private JFXButton profile;

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

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private Data data;

    @FXML
    public void initialize() {

        contentListener();
        contentNode.setAdmin(true);


        File admin = new File(AppUtils.ADMIN_LOGIN);
        String adminStr = null;
        try {
            adminStr = FileUtils.readFileToString(admin, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String adminId = adminStr.split(",")[1];

        AdmindetailsEntity admindetailsEntity =  adminRepo.findAllById(Integer.parseInt(adminId));
        data.setData(admindetailsEntity);

        viewProfile();
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
    void viewProfile() {
        adminHome.getChildren().clear();
        Node node = utils.loadFXML("classpath:fxml/profile.fxml");
        adminHome.getChildren().add(node);
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
