CREATE TABLE IF NOT EXISTS App_User (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_appuser_username ON App_User(username);

INSERT INTO App_User (username, password, role)
VALUES
('vitou.u', '{noop}2025', 'ADMIN'),
('bobby.s', '{noop}2025', 'USER');