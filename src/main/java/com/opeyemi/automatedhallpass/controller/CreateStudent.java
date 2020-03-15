package com.opeyemi.automatedhallpass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opeyemi.automatedhallpass.dbmodel.StudentdetailsEntity;
import com.opeyemi.automatedhallpass.service.AppServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
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

    @Autowired
    private AppServices appServices;

    @FXML
    public void initialize(){
        gender.itemsProperty().setValue(FXCollections.observableArrayList("Male", "Female"));
    }

    @FXML
   private void save(ActionEvent event) {

        String lastname = this.lastname.getText();
        String firstname = this.firstname.getText();
        String matricno = this.matricNo.getText();
        String level = this.level.getText();
        String faculty = this.faculty.getText();
        String course = this.course.getText();
        String gender = this.gender.getSelectionModel().getSelectedItem();
        String emailAddress = this.emailAddress.getText();
        String phoneNo = this.phoneNo.getText();
        String homeAddress =  this.homeAddress.getText();

        StudentdetailsEntity studentdetailsEntity = new StudentdetailsEntity();
        studentdetailsEntity.setLastName(lastname);
        studentdetailsEntity.setFirstName(firstname);
        studentdetailsEntity.setMatricNo(matricno);
        studentdetailsEntity.setLevel(level);
        studentdetailsEntity.setFaculty(faculty);
        studentdetailsEntity.setCourse(course);
        studentdetailsEntity.setGender(gender);
        studentdetailsEntity.setEmailAddress(emailAddress);
        studentdetailsEntity.setPhoneNo(phoneNo);
        studentdetailsEntity.setHomeAddress(homeAddress);

        appServices.saveStudent(studentdetailsEntity);
    }
}
