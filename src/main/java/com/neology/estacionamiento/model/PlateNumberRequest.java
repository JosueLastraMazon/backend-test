package com.neology.estacionamiento.model;

import jakarta.validation.constraints.NotBlank;

public record PlateNumberRequest(@NotBlank String plateNumber) {
}
