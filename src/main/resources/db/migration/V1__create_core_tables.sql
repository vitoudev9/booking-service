
CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS employee (
    id VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL UNIQUE,
    specialization VARCHAR(255),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS employee_schedule (
    id VARCHAR(50) PRIMARY KEY,
    employee_id VARCHAR(50) NOT NULL,
    day_of_week INT NOT NULL,  -- 0=Sunday, 1=Monday, ..., 6=Saturday
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_schedule_employee FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    CONSTRAINT chk_schedule_times CHECK (end_time > start_time),
    CONSTRAINT chk_day_of_week CHECK (day_of_week BETWEEN 0 AND 6)
);

CREATE TABLE IF NOT EXISTS service (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    duration_minutes INT NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    description VARCHAR(255),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS appointment (
    id VARCHAR(50) PRIMARY KEY,
    customer_id VARCHAR(50) NOT NULL,
    employee_id VARCHAR(50) NOT NULL,
    service_id VARCHAR(50) NOT NULL,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'BOOKED',
    notes VARCHAR(255),
    confirmed BOOLEAN NOT NULL DEFAULT false,
    cancelled_at TIMESTAMPTZ,
    cancellation_reason TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_appointment_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE RESTRICT,
    CONSTRAINT fk_appointment_employee FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE RESTRICT,
    CONSTRAINT fk_appointment_service FOREIGN KEY (service_id) REFERENCES service(id) ON DELETE RESTRICT,
    CONSTRAINT chk_appointment_times CHECK (end_time > start_time)
);

CREATE TABLE IF NOT EXISTS app_user (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Customer indexes
CREATE INDEX IF NOT EXISTS idx_customer_phone ON customer(phone_number);

-- Employee indexes
CREATE INDEX IF NOT EXISTS idx_employee_phone ON employee(phone_number);
CREATE INDEX IF NOT EXISTS idx_employee_active ON employee(active);

-- Service indexes
CREATE INDEX IF NOT EXISTS idx_service_name ON service(name);
CREATE INDEX IF NOT EXISTS idx_service_active ON service(active);

-- Appointment indexes
CREATE INDEX IF NOT EXISTS idx_appointment_customer ON appointment(customer_id);
CREATE INDEX IF NOT EXISTS idx_appointment_employee ON appointment(employee_id);
CREATE INDEX IF NOT EXISTS idx_appointment_service ON appointment(service_id);
CREATE INDEX IF NOT EXISTS idx_appointment_start_time ON appointment(start_time);
CREATE INDEX IF NOT EXISTS idx_appointment_status ON appointment(status);

-- App_User indexes
CREATE INDEX IF NOT EXISTS idx_appuser_username ON app_user(username);

-- Composite index for efficient availability queries
CREATE INDEX IF NOT EXISTS idx_appointment_employee_time ON appointment(employee_id, start_time);

-- Prevent double-booking: employee cannot have overlapping appointments (excludes cancelled/no-show)
CREATE UNIQUE INDEX IF NOT EXISTS idx_appointment_no_overlap
ON appointment(employee_id, start_time, end_time)
WHERE status NOT IN ('CANCELLED', 'NO_SHOW');
