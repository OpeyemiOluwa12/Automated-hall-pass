package com.opeyemi.automatedhallpass.repositories;

import com.opeyemi.automatedhallpass.dbmodel.AdmindetailsEntity;
import com.opeyemi.automatedhallpass.dbmodel.StudentHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<AdmindetailsEntity, Integer> {

    AdmindetailsEntity findAdmindetailsEntityByEmaillAddressAndAndPassword(String emailAddress, String password);

    AdmindetailsEntity findAllById(int admin);

}
