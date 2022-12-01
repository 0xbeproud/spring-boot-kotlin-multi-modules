create table user
(
    id             bigint auto_increment comment 'id',
    wallet_address varchar(256) not null comment 'wallet address',
    email          varchar(256) not null comment 'email',
    nickname       varchar(256) not null comment 'nickname',
    updated_at     datetime(6) not null,
    created_at     datetime(6) not null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

