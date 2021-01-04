CREATE table hall
(
    id         SERIAL PRIMARY KEY,
    occupation boolean
);

INSERT into hall (occupation)
values (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE),
       (FALSE);


CREATE table users
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    lastname TEXT,
    phone    varchar(20) UNIQUE
);
CREATE table tickets
(
    id          SERIAL PRIMARY KEY,
    datePayment TIMESTAMP,
    dateSession TIMESTAMP,
    placeNumber INTEGER,
    number      INTEGER UNIQUE
);
CREATE table ticketAccounts
(
    id           SERIAL PRIMARY KEY,
    userPhone    varchar(20) REFERENCES users (phone),
    ticketNumber INTEGER UNIQUE REFERENCES tickets (number)
);

AlTER table ticketaccounts
DROP
column ticketNumber;

AlTER table ticketaccounts
    add column ticketNumber INTEGER UNIQUE REFERENCES tickets (number) ON DELETE SET NULL;

E;