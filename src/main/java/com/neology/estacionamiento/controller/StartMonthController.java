package com.neology.estacionamiento.controller;

import com.neology.estacionamiento.service.StartMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartMonthController {
    @Autowired
    private StartMonthService startMonthService;

    @PostMapping("/startMonth")
    public ResponseEntity<?> startMonth() {
        return ResponseEntity.ok(startMonthService.startMonth());
    }
}
