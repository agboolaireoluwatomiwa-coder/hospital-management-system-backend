-- Hospital Management System: PostgreSQL schema
-- Runs automatically on startup (safe to re-run: every statement is idempotent).

CREATE TABLE IF NOT EXISTS users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,              -- BCrypt hash
    full_name   VARCHAR(100),
    role        VARCHAR(20)  NOT NULL DEFAULT 'STAFF',
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS patients (
    id             BIGSERIAL PRIMARY KEY,
    first_name     VARCHAR(60)  NOT NULL,
    last_name      VARCHAR(60)  NOT NULL,
    gender         VARCHAR(10),
    date_of_birth  DATE,
    phone          VARCHAR(30),
    email          VARCHAR(120) UNIQUE,
    address        TEXT,
    blood_group    VARCHAR(5),
    photo_url      TEXT,                            -- Cloudinary image URL
    created_at     TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS doctors (
    id              BIGSERIAL PRIMARY KEY,
    first_name      VARCHAR(60)  NOT NULL,
    last_name       VARCHAR(60)  NOT NULL,
    specialization  VARCHAR(80)  NOT NULL,
    department      VARCHAR(80),
    phone           VARCHAR(30),
    email           VARCHAR(120) UNIQUE,
    license_number  VARCHAR(50)  NOT NULL UNIQUE,
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS appointments (
    id                BIGSERIAL PRIMARY KEY,
    patient_id        BIGINT    NOT NULL REFERENCES patients(id),
    doctor_id         BIGINT    NOT NULL REFERENCES doctors(id),
    appointment_time  TIMESTAMP NOT NULL,
    reason            TEXT,
    status            VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED'
                      CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED')),
    created_at        TIMESTAMP NOT NULL DEFAULT NOW()
);
-- A doctor cannot hold two active appointments at the same time
CREATE UNIQUE INDEX IF NOT EXISTS uq_doctor_slot
    ON appointments (doctor_id, appointment_time) WHERE status <> 'CANCELLED';

CREATE TABLE IF NOT EXISTS medical_records (
    id          BIGSERIAL PRIMARY KEY,
    patient_id  BIGINT NOT NULL REFERENCES patients(id),
    doctor_id   BIGINT NOT NULL REFERENCES doctors(id),
    visit_date  DATE   NOT NULL DEFAULT CURRENT_DATE,
    diagnosis   TEXT   NOT NULL,
    treatment   TEXT,
    notes       TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS prescriptions (
    id             BIGSERIAL PRIMARY KEY,
    patient_id     BIGINT NOT NULL REFERENCES patients(id),
    doctor_id      BIGINT NOT NULL REFERENCES doctors(id),
    medication     VARCHAR(120) NOT NULL,
    dosage         VARCHAR(60)  NOT NULL,
    frequency      VARCHAR(60),
    duration_days  INT CHECK (duration_days > 0),
    instructions   TEXT,
    created_at     TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS lab_tests (
    id              BIGSERIAL PRIMARY KEY,
    patient_id      BIGINT NOT NULL REFERENCES patients(id),
    doctor_id       BIGINT NOT NULL REFERENCES doctors(id),
    test_name       VARCHAR(120) NOT NULL,
    requested_date  DATE NOT NULL DEFAULT CURRENT_DATE,
    status          VARCHAR(20) NOT NULL DEFAULT 'REQUESTED'
                    CHECK (status IN ('REQUESTED', 'IN_PROGRESS', 'COMPLETED')),
    result          TEXT,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS invoices (
    id          BIGSERIAL PRIMARY KEY,
    patient_id  BIGINT NOT NULL REFERENCES patients(id),
    description VARCHAR(255) NOT NULL,
    amount      NUMERIC(12,2) NOT NULL CHECK (amount >= 0),
    issue_date  DATE NOT NULL DEFAULT CURRENT_DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'UNPAID'
                CHECK (status IN ('UNPAID', 'PARTIAL', 'PAID')),
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notifications (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(150) NOT NULL,
    message         TEXT NOT NULL,
    recipient_role  VARCHAR(20) NOT NULL DEFAULT 'ALL',
    status          VARCHAR(10) NOT NULL DEFAULT 'UNREAD' CHECK (status IN ('UNREAD', 'READ')),
    created_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_appointments_patient ON appointments(patient_id);
CREATE INDEX IF NOT EXISTS idx_appointments_doctor  ON appointments(doctor_id);
CREATE INDEX IF NOT EXISTS idx_records_patient      ON medical_records(patient_id);
CREATE INDEX IF NOT EXISTS idx_prescriptions_patient ON prescriptions(patient_id);
CREATE INDEX IF NOT EXISTS idx_lab_tests_patient    ON lab_tests(patient_id);
CREATE INDEX IF NOT EXISTS idx_invoices_patient     ON invoices(patient_id);
