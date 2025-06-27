CREATE DATABASE IF NOT EXISTS ghazal;
USE ghazal;

CREATE TABLE categories (
    cat_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cat_category VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE colors (
    col_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    col_color VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE furniture_types (
    fut_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fut_furniture_type VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE manufacturers (
    man_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    man_manufacturer VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE materials (
    mat_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mat_material VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    usr_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    usr_email VARCHAR(100) NOT NULL UNIQUE,
    usr_password VARCHAR(100) NOT NULL,
    usr_name VARCHAR(100) NOT NULL,
    usr_cpf VARCHAR(15) UNIQUE
);

CREATE TABLE favorites (
    fav_usr_id INT NOT NULL PRIMARY KEY,

    CONSTRAINT fk_fav_usr
        FOREIGN KEY (fav_usr_id) REFERENCES users(usr_id)
        ON DELETE CASCADE
);

CREATE TABLE furnitures (
    fur_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fur_model VARCHAR(100) NOT NULL UNIQUE,
    fur_height DECIMAL(8,3) NOT NULL,
    fur_width DECIMAL(8,3) NOT NULL,
    fur_depth DECIMAL(8,3) NOT NULL,
    fur_weight DECIMAL(8,3) NOT NULL,
    fur_characteristics VARCHAR(255),
    fur_image LONGBLOB NOT NULL,
    fur_price DECIMAL(10,3) NOT NULL,
    fur_stock INT NOT NULL,
    fur_fut_id INT NOT NULL,
    fur_col_id INT NOT NULL,
    fur_man_id INT NOT NULL,

    CONSTRAINT fk_fur_fut
        FOREIGN KEY (fur_fut_id) REFERENCES furniture_types(fut_id),

    CONSTRAINT fk_fur_col
        FOREIGN KEY (fur_col_id) REFERENCES colors(col_id),

    CONSTRAINT fk_fur_man
        FOREIGN KEY (fur_man_id) REFERENCES manufacturers(man_id)
);

CREATE TABLE reviews (
<<<<<<< HEAD
    rev_id INT AUTO_INCREMENT PRIMARY KEY,
    rev_rating DECIMAL(4,2),
=======
<<<<<<< HEAD
    rev_id INT PRIMARY KEY,
    rev_ranting DECIMAL(4,2),
=======
    rev_id INT AUTO_INCREMENT PRIMARY KEY,
    rev_rating DECIMAL(4,2),
>>>>>>> 1ac0a9c (Finalizando)
>>>>>>> 7e7e61d (Teste)
    rev_comment VARCHAR(255),
    rev_usr_id INT NOT NULL,
    rev_fur_id INT NOT NULL,

    CONSTRAINT fk_rev_usr
        FOREIGN KEY (rev_usr_id) REFERENCES users(usr_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_rev_fur
        FOREIGN KEY (rev_fur_id) REFERENCES furnitures(fur_id)
        ON DELETE CASCADE
);

CREATE TABLE favorites_furnitures (
    far_fur_id INT NOT NULL,
    far_fav_id INT NOT NULL,
    PRIMARY KEY (far_fur_id, far_fav_id),

    CONSTRAINT fk_far_fur
        FOREIGN KEY (far_fur_id) REFERENCES furnitures(fur_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_far_fav
        FOREIGN KEY (far_fav_id) REFERENCES favorites(fav_usr_id)
        ON DELETE CASCADE
);

CREATE TABLE furnitures_categories (
    fca_cat_id INT NOT NULL,
    fca_fur_id INT NOT NULL,
    PRIMARY KEY (fca_fur_id, fca_cat_id),

    CONSTRAINT fk_fca_fur
        FOREIGN KEY (fca_fur_id) REFERENCES furnitures(fur_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_fca_cat
        FOREIGN KEY (fca_cat_id) REFERENCES categories(cat_id)
        ON DELETE CASCADE
);

CREATE TABLE furnitures_materials (
    fma_mat_id INT NOT NULL,
    fma_fur_id INT NOT NULL,
    PRIMARY KEY (fma_fur_id, fma_mat_id),

    CONSTRAINT fk_fma_fur
        FOREIGN KEY (fma_fur_id) REFERENCES furnitures(fur_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_fma_mat
        FOREIGN KEY (fma_mat_id) REFERENCES materials(mat_id)
        ON DELETE CASCADE
);