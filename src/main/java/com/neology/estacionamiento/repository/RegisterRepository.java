package com.neology.estacionamiento.repository;

import com.neology.estacionamiento.entities.Register;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByVehiclePlateNumber(String vehiclePlateNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM Register r WHERE r.vehicle.typeVehicle = 'Official'")
    void deleteAllOfficialVehicles();

    @Modifying
    @Transactional
    @Query("UPDATE Register r SET r.entryTime = CURRENT_TIMESTAMP , r.exitTime = NULL WHERE r.vehicle.typeVehicle = 'Resident'")
    void resetResidentTimes();

    List<Register> findByVehicleTypeVehicle(String vehicleTypeVehicle);



    
}
