CREATE TABLE IF NOT EXISTS breads (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(40) NOT NULL,
                                     unit_grams INT NOT NULL,
                                     price DECIMAL(6,2) NOT NULL
);