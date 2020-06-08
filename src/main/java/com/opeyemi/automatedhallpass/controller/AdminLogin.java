package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.opeyemi.automatedhallpass.bootstrap.AppUtils;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import com.opeyemi.automatedhallpass.dbmodel.AdmindetailsEntity;
import com.opeyemi.automatedhallpass.service.AppServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

@Controller
public class AdminLogin {

    @FXML
    StackPane loginPane;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton login;

    @Autowired
    private AppServices appServices;

    @Autowired
    private Utils utils;

    @FXML
    public void initialize() {

    }


    @FXML
    void login(ActionEvent event) {
        AdmindetailsEntity admindetailsEntity = appServices.findAdmin(username.getText(), password.getText());

        if (admindetailsEntity != null) {

            File adminLogin = new File(AppUtils.ADMIN_LOGIN);
            try {
                FileUtils.writeStringToFile(adminLogin, "loggedin", "UTF-8");
                FileUtils.writeStringToFile(adminLogin, "," + admindetailsEntity.getId(), "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(loginPane.getScene().getWindow());
            alert.setTitle("Login!!!");
            alert.setHeaderText("Login");
            alert.setContentText("Login Successful");
            alert.show();
            StackPane homePage = ((StackPane) loginPane.getParent());
            homePage.getChildren().clear();
            Node adminPage = utils.loadFXML("classpath:fxml/adminpage.fxml");
            homePage.getChildren().add(adminPage);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(loginPane.getScene().getWindow());
            alert.setHeaderText("Login");
            alert.setTitle("Login!!!");
            alert.setContentText("Invalid Username or Password");
            alert.show();
        }

    }
}
