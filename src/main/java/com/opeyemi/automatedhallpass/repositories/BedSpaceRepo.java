package com.opeyemi.automatedhallpass.repositories;

import com.opeyemi.automatedhallpass.dbmodel.BedspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedSpaceRepo extends JpaRepository<BedspaceEntity, Integer> {
}
