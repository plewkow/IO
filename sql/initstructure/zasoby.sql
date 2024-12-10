drop table warehouses;
CREATE TABLE warehouses (
                            id BIGINT PRIMARY KEY,
                            warehouse_name VARCHAR(255) NOT NULL,
                            location VARCHAR(255) NOT NULL
);

drop table resources;
CREATE TABLE resources (
                           id BIGINT PRIMARY KEY,
                           resource_name VARCHAR(255) NOT NULL,
                           resource_type VARCHAR(255) NOT NULL,
                           resource_quantity INT NOT NULL,
                           resource_status VARCHAR(50) NOT NULL,
                           warehouse_id BIGINT,
                           volunteer_name VARCHAR(255),
                           assigned_task VARCHAR(255),
                           CONSTRAINT fk_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouses(id)
);