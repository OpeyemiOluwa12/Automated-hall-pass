package com.opeyemi.automatedhallpass.repositories;

import com.opeyemi.automatedhallpass.dbmodel.HallpassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallPassRepo extends JpaRepository<HallpassEntity, Integer> {

    List<HallpassEntity> findAllByStudentId(int studentId);
}
