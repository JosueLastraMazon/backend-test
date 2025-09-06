package com.neology.estacionamiento.controller;

import com.neology.estacionamiento.model.PlateNumberRequest;
import com.neology.estacionamiento.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/addCheckInRegistration")
    public ResponseEntity<?> addCheckInRegistration(@RequestBody PlateNumberRequest plateNumberRequest) {
        return ResponseEntity.ok(registerService.checkInRegistration(plateNumberRequest));
    }

    @PostMapping("/addCheckOutRegistration")
    public ResponseEntity<?> addCheckOutRegistration(@RequestBody PlateNumberRequest plateNumberRequest) {
        return ResponseEntity.ok(registerService.checkOutRegistration(plateNumberRequest));
    }

}
