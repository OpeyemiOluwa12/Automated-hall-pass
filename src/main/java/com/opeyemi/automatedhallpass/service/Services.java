package com.opeyemi.automatedhallpass.service;

import com.opeyemi.automatedhallpass.dbmodel.HallEntity;
import com.opeyemi.automatedhallpass.repositories.HallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {

    @Autowired
    private HallRepo hallRepo;

    public List<HallEntity> findHalls() {
        return hallRepo.findAll();
    }
}
