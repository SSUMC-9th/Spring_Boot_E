create table term
(
    id   bigint auto_increment
        primary key,
    name enum ('AGE', 'LOCATION', 'MARKETING', 'PRIVACY', 'SERVICE') null
);

