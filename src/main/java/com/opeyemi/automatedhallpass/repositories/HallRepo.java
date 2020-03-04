package com.opeyemi.automatedhallpass.repositories;

import com.opeyemi.automatedhallpass.dbmodel.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepo extends JpaRepository<HallEntity, Integer> {
}
