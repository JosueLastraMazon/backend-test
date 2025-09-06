package com.neology.estacionamiento.service;

import com.neology.estacionamiento.model.GenericSuccessResponse;
import com.neology.estacionamiento.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartMonthService {
    @Autowired
    private RegisterRepository registerRepository;

    public GenericSuccessResponse startMonth() {
        registerRepository.deleteAllOfficialVehicles();
        registerRepository.resetResidentTimes();
        return new GenericSuccessResponse("Comienza inicio de mes");
    }
}
