-- liquibase formatted sql

-- changeset Alexey_Bobrovich:1737825142730-1
CREATE TABLE "limit"
(
    id                        VARCHAR(255)                NOT NULL,
    category                  SMALLINT                    NOT NULL,
    limit_sum                 DECIMAL(*, 2)               NOT NULL,
    limit_date_time           TIMESTAMP WITH TIME ZONE NOT NULL,
    limit_currency_short_name VARCHAR(255),
    CONSTRAINT pk_limit PRIMARY KEY (id)
);

-- changeset Alexey_Bobrovich:1737825142730-2
CREATE TABLE transaction
(
    id                  VARCHAR(255)                NOT NULL,
    account_from        INTEGER                     NOT NULL,
    account_to          INTEGER                     NOT NULL,
    currency_short_name VARCHAR(255)                NOT NULL,
    sum                 DECIMAL(*, 2)               NOT NULL,
    expense_category    SMALLINT                    NOT NULL,
    datetime            TIMESTAMP WITH TIME ZONE NOT NULL,
    limit_exceeded      BOOLEAN                     NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

