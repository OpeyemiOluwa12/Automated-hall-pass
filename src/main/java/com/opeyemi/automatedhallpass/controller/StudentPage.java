package com.opeyemi.automatedhallpass.controller;


import com.jfoenix.controls.JFXButton;
import com.opeyemi.automatedhallpass.bootstrap.AppUtils;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
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
public class StudentPage {

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton viewPass;

    @FXML
    private StackPane studentHome;

    @Autowired
    private Utils utils;

    @Autowired
    private Data data;

    @Autowired
    private StudentHallRepo studentHallRepo;


    @FXML
    public void initialize() {
        File student = new File(AppUtils.STUDENT_LOGIN);
        String studentStr = null;
        try {
            studentStr = FileUtils.readFileToString(student, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String studentId = studentStr.split(",")[1];

        StudentHallEntity studentHallEntity = studentHallRepo.findAllByStudentId(Integer.parseInt(studentId));
        data.setData(studentHallEntity);

//        contentNode.setAdmin(false);
        viewProfile();
    }

    @FXML
    void viewPass(ActionEvent event) {
        studentHome.getChildren().clear();
        Node node = utils.loadFXML("classpath:fxml/pass.fxml");
        studentHome.getChildren().add(node);

    }

    @FXML
    void viewProfile() {
        studentHome.getChildren().clear();
        Node node = utils.loadFXML("classpath:fxml/profile.fxml");
        studentHome.getChildren().add(node);
    }
}
