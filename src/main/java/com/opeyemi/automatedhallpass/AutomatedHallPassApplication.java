package com.opeyemi.automatedhallpass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AutomatedHallPassApplication {
    public static void main(String[] args){
        Application.launch(HallPassBase.class, args);
    }

    @Configuration
    @Profile("local")
    @ComponentScan(lazyInit = true)
    static class ScanConfig {

    }

}
