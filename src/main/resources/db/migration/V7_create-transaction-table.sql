CREATE TABLE IF NOT EXISTS transactions
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    uid  varchar(50) not null,
    from_iban  varchar(100) not null,
    to_iban varchar(100) not null,
    amount DOUBLE precision not null
);
