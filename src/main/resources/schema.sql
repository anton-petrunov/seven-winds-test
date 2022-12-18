DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    email      VARCHAR(128)                                         NOT NULL,
    surname    VARCHAR(128)                                         NOT NULL,
    name       VARCHAR(128)                                         NOT NULL,
    patronymic VARCHAR(128),
    phone      VARCHAR(32),
    CONSTRAINT email_idx UNIQUE (email)
)