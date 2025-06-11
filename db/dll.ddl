-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2025-06-11 00:07:34 BRT
--   site:      Oracle Database 12c
--   tipo:      Oracle Database 12c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE addresses 
    ( 
     add_id         NUMBER (10)  NOT NULL , 
     add_number     VARCHAR2 (10)  NOT NULL , 
     add_complement VARCHAR2 (255) , 
     add_cit_id     NUMBER (10)  NOT NULL , 
     add_rty_id     NUMBER (10)  NOT NULL , 
     add_str_id     NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE addresses 
    ADD CONSTRAINT address_pk PRIMARY KEY ( add_id ) ;

CREATE TABLE administrators 
    ( 
     adm_id       NUMBER (10)  NOT NULL , 
     adm_email    VARCHAR2 (255)  NOT NULL , 
     adm_password VARCHAR2 (255)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE administrators 
    ADD CONSTRAINT administrators_PK PRIMARY KEY ( adm_id ) ;

CREATE TABLE cards 
    ( 
     car_id     NUMBER (10)  NOT NULL , 
     car_number VARCHAR2 (17)  NOT NULL , 
     car_ccv    VARCHAR2 (4)  NOT NULL , 
     car_holder VARCHAR2 (255)  NOT NULL , 
     car_valid  VARCHAR2 (6)  NOT NULL , 
     car_pty_id NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE cards 
    ADD CONSTRAINT cards_pk PRIMARY KEY ( car_id ) ;

CREATE TABLE categories 
    ( 
     cat_id       NUMBER (10)  NOT NULL , 
     cat_category VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE categories 
    ADD CONSTRAINT categories_pk PRIMARY KEY ( cat_id ) ;

CREATE TABLE cities 
    ( 
     cit_id     NUMBER (10)  NOT NULL , 
     cit_city   VARCHAR2 (100)  NOT NULL , 
     cit_sta_id NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE cities 
    ADD CONSTRAINT cities_pk PRIMARY KEY ( cit_id ) ;

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

CREATE TABLE order_items 
    ( 
     oit_fur_id     NUMBER (10)  NOT NULL , 
     oit_sal_id     NUMBER (10)  NOT NULL , 
     oit_quantity   NUMBER (5)  NOT NULL , 
     oit_unit_price NUMBER (10,3)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE order_items 
    ADD CONSTRAINT order_items_PK PRIMARY KEY ( oit_fur_id, oit_sal_id ) ;

CREATE TABLE payment_types 
    ( 
     pty_id           NUMBER (10)  NOT NULL , 
     pty_payment_type VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE payment_types 
    ADD CONSTRAINT payment_types_PK PRIMARY KEY ( pty_id ) ;

CREATE TABLE residence_types 
    ( 
     rty_id             NUMBER (10)  NOT NULL , 
     rty_residence_type VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE residence_types 
    ADD CONSTRAINT residence_types_PK PRIMARY KEY ( rty_id ) ;

CREATE TABLE sales 
    ( 
     sal_id            NUMBER (10)  NOT NULL , 
     sal_value         NUMBER (10,3)  NOT NULL , 
     sal_date          DATE  NOT NULL , 
     sal_delivery_date DATE  NOT NULL , 
     sal_delivered     VARCHAR2 (1)  NOT NULL , 
     sal_usr_id        NUMBER (10)  NOT NULL , 
     sal_pty_id        NUMBER (10)  NOT NULL , 
     sal_add_id        NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE sales 
    ADD CONSTRAINT sales_pk PRIMARY KEY ( sal_id ) ;

CREATE TABLE states 
    ( 
     sta_id    NUMBER (10)  NOT NULL , 
     sta_state VARCHAR2 (50)  NOT NULL , 
     sta_uf    VARCHAR2 (3)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE states 
    ADD CONSTRAINT states_pk PRIMARY KEY ( sta_id ) ;

CREATE TABLE street_types 
    ( 
     sty_id          NUMBER (10)  NOT NULL , 
     sty_street_type VARCHAR2 (50)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE street_types 
    ADD CONSTRAINT street_types_PK PRIMARY KEY ( sty_id ) ;

CREATE TABLE streets 
    ( 
     str_id           NUMBER (10)  NOT NULL , 
     str_street       VARCHAR2 (100)  NOT NULL , 
     str_neighborhood VARCHAR2 (100)  NOT NULL , 
     str_zip          VARCHAR2 (11)  NOT NULL , 
     str_sty_id       NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE streets 
    ADD CONSTRAINT streets_PK PRIMARY KEY ( str_id ) ;

CREATE TABLE users 
    ( 
     usr_id         NUMBER (10)  NOT NULL , 
     usr_email      VARCHAR2 (255)  NOT NULL , 
     usr_password   VARCHAR2 (255)  NOT NULL , 
     usr_name       VARCHAR2 (255)  NOT NULL , 
     usr_cellphone  VARCHAR2 (18)  NOT NULL , 
     usr_birth_date DATE  NOT NULL , 
     usr_cpf        VARCHAR2 (15)  NOT NULL , 
     usr_rg         VARCHAR2 (12) , 
     usr_add_id     NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE users 
    ADD CONSTRAINT users_pk PRIMARY KEY ( usr_id ) ;

CREATE TABLE users_cards 
    ( 
     uca_usr_id NUMBER (10)  NOT NULL , 
     uca_car_id NUMBER (10)  NOT NULL 
    ) 
    LOGGING 
;

ALTER TABLE addresses 
    ADD CONSTRAINT add_cit_FK FOREIGN KEY 
    ( 
     add_cit_id
    ) 
    REFERENCES cities 
    ( 
     cit_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE addresses 
    ADD CONSTRAINT add_rty_FK FOREIGN KEY 
    ( 
     add_rty_id
    ) 
    REFERENCES residence_types 
    ( 
     rty_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE addresses 
    ADD CONSTRAINT add_str_FK FOREIGN KEY 
    ( 
     add_str_id
    ) 
    REFERENCES streets 
    ( 
     str_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE cards 
    ADD CONSTRAINT car_pty_FK FOREIGN KEY 
    ( 
     car_pty_id
    ) 
    REFERENCES payment_types 
    ( 
     pty_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE cities 
    ADD CONSTRAINT cit_sta_FK FOREIGN KEY 
    ( 
     cit_sta_id
    ) 
    REFERENCES states 
    ( 
     sta_id
    ) 
    NOT DEFERRABLE 
;

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

ALTER TABLE order_items 
    ADD CONSTRAINT oit_fur_FK FOREIGN KEY 
    ( 
     oit_fur_id
    ) 
    REFERENCES furnitures 
    ( 
     fur_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE order_items 
    ADD CONSTRAINT oit_sal_FK FOREIGN KEY 
    ( 
     oit_sal_id
    ) 
    REFERENCES sales 
    ( 
     sal_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE sales 
    ADD CONSTRAINT sal_add_FK FOREIGN KEY 
    ( 
     sal_add_id
    ) 
    REFERENCES addresses 
    ( 
     add_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE sales 
    ADD CONSTRAINT sal_pty_FK FOREIGN KEY 
    ( 
     sal_pty_id
    ) 
    REFERENCES payment_types 
    ( 
     pty_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE sales 
    ADD CONSTRAINT sal_usr_FK FOREIGN KEY 
    ( 
     sal_usr_id
    ) 
    REFERENCES users 
    ( 
     usr_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE streets 
    ADD CONSTRAINT str_sty_FK FOREIGN KEY 
    ( 
     str_sty_id
    ) 
    REFERENCES street_types 
    ( 
     sty_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE users_cards 
    ADD CONSTRAINT uca_car_FK FOREIGN KEY 
    ( 
     uca_car_id
    ) 
    REFERENCES cards 
    ( 
     car_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE users_cards 
    ADD CONSTRAINT uca_usr_FK FOREIGN KEY 
    ( 
     uca_usr_id
    ) 
    REFERENCES users 
    ( 
     usr_id
    ) 
    NOT DEFERRABLE 
;

ALTER TABLE users 
    ADD CONSTRAINT usr_add_FK FOREIGN KEY 
    ( 
     usr_add_id
    ) 
    REFERENCES addresses 
    ( 
     add_id
    ) 
    NOT DEFERRABLE 
;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            21
-- CREATE INDEX                             0
-- ALTER TABLE                             39
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
