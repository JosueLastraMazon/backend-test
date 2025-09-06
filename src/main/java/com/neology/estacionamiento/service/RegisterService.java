package com.neology.estacionamiento.service;

import com.neology.estacionamiento.entities.Register;
import com.neology.estacionamiento.entities.Vehicle;
import com.neology.estacionamiento.model.GenericSuccessResponse;
import com.neology.estacionamiento.model.PlateNumberRequest;
import com.neology.estacionamiento.repository.RegisterRepository;
import com.neology.estacionamiento.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public GenericSuccessResponse checkInRegistration(PlateNumberRequest plateNumberRequest) {
        log.info("checkInRegistration");
        if (vehicleRepository.existsByPlateNumber(plateNumberRequest.plateNumber())) {
            Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumberRequest.plateNumber());
            Register register = new Register();
            register.setEntryTime(LocalDateTime.now());
            register.setVehicle(vehicle);
            Register saved = registerRepository.save(register);
            log.info("Entrada registrada:{}", saved.getEntryTime());
            return new GenericSuccessResponse("Registrado correctamente:"+saved.getEntryTime());
        } else {
            log.info("Registro no encontrado");
            throw new RuntimeException("No existe el registrado");
        }

    }

    public GenericSuccessResponse checkOutRegistration(PlateNumberRequest plateNumberRequest) {
        log.info("checkOutRegistration");
        if (vehicleRepository.existsByPlateNumber(plateNumberRequest.plateNumber())) {
            Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumberRequest.plateNumber());

            Register register = registerRepository.findByVehiclePlateNumber(plateNumberRequest.plateNumber()).get(0);
            register.setExitTime(LocalDateTime.now());
            register.setVehicle(vehicle);
            Register saved = registerRepository.save(register);
            log.info("Salida registrada:{}", saved.getVehicle().getPlateNumber());
            return  new GenericSuccessResponse("Registrado correctamente:"+saved.getExitTime());
        } else {
            log.info("Registro no encontrado");
            throw new RuntimeException("No existe el registrado");
        }


    }

}
