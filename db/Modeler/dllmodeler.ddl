-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2025-06-15 20:26:56 BRT
--   site:      Oracle Database 12c
--   tipo:      Oracle Database 12c



--  CREATE DATABASE IF 
 --     CONTROLFILE REUSE
 --     MAXLOGFILES 1 
 --     MAXLOGMEMBERS 1 
 --     MAXLOGHISTORY 0 
 --     MAXDATAFILES 10 
 --     MAXINSTANCES 1

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
    ADD CONSTRAINT INDEX_1 UNIQUE ( col_color ) ;

CREATE TABLE favorites 
    ( 
     fav_usr_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE favorites 
    ADD CONSTRAINT favorites_PK PRIMARY KEY ( fav_usr_id ) ;

CREATE TABLE favorites_furnitures 
    ( 
     far_fur_id INTEGER  NOT NULL , 
     far_fav_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE favorites_furnitures 
    ADD CONSTRAINT favorites_furnitures_PK PRIMARY KEY ( far_fur_id, far_fav_id ) ;

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
    ADD CONSTRAINT INDEX_1 UNIQUE ( fut_furniture_type ) ;

CREATE TABLE furnitures 
    ( 
     fur_id              INTEGER  NOT NULL , 
     fur_model           VARCHAR2 (100)  NOT NULL , 
     fur_height          NUMBER (8,3)  NOT NULL , 
     fur_width           NUMBER (8,3)  NOT NULL , 
     fur_depth           NUMBER (8,3)  NOT NULL , 
     fur_weight          NUMBER (8,3)  NOT NULL , 
     fur_characteristics VARCHAR2 (255) , 
     fur_image           UNKNOWN 
--  ERROR: Datatype UNKNOWN is not allowed 
                     NOT NULL , 
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
    ADD CONSTRAINT INDEX_1 UNIQUE ( fur_model ) ;

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

CREATE TABLE low_price 
    ( 
     lop_id            INTEGER  NOT NULL , 
     lop_active        CHAR (1)  NOT NULL , 
     lop_value         NUMBER (4,3)  NOT NULL , 
     lop_start_day     DATE  NOT NULL , 
     lop_final_day     DATE  NOT NULL , 
     furnitures_fur_id INTEGER  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE low_price 
    ADD CONSTRAINT promotions_PK PRIMARY KEY ( lop_id ) ;

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
    ADD CONSTRAINT INDEX_1 UNIQUE ( man_manufacturer ) ;

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
    ADD CONSTRAINT INDEX_1 UNIQUE ( mat_material ) ;

CREATE TABLE users 
    ( 
     usr_id       INTEGER  NOT NULL , 
     usr_email    VARCHAR2 (100)  NOT NULL , 
     usr_password VARCHAR2 (100)  NOT NULL , 
     usr_birthday UNKNOWN 
--  ERROR: Datatype UNKNOWN is not allowed 
                    , 
     usr_cpf      CHAR (15)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE users 
    ADD CONSTRAINT users_PK PRIMARY KEY ( usr_id ) ;

ALTER TABLE users 
    ADD CONSTRAINT INDEX_1 UNIQUE ( usr_email ) ;

ALTER TABLE favorites_furnitures 
    ADD CONSTRAINT fk_far_fav FOREIGN KEY 
    ( 
     far_fav_id
    ) 
    REFERENCES favorites 
    ( 
     fav_usr_id
    ) 
    ON DELETE CASCADE 
    NOT DEFERRABLE 
;

ALTER TABLE favorites_furnitures 
    ADD CONSTRAINT fk_far_fur FOREIGN KEY 
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
    ON DELETE CASCADE 
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

ALTER TABLE low_price 
    ADD CONSTRAINT low_price_furnitures_FK FOREIGN KEY 
    ( 
     furnitures_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    NOT DEFERRABLE 
;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            12
-- CREATE INDEX                             0
-- ALTER TABLE                             30
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
-- CREATE DATABASE                          1
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
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   2
-- WARNINGS                                 0
