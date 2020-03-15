package com.opeyemi.automatedhallpass.bootstrap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class Utils {

    private String TAG = getClass().getName();
    @Autowired
    protected ApplicationContext applicationContext;

    /**
     * Loads an FXMLView From a classpath and returns the root node
     *
     * @param classpath String value of fxml classpath;
     * @return root node of the fxml view
     */
    public Node loadFXML(String classpath) {

        URL url;
        try {
            url = ResourceUtils.getURL(classpath);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            return fxmlLoader.load();
        } catch (Exception exception) {
            Logger.getLogger(TAG).log(Level.SEVERE, exception.getMessage());
            return null;
        }
    }
}
