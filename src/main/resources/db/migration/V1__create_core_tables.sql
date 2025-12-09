CREATE TABLE IF NOT EXISTS Customer (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    phone_number VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(50),
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Lapetite_Service (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Appointment (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES Customer(id),
    employee_id BIGINT NOT NULL REFERENCES Employee(id),
    service_id BIGINT NOT NULL REFERENCES Lapetite_Service(id),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_customer_phone ON Customer(phone_number);
CREATE INDEX IF NOT EXISTS idx_customer_name ON Customer(full_name);
CREATE INDEX IF NOT EXISTS idx_employee_name ON Employee(full_name);

CREATE INDEX IF NOT EXISTS idx_appointment_customer_id ON Appointment (customer_id);
CREATE INDEX IF NOT EXISTS idx_appointment_service_id ON Appointment (service_id);
CREATE INDEX IF NOT EXISTS idx_appointment_start_time ON Appointment (start_time);
CREATE INDEX IF NOT EXISTS idx_appointment_employee_time ON Appointment (employee_id, start_time);