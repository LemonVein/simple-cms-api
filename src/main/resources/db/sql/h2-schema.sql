-- TODO schema
-- example
create table member
(
    id                 bigint primary key      not null auto_increment,
    name               varchar(50)             not null,
    password           varchar(255)            not null,
    role               varchar(20)             not null,
    created_date       timestamp default now() not null,
    last_modified_date timestamp
);

CREATE TABLE IF NOT EXISTS contents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    view_count BIGINT NOT NULL DEFAULT 0,
    created_date TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(50)
);