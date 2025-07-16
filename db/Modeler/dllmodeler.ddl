-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2025-07-15 11:58:24 BRT
--   site:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE categories 
    ( 
     cat_id       INTEGER  NOT NULL , 
     cat_category VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE categories 
    ADD CONSTRAINT categories_PK PRIMARY KEY ( cat_id ) ;

ALTER TABLE categories 
    ADD CONSTRAINT INDEX_1 UNIQUE ( cat_category ) ;

CREATE TABLE colors 
    ( 
     col_id    INTEGER  NOT NULL , 
     col_color VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE colors 
    ADD CONSTRAINT colors_PK PRIMARY KEY ( col_id ) ;

ALTER TABLE colors 
    ADD CONSTRAINT INDEX_1v1 UNIQUE ( col_color ) ;

CREATE TABLE favorites 
    ( 
     far_fur_id INTEGER  NOT NULL , 
     fav_usr_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE favorites 
    ADD CONSTRAINT favorites_furnitures_PK PRIMARY KEY ( far_fur_id, fav_usr_id ) ;

CREATE TABLE furniture_types 
    ( 
     fut_id             INTEGER  NOT NULL , 
     fut_furniture_type VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furniture_types 
    ADD CONSTRAINT furniture_types_PK PRIMARY KEY ( fut_id ) ;

ALTER TABLE furniture_types 
    ADD CONSTRAINT INDEX_1v2 UNIQUE ( fut_furniture_type ) ;

CREATE TABLE furnitures 
    ( 
     fur_id              INTEGER  NOT NULL , 
     fur_model           VARCHAR2 (100)  NOT NULL , 
     fur_height          NUMBER (8,3)  NOT NULL , 
     fur_width           NUMBER (8,3)  NOT NULL , 
     fur_depth           NUMBER (8,3)  NOT NULL , 
     fur_weight          NUMBER (8,3)  NOT NULL , 
     fur_characteristics VARCHAR2 (255) , 
     fur_image           VARCHAR2 (255)  NOT NULL , 
     fur_price           NUMBER (10,3)  NOT NULL , 
     fur_stock           INTEGER  NOT NULL , 
     fur_fut_id          INTEGER  NOT NULL , 
     fur_col_id          INTEGER  NOT NULL , 
     fur_man_id          INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT furnitures_PK PRIMARY KEY ( fur_id ) ;

ALTER TABLE furnitures 
    ADD CONSTRAINT INDEX_1v3 UNIQUE ( fur_model ) ;

CREATE TABLE furnitures_categories 
    ( 
     fca_cat_id INTEGER  NOT NULL , 
     fca_fur_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furnitures_categories 
    ADD CONSTRAINT furnitures_categories_PK PRIMARY KEY ( fca_fur_id, fca_cat_id ) ;

CREATE TABLE furnitures_materials 
    ( 
     fma_mat_id INTEGER  NOT NULL , 
     fma_fur_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furnitures_materials 
    ADD CONSTRAINT furnitures_materials_PK PRIMARY KEY ( fma_fur_id, fma_mat_id ) ;

CREATE TABLE manufacturers 
    ( 
     man_id           INTEGER  NOT NULL , 
     man_manufacturer VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE manufacturers 
    ADD CONSTRAINT manufacturers_PK PRIMARY KEY ( man_id ) ;

ALTER TABLE manufacturers 
    ADD CONSTRAINT INDEX_1v4 UNIQUE ( man_manufacturer ) ;

CREATE TABLE materials 
    ( 
     mat_id       INTEGER  NOT NULL , 
     mat_material VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE materials 
    ADD CONSTRAINT materials_PK PRIMARY KEY ( mat_id ) ;

ALTER TABLE materials 
    ADD CONSTRAINT INDEX_1v5 UNIQUE ( mat_material ) ;

CREATE TABLE reviews 
    ( 
     users_usr_id      INTEGER  NOT NULL , 
     furnitures_fur_id INTEGER  NOT NULL , 
     rev_comment       VARCHAR2 (255) , 
     rev_rating        NUMBER (4,2)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE reviews 
    ADD CONSTRAINT reviews_PK PRIMARY KEY ( users_usr_id, furnitures_fur_id ) ;

CREATE TABLE users 
    ( 
     usr_id       INTEGER  NOT NULL , 
     usr_email    VARCHAR2 (100)  NOT NULL , 
     usr_password VARCHAR2 (100)  NOT NULL , 
     usr_name     VARCHAR2 (100) , 
     usr_cpf      CHAR (15)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE users 
    ADD CONSTRAINT users_PK PRIMARY KEY ( usr_id ) ;

ALTER TABLE users 
    ADD CONSTRAINT INDEX_1v6 UNIQUE ( usr_email ) ;

ALTER TABLE favorites 
    ADD CONSTRAINT fk_fav_fur FOREIGN KEY 
    ( 
     far_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE favorites 
    ADD CONSTRAINT fk_fav_usr FOREIGN KEY 
    ( 
     fav_usr_id
    ) 
    REFERENCES users 
    ( 
     usr_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_categories 
    ADD CONSTRAINT fk_fca_cat FOREIGN KEY 
    ( 
     fca_cat_id
    ) 
    REFERENCES categories 
    ( 
     cat_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_categories 
    ADD CONSTRAINT fk_fca_fur FOREIGN KEY 
    ( 
     fca_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_materials 
    ADD CONSTRAINT fk_fma_fur FOREIGN KEY 
    ( 
     fma_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_materials 
    ADD CONSTRAINT fk_fma_mat FOREIGN KEY 
    ( 
     fma_mat_id
    ) 
    REFERENCES materials 
    ( 
     mat_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT fk_fur_col FOREIGN KEY 
    ( 
     fur_col_id
    ) 
    REFERENCES colors 
    ( 
     col_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT fk_fur_fut FOREIGN KEY 
    ( 
     fur_fut_id
    ) 
    REFERENCES furniture_types 
    ( 
     fut_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT fk_fur_man FOREIGN KEY 
    ( 
     fur_man_id
    ) 
    REFERENCES manufacturers 
    ( 
     man_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE reviews 
    ADD CONSTRAINT fk_rev_fur FOREIGN KEY 
    ( 
     furnitures_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE reviews 
    ADD CONSTRAINT fk_rev_usr FOREIGN KEY 
    ( 
     users_usr_id
    ) 
    REFERENCES users 
    ( 
     usr_id
    ) 
    NOT DEFERRABLE 
;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                             0
-- ALTER TABLE                             29
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
