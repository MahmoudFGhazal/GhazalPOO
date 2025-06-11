-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2025-06-11 13:09:55 BRT
--   site:      Oracle Database 12c
--   tipo:      Oracle Database 12c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE categories 
    ( 
     cat_id       NUMBER (10)  NOT NULL , 
     cat_category VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE categories 
    ADD CONSTRAINT categories_pk PRIMARY KEY ( cat_id ) ;

CREATE TABLE colors 
    ( 
     col_id    NUMBER (10)  NOT NULL , 
     col_color VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE colors 
    ADD CONSTRAINT colors_pk PRIMARY KEY ( col_id ) ;

CREATE TABLE furniture_types 
    ( 
     fut_id             NUMBER (10)  NOT NULL , 
     fut_furniture_type VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furniture_types 
    ADD CONSTRAINT furniture_types_pk PRIMARY KEY ( fut_id ) ;

CREATE TABLE furnitures 
    ( 
     fur_id               NUMBER (10)  NOT NULL , 
     fur_model            VARCHAR2 (100)  NOT NULL , 
     fur_height           NUMBER (5,3)  NOT NULL , 
     fur_width            NUMBER (5,3)  NOT NULL , 
     fur_depth            NUMBER (5,3)  NOT NULL , 
     fur_weight           NUMBER (4,3) , 
     fur_characteristics  VARCHAR2 (255) , 
     fur_image            BLOB  NOT NULL , 
     fur_price            NUMBER (7,3)  NOT NULL , 
     fur_promotion_active CHAR (1)  NOT NULL , 
     fur_promotion_value  NUMBER (2,3)  NOT NULL , 
     fur_stock            NUMBER (5)  NOT NULL , 
     fur_fut_id           NUMBER (10)  NOT NULL , 
     fur_col_id           NUMBER (10)  NOT NULL , 
     fur_man_id           NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT furnitures_pk PRIMARY KEY ( fur_id ) ;

CREATE TABLE furnitures_categories 
    ( 
     fca_cat_id NUMBER (10)  NOT NULL , 
     fca_fur_id NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

CREATE TABLE furnitures_materials 
    ( 
     fma_mat_id NUMBER (10)  NOT NULL , 
     fma_fur_id NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

CREATE TABLE manufacturers 
    ( 
     man_id           NUMBER (10)  NOT NULL , 
     man_manufacturer VARCHAR2 (100)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE manufacturers 
    ADD CONSTRAINT manufacturers_pk PRIMARY KEY ( man_id ) ;

CREATE TABLE materials 
    ( 
     mat_id       NUMBER (10)  NOT NULL , 
     mat_material VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE materials 
    ADD CONSTRAINT materials_pk PRIMARY KEY ( mat_id ) ;

ALTER TABLE furnitures_categories 
    ADD CONSTRAINT fca_cat_FK FOREIGN KEY 
    ( 
     fca_cat_id
    ) 
    REFERENCES categories 
    ( 
     cat_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_categories 
    ADD CONSTRAINT fca_fur_FK FOREIGN KEY 
    ( 
     fca_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_materials 
    ADD CONSTRAINT fma_fur_FK FOREIGN KEY 
    ( 
     fma_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures_materials 
    ADD CONSTRAINT fma_mat_FK FOREIGN KEY 
    ( 
     fma_mat_id
    ) 
    REFERENCES materials 
    ( 
     mat_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE furnitures 
    ADD CONSTRAINT fur_col_FK FOREIGN KEY 
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
    ADD CONSTRAINT fur_fty_FK FOREIGN KEY 
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
    ADD CONSTRAINT fur_man_FK FOREIGN KEY 
    ( 
     fur_man_id
    ) 
    REFERENCES manufacturers 
    ( 
     man_id
    ) 
    NOT DEFERRABLE 
;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             13
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
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
