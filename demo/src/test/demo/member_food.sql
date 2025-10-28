create table member_food
(
    id        bigint auto_increment
        primary key,
    food_id   bigint null,
    member_id bigint null,
    constraint FK5i0iwac2dcdifkxtb64cd17o8
        foreign key (member_id) references member (id),
    constraint FKj1eo2o1lys37eeqycfahfr0h9
        foreign key (food_id) references food (id)
);


ALTER TABLE member
    DROP COLUMN gender;

ALTER TABLE member
    ADD gender VARCHAR(255) NOT NULL;

ALTER TABLE food
    DROP COLUMN name;

ALTER TABLE food
    ADD name VARCHAR(255) NULL;

ALTER TABLE term
    DROP COLUMN name;

ALTER TABLE term
    ADD name VARCHAR(255) NULL;