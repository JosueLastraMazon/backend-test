package com.neology.estacionamiento.controller;

import com.neology.estacionamiento.model.PlateNumberRequest;
import com.neology.estacionamiento.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addResidentVehicle")
    public ResponseEntity<?> addResidentVehicle(@RequestBody PlateNumberRequest plateNumberRequest) {
        log.info("Recibiendo peticion de alta de vehiculo de residente");
        return ResponseEntity.ok(vehicleService.addResidentVehicle(plateNumberRequest));
    }

    @PostMapping("/addOfficialVehicle")
    public ResponseEntity<?> addOfficialVehicle(@RequestBody PlateNumberRequest plateNumberRequest) {
        log.info("Recibiendo peticion de alta de vehiculo de oficial");
        return ResponseEntity.ok(vehicleService.addOfficialVehicle(plateNumberRequest));
    }
}
