-- Tabla de vehículos (plate_number es la PK)
CREATE TABLE vehicle (
                         plate_number VARCHAR(20) PRIMARY KEY,
                         type_vehicle VARCHAR(50) NOT NULL
);

-- Tabla de registros de entradas/salidas
CREATE TABLE register (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          plate_number VARCHAR(20) NOT NULL,
                          entry_time TIMESTAMP NOT NULL,
                          exit_time TIMESTAMP,
                          CONSTRAINT fk_register_vehicle
                              FOREIGN KEY (plate_number) REFERENCES vehicle(plate_number)
);

-- Tabla de pagos (solo para residentes)
CREATE TABLE payment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         register_id BIGINT NOT NULL,
                         amount DECIMAL(10,2) NOT NULL,
                         duration_minutes BIGINT,
                         CONSTRAINT fk_payment_register
                             FOREIGN KEY (register_id) REFERENCES register(id)
);
