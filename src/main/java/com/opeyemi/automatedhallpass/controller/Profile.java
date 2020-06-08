package com.opeyemi.automatedhallpass.controller;

import com.opeyemi.automatedhallpass.dbmodel.AdmindetailsEntity;
import com.opeyemi.automatedhallpass.dbmodel.HallpassEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentdetailsEntity;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Profile {


    @FXML
    private VBox profile;

    @FXML
    private HBox lastname;

    @FXML
    private Text lastnameText;

    @FXML
    private HBox firstname;

    @FXML
    private Text firstnameText;

    @FXML
    private HBox level;

    @FXML
    private Text levelText;

    @FXML
    private HBox matric;

    @FXML
    private Text matricnoText;

    @FXML
    private HBox faculty;

    @FXML
    private Text facultyText;

    @FXML
    private HBox course;

    @FXML
    private Text courseText;

    @FXML
    private HBox gender;

    @FXML
    private Text genderText;

    @FXML
    private HBox email;

    @FXML
    private Text emailL;

    @FXML
    private Text emailText;

    @FXML
    private HBox phone;

    @FXML
    private Text phoneText;

    @FXML
    private HBox home;

    @FXML
    private Text homeText;

    @Autowired
    private ContentNode contentNode;

    @Autowired
    Data data;


    @FXML
    public void initialize() {

        if (!contentNode.isAdmin()) {
            StudentHallEntity studentHallEntity = ((StudentHallEntity) data.getData());
            StudentdetailsEntity studentdetailsEntity = studentHallEntity.getStudentdetailsByStudentId();
            lastnameText.setText(studentdetailsEntity.getLastName());
            firstnameText.setText(studentdetailsEntity.getFirstName());
            levelText.setText(studentdetailsEntity.getLevel());
            facultyText.setText(studentdetailsEntity.getFaculty());
            courseText.setText(studentdetailsEntity.getCourse());
            emailText.setText(studentdetailsEntity.getEmailAddress());
            phoneText.setText(studentdetailsEntity.getPhoneNo());
            homeText.setText(studentdetailsEntity.getHomeAddress());
            matricnoText.setText(studentdetailsEntity.getMatricNo());
            genderText.setText(studentdetailsEntity.getGender());
        } else {

            AdmindetailsEntity admindetailsEntity = ((AdmindetailsEntity) data.getData());
            lastnameText.setText(admindetailsEntity.getLastName());
            firstnameText.setText(admindetailsEntity.getFirstName());
            emailText.setText(admindetailsEntity.getEmaillAddress());

            profile.getChildren().remove(home);
            profile.getChildren().remove(phone);
            profile.getChildren().remove(level);
            profile.getChildren().remove(gender);
            profile.getChildren().remove(course);
            profile.getChildren().remove(faculty);
            profile.getChildren().remove(matric);
            profile.getChildren().remove(phone);

        }

    }


}
