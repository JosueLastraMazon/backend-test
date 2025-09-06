package com.neology.estacionamiento.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    private String plateNumber;
    private String typeVehicle;

    @OneToMany(mappedBy = "vehicle")
    private List<Register> registers;

}
