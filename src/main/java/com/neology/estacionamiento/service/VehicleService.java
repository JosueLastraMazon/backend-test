package com.neology.estacionamiento.service;

import com.neology.estacionamiento.entities.Vehicle;
import com.neology.estacionamiento.model.GenericSuccessResponse;
import com.neology.estacionamiento.model.PlateNumberRequest;
import com.neology.estacionamiento.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public GenericSuccessResponse addResidentVehicle(PlateNumberRequest plateNumberRequest) {
        log.info("Registrando vehiculo de alta de resident");
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(plateNumberRequest.plateNumber());
        vehicle.setTypeVehicle("Resident");
        Vehicle saved=vehicleRepository.save(vehicle);
        log.info("Vehiculo registrado correctamente:{}",saved.getPlateNumber());
        return new GenericSuccessResponse("Vehiculo registrado correctamente:"+saved.getPlateNumber());
    }
    public  GenericSuccessResponse addOfficialVehicle(PlateNumberRequest plateNumberRequest) {
        log.info("Registrando vehiculo de alta de official");
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(plateNumberRequest.plateNumber());
        vehicle.setTypeVehicle("Official");
        Vehicle saved=vehicleRepository.save(vehicle);
        log.info("Vehiculo registrado correctamente:{}",saved.getPlateNumber());
        return new GenericSuccessResponse("Vehiculo registrado correctamente:"+saved.getPlateNumber());
    }
}
