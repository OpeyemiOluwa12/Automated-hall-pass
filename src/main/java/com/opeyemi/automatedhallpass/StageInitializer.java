package com.opeyemi.automatedhallpass;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                     @Value("classpath:fxml/homePage.fxml") Resource resource,
                     ApplicationContext ac) {

        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.ac = ac;
    }


    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();

            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);

            fxmlLoader.setControllerFactory(ac::getBean);

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(600);
            stage.setWidth(800);
            stage.setTitle(this.applicationTitle);
            stage.setMaximized(true);
//            stage.getIcons().add(new Image(this.image.getURL().toExternalForm()));
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                }
            });



        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}