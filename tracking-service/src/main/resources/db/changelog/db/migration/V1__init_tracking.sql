-- liquibase formatted sql
-- changeset author:tracking-init

CREATE TABLE tracking_records (
                                  package_id VARCHAR(36) PRIMARY KEY,
                                  description TEXT,
                                  weight INTEGER,
                                  fragile BOOLEAN DEFAULT FALSE,
                                  current_status VARCHAR(50) CHECK (current_status IN ('CREATED', 'IN_TRANSIT', 'DELIVERED', 'CANCELLED')),
                                  last_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE checkpoint_history (
                                    id BIGSERIAL PRIMARY KEY,
                                    package_id VARCHAR(36) NOT NULL,
                                    sequence_idx INTEGER NOT NULL,
                                    checkpoint_id VARCHAR(36) NOT NULL,
                                    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                    location VARCHAR(100),
                                    notes TEXT,
                                    CONSTRAINT fk_package FOREIGN KEY (package_id) REFERENCES tracking_records(package_id) ON DELETE CASCADE,
                                    CONSTRAINT uc_checkpoint_entry UNIQUE (package_id, sequence_idx)
);

CREATE INDEX idx_tracking_status ON tracking_records(current_status);
CREATE INDEX idx_checkpoint_package ON checkpoint_history(package_id);