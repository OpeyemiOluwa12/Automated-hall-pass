package com.opeyemi.automatedhallpass.controller;

import org.springframework.stereotype.Service;

@Service
public class Data {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
