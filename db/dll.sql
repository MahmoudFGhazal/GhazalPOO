CREATE DATABASE IF NOT EXISTS ghazal;
USE ghazal;

CREATE TABLE categories (
    cat_id INT NOT NULL AUTO_INCREMENT,
    cat_category VARCHAR(50) NOT NULL,
    PRIMARY KEY (cat_id)
);

CREATE TABLE colors (
    col_id INT NOT NULL AUTO_INCREMENT,
    col_color VARCHAR(100) NOT NULL,
    PRIMARY KEY (col_id)
);

CREATE TABLE furniture_types (
    fut_id INT NOT NULL AUTO_INCREMENT,
    fut_furniture_type VARCHAR(100) NOT NULL,
    PRIMARY KEY (fut_id)
);

CREATE TABLE manufacturers (
    man_id INT NOT NULL AUTO_INCREMENT,
    man_manufacturer VARCHAR(100) NOT NULL,
    PRIMARY KEY (man_id)
);

CREATE TABLE materials (
    mat_id INT NOT NULL AUTO_INCREMENT,
    mat_material VARCHAR(50) NOT NULL,
    PRIMARY KEY (mat_id)
);

CREATE TABLE furnitures (
    fur_id INT NOT NULL AUTO_INCREMENT,
    fur_model VARCHAR(100) NOT NULL,
    fur_height DECIMAL(8,3) NOT NULL,
    fur_width DECIMAL(8,3) NOT NULL,
    fur_depth DECIMAL(8,3) NOT NULL,
    fur_weight DECIMAL(8,3),
    fur_characteristics VARCHAR(255),
    fur_image LONGBLOB NOT NULL,
    fur_price DECIMAL(10,3) NOT NULL,
    fur_promotion_active ENUM("Y", "N") NOT NULL,
    fur_promotion_value DECIMAL(8,3) NOT NULL,
    fur_stock INT NOT NULL,
    fur_fut_id INT NOT NULL,
    fur_col_id INT NOT NULL,
    fur_man_id INT NOT NULL,
    PRIMARY KEY (fur_id),
    FOREIGN KEY (fur_fut_id) REFERENCES furniture_types(fut_id),
    FOREIGN KEY (fur_col_id) REFERENCES colors(col_id),
    FOREIGN KEY (fur_man_id) REFERENCES manufacturers(man_id)
);

CREATE TABLE furnitures_categories (
    fca_cat_id INT NOT NULL,
    fca_fur_id INT NOT NULL,
    FOREIGN KEY (fca_cat_id) REFERENCES categories(cat_id),
    FOREIGN KEY (fca_fur_id) REFERENCES furnitures(fur_id)
);

CREATE TABLE furnitures_materials (
    fma_mat_id INT NOT NULL,
    fma_fur_id INT NOT NULL,
    FOREIGN KEY (fma_mat_id) REFERENCES materials(mat_id),
    FOREIGN KEY (fma_fur_id) REFERENCES furnitures(fur_id)
);
