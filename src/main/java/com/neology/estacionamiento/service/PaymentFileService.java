package com.neology.estacionamiento.service;

import com.neology.estacionamiento.entities.Payment;
import com.neology.estacionamiento.repository.PaymentRepository;
import com.neology.estacionamiento.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PaymentFileService {
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public byte[] generateFile(){
        log.info("Generando el archivo de registros");
        registerRepository.findByVehicleTypeVehicle("Resident").forEach(register->{
            Duration duration=Duration.between(register.getEntryTime(), register.getExitTime());
            double amount=duration.toMinutes()*0.05;
            Payment payment = new Payment();
            payment.setDurationMinutes(duration.toMinutes());
            payment.setAmount(BigDecimal.valueOf(amount));
            payment.setRegister(register);
            paymentRepository.save(payment);
        });


        List<String> headers = Arrays.asList("Num.Placa", "Tiempo Estacionado(min)", "Cantidad a pagar");



        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8))) {


            writer.println(String.join("\t", headers));

            // Escribir filas
            paymentRepository.findAll().forEach(payment->{
                writer.println(
                        payment.getRegister().getVehicle().getPlateNumber()
                        .concat("\t")
                        .concat(""+payment.getDurationMinutes())
                        .concat(payment.getAmount().toString()));
            });


            writer.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando TSV", e);
        }
    }


}
