package com.opeyemi.automatedhallpass.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import org.springframework.stereotype.Service;

@Service
public class ContentNode {

    private ObjectProperty<Node> adminNode = new SimpleObjectProperty<>();
    private ObjectProperty<Node> studentNode = new SimpleObjectProperty<>();

    private boolean admin;

    public Node getAdminNode() {
        return adminNode.get();
    }

    public ObjectProperty<Node> adminNodeProperty() {
        return adminNode;
    }

    public void setAdminNode(Node adminNode) {
        this.adminNode.set(adminNode);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
