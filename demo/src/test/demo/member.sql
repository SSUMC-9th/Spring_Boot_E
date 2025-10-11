create table member
(
    id             bigint auto_increment
        primary key,
    birth          date                            not null,
    created_at     datetime(6)                     not null,
    deleted_at     datetime(6)                     not null,
    detail_address varchar(255)                    not null,
    email          varchar(255)                    not null,
    gender         enum ('FEMALE', 'MALE', 'NONE') not null,
    name           varchar(3)                      not null,
    phone_number   int                             not null,
    point          int                             not null,
    social_type    tinyint                         not null,
    social_uid     varchar(255)                    not null,
    updated_at     datetime(6)                     not null,
    check (`social_type` between 0 and 3)
);

