create table reply
(
    id        bigint auto_increment
        primary key,
    content   varchar(255) null,
    review_id bigint       null,
    constraint UK4uh4vegunuxj6p9ilmeyy6gm2
        unique (review_id),
    constraint FKd5ckwt38d4ibe84wlfc3o8jw8
        foreign key (review_id) references review (id)
);

