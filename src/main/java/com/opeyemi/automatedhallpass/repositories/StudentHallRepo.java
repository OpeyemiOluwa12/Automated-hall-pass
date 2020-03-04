package com.opeyemi.automatedhallpass.repositories;

import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentHallRepo extends JpaRepository<StudentHallEntity, Integer> {

    StudentHallEntity findAllByStudentId(int studentId);
}
