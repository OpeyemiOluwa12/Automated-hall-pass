package com.opeyemi.automatedhallpass;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class HallPassBase extends Application {

        private ConfigurableApplicationContext applicationContext;

        @Override
        public void init() throws Exception {
//        applicationContext = new SpringApplicationBuilder(PrimeApplication.class).run();


            ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
                ac.registerBean(Application.class, () -> HallPassBase.this);
                ac.registerBean(Parameters.class, this::getParameters);
                ac.registerBean(HostServices.class, this::getHostServices);
            };

            this.applicationContext = new SpringApplicationBuilder()
                    .sources(AutomatedHallPassApplication.class)
                    .initializers(initializer)
                    .run(getParameters().getRaw().toArray(new String[0]));


        }

        @Override
        public void stop() throws Exception {
            applicationContext.close();
            Platform.exit();
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            applicationContext.publishEvent(new StageReadyEvent(primaryStage));

        }
}
