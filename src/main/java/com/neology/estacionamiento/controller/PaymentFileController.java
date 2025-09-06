package com.neology.estacionamiento.controller;

import com.neology.estacionamiento.service.PaymentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentFileController {
    @Autowired
    private PaymentFileService paymentFileService;

    @GetMapping("/downloadPaymentFile")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName) {
        byte[] tsvBytes = paymentFileService.generateFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".txt\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(tsvBytes);

    }
}
