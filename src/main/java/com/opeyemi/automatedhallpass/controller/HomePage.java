package com.opeyemi.automatedhallpass.controller;

import com.opeyemi.automatedhallpass.bootstrap.AppUtils;
import com.opeyemi.automatedhallpass.bootstrap.Utils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

@Controller
public class HomePage {

    @FXML
    private StackPane homePage;

    @Autowired
    Utils utils;

    private boolean isAdmin;

    @FXML
    public void initialize(){
        checkRoles();

        if(isAdmin) {
            checkAdminEligible();
        }else{
            checkStudentEligible();
        }
    }

    private void checkAdminEligible() {
        File adminLogin = new File(AppUtils.ADMIN_LOGIN);
        if (adminLogin.exists()) {
            String loginValue = null;
            try {
                loginValue = FileUtils.readFileToString(adminLogin, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!loginValue.contains("loggedin")) {
                loadLoginPage();
            }else {
                loadAdminPage();
            }
        } else {
            loadLoginPage();
        }

    }

    private void checkStudentEligible() {
        File studentLogin = new File(AppUtils.STUDENT_LOGIN);
        if (studentLogin.exists()) {
            String loginValue = null;
            try {
                loginValue = FileUtils.readFileToString(studentLogin, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!loginValue.contains("loggedin")) {
                loadStudentLogin();
            }else {
                loadStudentPage();
            }
        } else {
            loadStudentLogin();
        }

    }

    private void checkRoles(){
        File roles = new File(AppUtils.ROLES);
        if (roles.exists()) {
            String rolesValue = null;
            try {
                rolesValue = FileUtils.readFileToString(roles, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!rolesValue.contains("admin")) {
                isAdmin = false;
            }else {
                isAdmin = true;
            }
        }else {
            isAdmin = false;
        }
    }


    private void loadLoginPage() {
        homePage.getChildren().add(utils.loadFXML("classpath:fxml/adminlogin.fxml"));

    }

    private void loadStudentLogin() {
        homePage.getChildren().add(utils.loadFXML("classpath:fxml/studentlogin.fxml"));

    }

    private void loadAdminPage(){
        homePage.getChildren().add(utils.loadFXML("classpath:fxml/adminpage.fxml"));

    }

    private void loadStudentPage(){
        Node node = utils.loadFXML("classpath:fxml/studentpage.fxml");
        homePage.getChildren().add(node);

    }
}
