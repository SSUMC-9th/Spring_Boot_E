create table food
(
    id         bigint auto_increment
        primary key,
    created_at datetime(6)                                                                                 not null,
    updated_at datetime(6)                                                                                 not null,
    name       enum ('고기', '구이', '도시락', '디저트', '분식', '아시안푸드', '야식', '양식', '일식', '중식', '치킨', '패스트푸드', '한식') null
);

