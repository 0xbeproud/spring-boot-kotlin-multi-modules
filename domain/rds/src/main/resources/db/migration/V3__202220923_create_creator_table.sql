create table creator
(
    id             bigint auto_increment comment 'id',
    wallet_address varchar(256) not null comment 'wallet address',
    updated_at     datetime(6) not null,
    created_at     datetime(6) not null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

