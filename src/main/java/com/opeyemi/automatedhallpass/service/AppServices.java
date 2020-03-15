package com.opeyemi.automatedhallpass.service;

import com.opeyemi.automatedhallpass.dbmodel.*;
import com.opeyemi.automatedhallpass.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class AppServices {

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private StudentDetailsRepo studentDetailsRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BedSpaceRepo bedSpaceRepo;

    @Autowired
    private StudentHallRepo studentHallRepo;


    public List<HallEntity> findHalls() {
        return hallRepo.findAll();
    }
    public List<StudentdetailsEntity> findStudents() {
        return studentDetailsRepo.findAll();
    }

    public List<RoomsEntity> findRoom() {
        return roomRepo.findAll();
    }

    public List<BedspaceEntity> findBedSpace() {
        return bedSpaceRepo.findAll();
    }
    public void saveStudent(StudentdetailsEntity studentdetailsEntity){
        studentDetailsRepo.save(studentdetailsEntity);
    }

    public void saveStudentHall(StudentHallEntity studentHallEntity){
        studentHallRepo.save(studentHallEntity);
    }

    public List<StudentHallEntity> getStudentHall(){
        return studentHallRepo.findAll();
    }

}
