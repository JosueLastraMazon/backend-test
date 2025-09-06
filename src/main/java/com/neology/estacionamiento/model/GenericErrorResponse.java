package com.neology.estacionamiento.model;

public record GenericErrorResponse(String message, String errorCode, String errorDescription, String errorUri) {
}
