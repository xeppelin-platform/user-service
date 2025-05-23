CREATE TABLE users (
    id UUID NOT NULL,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    email CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    role CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    status CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    created_by VARCHAR(50),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(50),
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT chk_users_email CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_status ON users(status);

CREATE TABLE addresses (
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    address_line1 CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    address_line2 CHARACTER VARYING COLLATE pg_catalog."default",
    city CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    state CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    postal_code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    country CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    phone_number VARCHAR(20),
    created_by VARCHAR(50),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(50),
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT addresses_pkey PRIMARY KEY (id),
    CONSTRAINT fk_addresses_user FOREIGN KEY (user_id)
        REFERENCES users(id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT chk_addresses_postal_code CHECK (postal_code ~ '^[A-Za-z0-9\-\s]{3,20}$'),
    CONSTRAINT chk_addresses_phone_number CHECK (phone_number IS NULL OR phone_number ~ '^\+?[0-9\-\s]{10,19}$')
);

CREATE INDEX idx_addresses_user_id ON addresses(user_id);
CREATE INDEX idx_addresses_postal_code ON addresses(postal_code);
CREATE INDEX idx_addresses_country ON addresses(country);
CREATE INDEX idx_addresses_state ON addresses(state);
