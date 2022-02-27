Create TABLE Lord
(
    id bigserial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT
);

Create TABLE Planet
(
    id bigserial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lord_id bigint references Lord(id)
);