package com.neology.estacionamiento.repository;

import com.neology.estacionamiento.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByPlateNumber(String plateNumber);

    Vehicle findByPlateNumber(String plateNumber);
}
